package com.example.vaibh.gobiz.pojos;

import java.util.ArrayList;
import java.util.Arrays;

public class TrueFalseQuestion extends QuizQuestion {

    public TrueFalseQuestion(String question, boolean answer) {
        super(question, new ArrayList<>(Arrays.asList("True", "False")), answer ? 0 : 1);
    }
}
