package com.an.test.personalitytestapp.controller;

import com.an.test.personalitytestapp.model.Answer;
import com.an.test.personalitytestapp.model.Question;
import com.an.test.personalitytestapp.repository.QuestionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application-it.properties"
)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    @Order(1)
    void getQuestionList_throws_NotFoundException() throws Exception {
        questionRepository.deleteAll();
        ResultActions resultActions = mockMvc.perform(
                get("/api/test/getQuestionList")
                        .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isNotFound());
    }

    @Test
    @Order(2)
    void submitTest_emptyAnswer_throws_EmptyAnswerException() throws Exception {
        Answer answer = new Answer(1,1,"");

        ResultActions resultActions = mockMvc.perform(
                post("/api/test/submitTest")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(answer)));
        resultActions.andExpect(status().isBadRequest());
    }

    @Test
    @Order(3)
    void submitTest_successful() throws Exception {
        Answer answer = new Answer(2,3,"answer");

        ResultActions resultActions = mockMvc.perform(
                post("/api/test/submitTest")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(answer)));
        resultActions.andExpect(status().isOk());
    }

    @Test
    @Order(4)
    void nextQuestion_throws_EmptyAnswerException() throws Exception {
        Answer answer = new Answer(1,1,"");

        ResultActions resultActions = mockMvc.perform(
                post("/api/test/nextQuestion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(answer)));

        resultActions.andExpect(status().isBadRequest());
    }

    @Test
    @Order(5)
    @Disabled
    void nextQuestion_successful() throws Exception {
        for(int i = 1; i<10 ; i++){
            Question question = new Question(
                    String.format("Question number %d", i));
            questionRepository.save(question);
        }
        Answer answer = new Answer(2,3,"answer");

        ResultActions resultActions = mockMvc.perform(
                post("/api/test/nextQuestion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(answer)));

        resultActions.andExpect(status().isOk());
    }

    @Test
    @Order(6)
    @Disabled
    void nextQuestion_successful_passConditionalQuestion() throws Exception {
        Answer answer = new Answer(3,1,"answer");

        ResultActions resultActions = mockMvc.perform(
                post("/api/test/nextQuestion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(answer)));

        resultActions.andExpect(status().isOk());
    }

}