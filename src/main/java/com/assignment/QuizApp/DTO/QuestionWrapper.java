package com.assignment.QuizApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionWrapper {
    private Long id;
    private String questionTitle, option1, option2, option3, option4;

}
