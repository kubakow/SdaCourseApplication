package com.example.rent.myapplication.quizPackage;

import java.util.List;

/**
 * Created by RENT on 2017-02-25.
 */

public class QuizQuestion {

    private int id;
    private String question;
    private List<SingleAnswer> answers;

    public QuizQuestion(int id, String question, List<SingleAnswer> answers) {
        this.id = id;
        this.question = question;
        this.answers = answers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

}




