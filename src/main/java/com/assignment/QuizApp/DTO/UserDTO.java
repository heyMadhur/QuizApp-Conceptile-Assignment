package com.assignment.QuizApp.DTO;

import com.assignment.QuizApp.Model.User;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;

    private String username;

    private int totalQuestionsAnswered, correctAnswers, incorrectAnswers;

}
