package com.exam.controllers;

import com.exam.models.exam.Quiz;
import com.exam.services.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }


    //Method->Add-Quiz
    @PostMapping("/")
    public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz){

        return ResponseEntity.ok (this.quizService.addQuiz ( quiz ));
    }
    //Method->Update-Quiz
    @PutMapping("/")
    public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz){
        return ResponseEntity.ok (this.quizService.addQuiz ( quiz ));
    }

    //Method->Get-AllQuiz
    @GetMapping("/")
    public ResponseEntity<?> getAllQuiz(){
        return ResponseEntity.ok (this.quizService.getAllQuiz ());
    }

    //Method->Get-Quiz
    @GetMapping("/{quizId}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable Long quizId){
        return ResponseEntity.ok (this.quizService.getQuiz ( quizId ));
    }

    //Method->Delete-Quiz
    @DeleteMapping("/{quizId}")
    public ResponseEntity<?> deleteQuiz(@PathVariable Long quizId){
        this.quizService.deleteQuiz ( quizId );
        return ResponseEntity.ok ( Map.of("message", "Quiz deleted successfully"));
    }






}
