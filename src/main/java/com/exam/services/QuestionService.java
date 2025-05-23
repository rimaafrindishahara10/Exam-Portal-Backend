package com.exam.services;

import com.exam.models.exam.Question;
import com.exam.models.exam.Quiz;

import java.util.Set;

public interface QuestionService {

    public Question addQuestion(Question question);
    public Question updateQuestion(Question question);
    public Set<Question> getAllQuestion();
    public Question getQuestion(Long questionId);
    public Set<Question> getQuestionOfQuiz(Quiz quiz);
    public void deleteQuestion(Long questionId);
}
