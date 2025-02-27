package com.exam.services.impl;

import com.exam.models.exam.Question;
import com.exam.models.exam.Quiz;
import com.exam.repositories.QuestionRepo;
import com.exam.services.QuestionService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepo questionRepo;

    public QuestionServiceImpl(QuestionRepo questionRepo) {
        this.questionRepo = questionRepo;
    }


    @Override
    public Question addQuestion(Question question) {
        return this.questionRepo.save ( question );
    }

    @Override
    public Question updateQuestion(Question question) {
        return this.questionRepo.save ( question );
    }

    @Override
    public Set<Question> getAllQuestion() {
        return new HashSet<> (this.questionRepo.findAll ());
    }

    @Override
    public Question getQuestion(Long questionId) {
        return this.questionRepo.findById ( questionId ).get ();
    }

    @Override
    public Set<Question> getQuestionOfQuiz(Quiz quiz) {
        return this.questionRepo.findByQuiz(quiz);
    }
}
