package com.exam.controllers;

import com.exam.models.exam.Question;
import com.exam.models.exam.Quiz;
import com.exam.services.QuestionService;
import com.exam.services.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;
    private final QuizService quizService;

    public QuestionController(QuestionService questionService, QuizService quizService) {
        this.questionService = questionService;
        this.quizService = quizService;
    }


    //Method->Add-Question
    @PostMapping("/")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){
        return ResponseEntity.ok (this.questionService.addQuestion ( question ));
    }

    //Method->Update-Question
    @PutMapping("/")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question){
        return ResponseEntity.ok (this.questionService.addQuestion ( question ));
    }

    //Method->Get-AllQuestions of any quiz
    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<?> getAllQuestionOfQuiz(@PathVariable Long quizId){

        Quiz quiz = this.quizService.getQuiz ( quizId );
        Set<Question> questions = quiz.getQuestions ();
        List list = new ArrayList (questions);
        if (list.size ()>Integer.parseInt ( quiz.getNumOfQuestions ())){
            list = list.subList ( 0, Integer.parseInt ( quiz.getNumOfQuestions () + 1 ) );
        }

        Collections.shuffle ( list );
        return ResponseEntity.ok (list);
    }

   //Method->Get-Question
    @GetMapping("/{questionId}")
    public ResponseEntity<Question> getQuestion(@PathVariable Long questionId){
        return ResponseEntity.ok (this.questionService.getQuestion ( questionId ));
    }

    //Method->Delete-Question
    @DeleteMapping("/{questionId}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long questionId){
        this.questionService.deleteQuestion ( questionId );
        return ResponseEntity.ok ( "!!! This question has been deleted from database !!!" );
    }



}
