package com.assignment.QuizApp.Service;

import com.assignment.QuizApp.Model.User;
import com.assignment.QuizApp.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public User findUser(String username) {
        return userRepo.findByUsername(username);

    }

    public User registerUser(String username) {
        User user= new User();
        user.setUsername(username);
        System.out.println("User="+ user);
        return userRepo.save(user);
    }

    public void resetUserStats(User user) throws Exception {
        user.setIncorrectAnswers(0);
        user.setCorrectAnswers(0);
        user.setAnsweredQuestions(new ArrayList<>());
        try{
            userRepo.save(user);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
