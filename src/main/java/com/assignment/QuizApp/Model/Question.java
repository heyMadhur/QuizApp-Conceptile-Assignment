package com.assignment.QuizApp.Model;

import com.assignment.QuizApp.DTO.QuestionWrapper;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "questions")
@Getter
@Setter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question_title", nullable = false, length = 500)
    private String questionTitle;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String option1;

    @Column(nullable = false)
    private String option2;

    @Column(nullable = false)
    private String option3;

    @Column(nullable = false)
    private String option4;

    @Column(name = "right_answer", nullable = false)
    private int rightAnswer;

    public QuestionWrapper toQuesWrapper(){
        QuestionWrapper quesWrap= new QuestionWrapper();
        quesWrap.setId(this.getId());
        quesWrap.setQuestionTitle(this.getQuestionTitle());
        quesWrap.setOption1(this.getOption1());
        quesWrap.setOption2(this.getOption2());
        quesWrap.setOption3(this.getOption3());
        quesWrap.setOption4(this.getOption4());

        return quesWrap;
    }
}
