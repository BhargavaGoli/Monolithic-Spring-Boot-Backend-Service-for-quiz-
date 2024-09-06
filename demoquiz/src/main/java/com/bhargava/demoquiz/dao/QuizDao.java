package com.bhargava.demoquiz.dao;

import com.bhargava.demoquiz.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz,Integer> {
}
