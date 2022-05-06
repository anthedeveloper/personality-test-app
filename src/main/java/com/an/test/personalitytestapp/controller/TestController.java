package com.an.test.personalitytestapp.controller;

import com.an.test.personalitytestapp.controllerUtil.TestControllerUtil;
import com.an.test.personalitytestapp.model.Answer;
import com.an.test.personalitytestapp.model.Question;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/test")
@AllArgsConstructor
public class TestController {

    private final TestControllerUtil controllerUtil;

    @GetMapping(path = "/getQuestionList")
    public ResponseEntity<List<Question>>  getQuestionList(){
        return controllerUtil.getQuestionList();
    }

    @PostMapping(path = "/submitTest")
    public ResponseEntity<String> submitTest(@Valid @RequestBody Answer answer){
        return controllerUtil.submitTest(answer);

    }

    @PostMapping(path = "/nextQuestion")
    public ResponseEntity<Question> nextQuestion(@RequestBody Answer answer){
        return controllerUtil.nextQuestion(answer);
    }
}
