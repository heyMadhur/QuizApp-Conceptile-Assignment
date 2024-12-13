package com.assignment.QuizApp.DTO;

import com.assignment.QuizApp.Model.Question;
import com.assignment.QuizApp.Model.User;
import com.assignment.QuizApp.Repository.QuestionRepo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserStatsDTO {

    private String username;
    private int totalAnswerQuestions, correctAnswers, incorrectAnswers;

    private List<Question> actualAnswersForEachQuestion;

    public UserStatsDTO(User user, List<Question> actualAnswersForEachQuestion) {
        this.username= user.getUsername();
        this.totalAnswerQuestions= user.getAnsweredQuestions().size();
        this.correctAnswers= user.getCorrectAnswers();
        this.incorrectAnswers= user.getIncorrectAnswers();
        this.actualAnswersForEachQuestion= actualAnswersForEachQuestion;
    }

}
