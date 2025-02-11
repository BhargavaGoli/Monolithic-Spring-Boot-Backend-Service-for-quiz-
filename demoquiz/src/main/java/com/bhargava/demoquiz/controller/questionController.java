package com.bhargava.demoquiz.controller;

import com.bhargava.demoquiz.model.Question;
import com.bhargava.demoquiz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class questionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public List<Question> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{cat}")
    public List<Question> getQuestionsByCategory(@PathVariable String cat){
        return questionService.getQuestionsByCategory(cat);
    }

    @PostMapping("add")
    public String addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);

    }
}
