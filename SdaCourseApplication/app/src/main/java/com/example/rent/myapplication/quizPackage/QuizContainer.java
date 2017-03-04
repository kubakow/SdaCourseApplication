package com.example.rent.myapplication.quizPackage;

import java.util.List;

/**
 * Created by RENT on 2017-02-27.
 */

public class QuizContainer {
    public int getQuestionsCount() {
        return questionsCount;
    }

    public List<QuizQuestion> getQuestions() {
        return questions;
    }

    private int questionsCount;
    private List<QuizQuestion> questions;


}