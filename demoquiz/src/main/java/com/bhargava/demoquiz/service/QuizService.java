package com.bhargava.demoquiz.service;

import com.bhargava.demoquiz.dao.QuestionDao;
import com.bhargava.demoquiz.dao.QuizDao;
import com.bhargava.demoquiz.model.Question;
import com.bhargava.demoquiz.model.QuestionWrapper;
import com.bhargava.demoquiz.model.Quiz;
import com.bhargava.demoquiz.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions=questionDao.findRandomQuestionsByCategory(category,numQ);

        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("succes", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
       Optional<Quiz> quiz= quizDao.findById(id);
       List<Question> questionsFromDB=quiz.get().getQuestions();
       List<QuestionWrapper> questionsForUser=new ArrayList<>();
       for(Question q:questionsFromDB){
           QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestion(),q.getOption1(),q.getOption2());
           questionsForUser.add(qw);
       }

       return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> CalculateResult(Integer id, List<Response> responses) {
        Quiz quiz=quizDao.findById(id).get();
        List<Question> questions=quiz.getQuestions();
        int right=0;
        int i=0;
        for(Response response: responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer())) right++;
            i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
