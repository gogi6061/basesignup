package com.servlet.mathServlets;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Stream;

@WebServlet("/fibInts")
public class FibInts extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {


            int n = Integer.parseInt(req.getParameter("a"));
            int fibs = fib(n);
            req.setAttribute("fib", fibs);
        }catch (Exception exception){
            req.setAttribute("fib", "нет результата");

        }finally {
            req.getRequestDispatcher("jsp/mathJSP/fibInts.jsp").forward(req, resp);

        }


    }

    static int fib(long n) {
        int prev = 0;
        int next = 1;
        for (int i = 0; i < n - 1; i++) {
            int tmp = prev;
            prev = next;
            next += tmp;


        }
        return prev;

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
