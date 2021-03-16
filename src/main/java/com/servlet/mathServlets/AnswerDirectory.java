package com.servlet.mathServlets;


import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnswerDirectory extends HttpServlet {

    static private List<Double> answers = new ArrayList<>();

    public static void save(double Double) {
        answers.add(Double);
    }

    private static void reverseAndDeleteLI() {
        if (answers.size() == 6) {
            answers.remove(5);
        }

    }

    public static List<Double> getAnswers() {
        Collections.reverse(answers);
        reverseAndDeleteLI();
        return answers;

    }

}
