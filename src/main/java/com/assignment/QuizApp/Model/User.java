package com.assignment.QuizApp.Model;

import com.assignment.QuizApp.DTO.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private String username;

    private int correctAnswers, incorrectAnswers;

    @Column
    @ElementCollection
    private List<Long> answeredQuestions = new ArrayList<>();

    public UserDTO toUserDTO() {
        UserDTO userDTO= new UserDTO();
        userDTO.setId(this.id);
        userDTO.setUsername(this.username);
        userDTO.setIncorrectAnswers(this.incorrectAnswers);
        userDTO.setCorrectAnswers(this.correctAnswers);
        userDTO.setTotalQuestionsAnswered(this.answeredQuestions.size());
        return userDTO;
    }

}
