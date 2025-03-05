package com.exam.services.impl;

import com.exam.models.exam.Quiz;
import com.exam.repositories.QuizRepo;
import com.exam.services.QuizService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuizRepo quizRepo;

    public QuizServiceImpl(QuizRepo quizRepo) {
        this.quizRepo = quizRepo;

    }




    @Override
    public Quiz addQuiz(Quiz quiz) {
        return this.quizRepo.save(quiz);
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        return this.quizRepo.save ( quiz );
    }

    @Override
    public Set<Quiz> getAllQuiz() {
        return new HashSet<> (this.quizRepo.findAll ());
    }

    @Override
    public Quiz getQuiz(Long quizId) {
        return this.quizRepo.findById ( quizId ).get ();
    }

    @Override
    public void deleteQuiz(Long quizId) {
        Quiz quiz = this.quizRepo.findById ( quizId ).get ();
        this.quizRepo.delete ( quiz );
    }
}
