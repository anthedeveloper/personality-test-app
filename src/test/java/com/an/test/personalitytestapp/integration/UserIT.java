package com.an.test.personalitytestapp.integration;

import com.an.test.personalitytestapp.model.User;
import com.an.test.personalitytestapp.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@TestPropertySource(
        locations = "classpath:application-it.properties"
)
@AutoConfigureMockMvc
public class UserIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    private final Faker faker = new Faker();

    @Test
    void test_post_addUser_successful() throws Exception {
        String username = faker.name().username();
        String email = String.format("%s@gmail.com", username);

        User user = new User(username, email);

        mockMvc.perform(
                post("/api/user/addTestTaker")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)));

        userRepository.findAll();

        User userFromDB = userRepository.findAll().stream()
                .filter(us -> email.equalsIgnoreCase(us.getEmail()))
                .findAny().get();

        assertThat(userFromDB.getUserName()).isEqualTo(username);
    }

    @Test
    void test_post_addUser_saveTwoTimes_throws_UserEmailAlreadyExistsException() throws Exception {
        String username = faker.name().username();
        String email = String.format("%s@gmail.com", username);

        User user = new User(username, email);

        mockMvc.perform(
                post("/api/user/addTestTaker")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)));

        ResultActions resultActions2 = mockMvc.perform(
                post("/api/user/addTestTaker")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)));

        resultActions2.andExpect(status().isBadRequest());
    }

    @Test
    void test_getAllUsers_notFound() throws Exception {
        userRepository.deleteAll();
        ResultActions resultActions = mockMvc.perform(
                get("/api/user/getTestTakers")
                        .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isNotFound());

    }

    @Test
    void test_getAllUsers_successful() throws Exception {
        String username = faker.name().username();
        String email = String.format("%s@gmail.com", username);

        User user = new User(username, email);

        mockMvc.perform(
                post("/api/user/addTestTaker")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)));
        ResultActions resultActions = mockMvc.perform(
                get("/api/user/getTestTakers")
                        .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk());

    }
}
