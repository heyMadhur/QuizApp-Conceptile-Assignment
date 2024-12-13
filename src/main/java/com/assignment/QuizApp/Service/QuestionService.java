package com.assignment.QuizApp.Service;

import com.assignment.QuizApp.DTO.QuestionWrapper;
import com.assignment.QuizApp.DTO.UserAnswer;
import com.assignment.QuizApp.Model.Question;
import com.assignment.QuizApp.Model.User;
import com.assignment.QuizApp.Repository.QuestionRepo;
import com.assignment.QuizApp.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class QuestionService {

    @Autowired
    QuestionRepo questionRepo;

    @Autowired
    UserRepo userRepo;

    public Question findQuestion(Long quesId) {
        return questionRepo.findById(quesId).orElse(null);
    }

    public Long getRandomQuestion(User user, String category) {
        List<Long> allQuestionIds;

        if(category!=null && !category.isEmpty()) {
            allQuestionIds= questionRepo.getQuestionIdsByCategory(category);
        } else {
            allQuestionIds= questionRepo.getAllQuestionIds();
        }

//        Remove all the Questions which have been already solved by User
        allQuestionIds.removeAll(user.getAnsweredQuestions());

        if (allQuestionIds.isEmpty()) {
            return null;
        }

        Random random= new Random();

        Long quesId= allQuestionIds.get(random.nextInt(allQuestionIds.size()));
        userRepo.save(user);

        return quesId;
    }

    public boolean submitQuestion(User user, UserAnswer userAnswer) {
        Question question= questionRepo.findById(userAnswer.getQuesId()).orElse(null);

        if(question==null) {
            return false;
        }

        if(question.getRightAnswer()== userAnswer.getAnswer()) {
            user.setCorrectAnswers(user.getCorrectAnswers()+1);
        } else {
            user.setIncorrectAnswers(user.getCorrectAnswers()+1);
        }
        user.getAnsweredQuestions().add(userAnswer.getQuesId());
        userRepo.save(user);

        return true;
    }

    public List<Question> getAllQuestionsAnsweredByUser(User user) {
        return questionRepo.findAllById(user.getAnsweredQuestions());
    }
}
