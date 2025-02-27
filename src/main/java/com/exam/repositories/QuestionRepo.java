package com.exam.repositories;


import com.exam.models.exam.Question;
import com.exam.models.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
@Repository
public interface QuestionRepo extends JpaRepository<Question,Long> {
    Set<Question> findByQuiz(Quiz quiz);
}
