package com.bhargava.demoquiz.controller;

import com.bhargava.demoquiz.model.Question;
import com.bhargava.demoquiz.model.QuestionWrapper;
import com.bhargava.demoquiz.model.Response;
import com.bhargava.demoquiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class quizController {
    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title ){
        return quizService.createQuiz(category,numQ,title);
    }
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitResponse(@PathVariable Integer id,@RequestBody List<Response> responses){
        return quizService.CalculateResult(id,responses);
    }


}
