package com.servlet.loginAndAuthServlets;

import com.servlet.database.DataBaseDAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Arrays;

@WebServlet("/homepage")
public class HomePageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


       req.getRequestDispatcher("jsp/SignUpForm.jsp").forward(req, resp);
       if()


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        session.setMaxInactiveInterval(-1);


//        Cookie cookie = new Cookie("user","red");
//        resp.addCookie(cookie);
//        Cookie[] cookies = req.getCookies();
//        System.out.println(cookies[0].getValue()     );


        DataBaseDAO dao = DataBaseDAO.returnDbDAO();
        try {
            dao.setConnectionToDB();
            String entryLogin = req.getParameter("login").trim();
            String entryPassword = req.getParameter("password").trim();
            String logPass = dao.getState(entryLogin, entryPassword);
            while (true) {
                System.out.println(dao.getState("",""));

                if((entryLogin+entryPassword).equals("")){
                    resp.sendRedirect("/homepage");
                    break;
                }

                if (logPass.equals("пустая строка")) {
                    dao.create(entryLogin, entryPassword,session.getId());
                    System.out.println("Вы зарегестрировались");
                    resp.sendRedirect("/sumInts");
                    break;
                } else if (logPass.contains(entryLogin) & !logPass.contains(entryPassword)) {
                    resp.sendRedirect("/homepage");
                    break;
                } else if (logPass.equals(entryLogin + " " + entryPassword)) {
                    System.out.println("Вы успешно вошли");
                    resp.sendRedirect("/sumInts");
                    break;
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
