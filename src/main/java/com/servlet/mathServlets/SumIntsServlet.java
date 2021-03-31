package com.servlet.mathServlets;

import com.servlet.database.DataBaseDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Stack;

@WebServlet("/sumInts")

public class SumIntsServlet extends HttpServlet {
    DataBaseDAO dao = DataBaseDAO.returnDbDAO();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double sum;
        Stack<Double> stack = new Stack<>();
        HttpSession session = req.getSession();

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
                            case "*", "×" -> stack.push(a * b);
                            case "/", ":" -> stack.push(b / a);
                            case "-" -> stack.push(a - b);
                            case "+" -> stack.push(a + b);
                            case "^" -> stack.push(Math.pow(b, a));

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
            try {
                if (dao.checkSessionID(session.getId())) {
                    req.getRequestDispatcher("/jsp/mathJSP/summer.jsp").forward(req, resp);

                } else {
                    resp.sendRedirect("/homepage");

                }


                //req.getRequestDispatcher("/jsp/mathJSP/summer.jsp").forward(req, resp);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


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
