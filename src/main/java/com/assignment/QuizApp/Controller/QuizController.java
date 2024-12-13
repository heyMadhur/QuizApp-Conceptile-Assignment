package com.assignment.QuizApp.Controller;

import com.assignment.QuizApp.DTO.UserAnswer;
import com.assignment.QuizApp.DTO.UserStatsDTO;
import com.assignment.QuizApp.Model.Question;
import com.assignment.QuizApp.Model.User;
import com.assignment.QuizApp.Service.QuestionService;
import com.assignment.QuizApp.Service.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    UserService userService;

    @Autowired
    QuestionService questionService;

    @PostMapping("/start")
    public ResponseEntity<?> startQuiz(@RequestParam String username) throws Exception {
        User user= userService.findUser(username);
        HashMap<String, Object> finalResponse= new HashMap<>();
        if(user!=null) {
            userService.resetUserStats(user);
            finalResponse.put("message", "Welcome Back "+user.getUsername());
            finalResponse.put("user", user.toUserDTO());
            return new ResponseEntity<>(finalResponse, HttpStatus.OK);
        }

        user= userService.registerUser(username);
        finalResponse.put("message", "Welcome "+user.getUsername());
        finalResponse.put("user", user.toUserDTO());
        return new ResponseEntity<>(finalResponse, HttpStatus.OK);

    }

    @GetMapping("/get-question")
    public ResponseEntity<?> getRandomQuestion(@RequestParam String username, @RequestParam(required = false) String category) {
        User user= userService.findUser(username);
        if(user== null) {
            return new ResponseEntity<>("User not found. Start a new Quiz and register user.", HttpStatus.NOT_FOUND);
        }

        Long quesId= questionService.getRandomQuestion(user, category);

        if(quesId==null) {
            return new ResponseEntity<>("No more unique questions available. Kindly, View your final Stats.", HttpStatus.OK);
        }

        Question question= questionService.findQuestion(quesId);
        if (question == null) {
            return new ResponseEntity<>("Error retrieving question.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(question.toQuesWrapper(), HttpStatus.OK);

    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitAnswer(
            @RequestParam String username,
            @RequestBody(required = true) UserAnswer userAnswer ) {

        User user= userService.findUser(username);
        if(user== null) {
            return new ResponseEntity<>("User not found. Start a new Quiz and register user.", HttpStatus.NOT_FOUND);
        }
        if(user.getAnsweredQuestions().contains(userAnswer.getQuesId())) {
            return new ResponseEntity<>("Question Already Answered", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        boolean isSubmitted= questionService.submitQuestion(user, userAnswer);

        HashMap<String, Object> finalRes= new HashMap<>();
        if(isSubmitted) {
            finalRes.put("message", "Answer Submitted Successfully");
            finalRes.put("totalQuestionAnswered", user.getAnsweredQuestions().size());
            return new ResponseEntity<>(finalRes, HttpStatus.OK);
        }

        finalRes.put("message", "Answer did not Submitted");
        return new ResponseEntity<>(finalRes, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping("get-result")
    public ResponseEntity<?> getResults(@RequestParam String username) {

        User user= userService.findUser(username);
        if(user== null) {
            return new ResponseEntity<>("User not found. Start a new Quiz and register user.", HttpStatus.NOT_FOUND);
        }

//        1st Way to send Response by HashMap
        HashMap<String, Object> finalResponse= new HashMap<>();
        finalResponse.put("username", user.getUsername());
        finalResponse.put("Score", user.getCorrectAnswers());
        finalResponse.put("Total Marks", user.getAnsweredQuestions().size());
        finalResponse.put("message", "To view your Stats, Kindly hit /api/quiz/stats");

        return new ResponseEntity<>(finalResponse, HttpStatus.OK);

    }

    @GetMapping("/stats")
    public ResponseEntity<?> getStats (@RequestParam String username) {
        User user= userService.findUser(username);
        if(user== null) {
            return new ResponseEntity<>("User not found. Start a new Quiz and register user.", HttpStatus.NOT_FOUND);
        }

        List<Question> questionListWIthAnswer= questionService.getAllQuestionsAnsweredByUser(user);

//        2nd Way to send Response by Creating your Response DTO, here ie. UserStatsDTO
        UserStatsDTO userStats= new UserStatsDTO(user, questionListWIthAnswer);

        return new ResponseEntity<>(userStats, HttpStatus.OK);

    }

}
