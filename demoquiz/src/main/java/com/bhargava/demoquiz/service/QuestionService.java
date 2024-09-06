package com.bhargava.demoquiz.service;

import com.bhargava.demoquiz.model.Question;
import com.bhargava.demoquiz.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public List<Question> getAllQuestions(){
        return questionDao.findAll();
    }

    public List<Question> getQuestionsByCategory(String cat) {
        return questionDao.findBycategory(cat);
    }

    public String addQuestion(Question question) {
        questionDao.save(question);
        return("sucess");
    }
}
