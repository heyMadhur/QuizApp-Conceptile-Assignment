package com.assignment.QuizApp.Repository;

import com.assignment.QuizApp.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Long> {

    @Query("SELECT q.id FROM Question q")
    List<Long> getAllQuestionIds();  // Fetch all question IDs

    @Query("SELECT q.id FROM Question q WHERE q.category = :category")
    List<Long> getQuestionIdsByCategory(String category);



}
