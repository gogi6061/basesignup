package com.servlet.mathServlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

@WebServlet("/sumInts")

public class SumIntsServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double sum;
        Stack<Double> stack = new Stack<>();

        try {
            String formInput = req.getParameter("a") + " ";
            String s = new Scanner(formInput).nextLine();
            Scanner scan = new Scanner(s);
            String i;


            while (scan.hasNext()) {
                try {
                    i = scan.next();

                    if (isDigit(i)) {
                        stack.push(Double.parseDouble(i));

                        continue;

                    }
                    if (stack.size() >= 2 & ("^×*/+-").contains(i)) {
                        Double a = stack.pop();
                        Double b = stack.pop();

                        switch (i) {
                            case "*" -> stack.push(a * b);
                            case "×" -> stack.push(a * b);
                            case "/" -> stack.push(b / a);
                            case "-" -> stack.push(a - b);
                            case "+" -> stack.push(a + b);
                            case "^" -> stack.push(Math.pow(b, a));
                            case ":" -> stack.push(b / a);

                        }

                    }
                } catch (Exception ig) {
                    ig.printStackTrace();

                }


            }

            sum = stack.pop();

            AnswerDirectory.save(sum);

            req.setAttribute("answers", AnswerDirectory.getAnswers());
            req.setAttribute("sum", sum);


        } catch (Exception exception) {
            req.setAttribute("sum", "нет результата");
            exception.printStackTrace();
            req.setAttribute("answers", AnswerDirectory.getAnswers());


        } finally {
            req.getRequestDispatcher("jsp/mathJSP/summer.jsp").forward(req, resp);


        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}
