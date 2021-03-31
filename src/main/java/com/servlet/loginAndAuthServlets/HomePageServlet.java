package com.servlet.loginAndAuthServlets;

import com.servlet.database.DataBaseDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/homepage")
public class HomePageServlet extends HttpServlet {
    DataBaseDAO dao = DataBaseDAO.returnDbDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        req.getRequestDispatcher("jsp/SignUpForm.jsp").forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


//        Cookie cookie = new Cookie("user","red");
//        resp.addCookie(cookie);
//        Cookie[] cookies = req.getCookies();
//        System.out.println(cookies[0].getValue()     );
        HttpSession session = req.getSession();

        try {
            dao.setConnectionToDB();
            String entryLogin = req.getParameter("login").trim().toLowerCase();
            String entryPassword = req.getParameter("password").trim().toLowerCase();
            String logPass = dao.getState(entryLogin, entryPassword);

            while (true) {
                //System.out.println(dao.getState("", ""));

                if((entryLogin+entryPassword).equals("")){
                    resp.sendRedirect("/homepage");
                    break;
                }

                if (logPass.equals("пустая строка")) {
                    dao.create(entryLogin, entryPassword,session.getId());
                    System.out.println("Вы зарегестрировались");
                    resp.sendRedirect("/sumInts");
                    break;
                }  if (logPass.contains(entryLogin) & !logPass.contains(entryPassword)) {
                    resp.sendRedirect("/homepage");
                    break;
                }  if (logPass.equals(entryLogin + " " + entryPassword)) {
                    System.out.println("Вы успешно вошли");
                    dao.updateSessionID(entryLogin, entryPassword, session.getId() );
                    resp.sendRedirect("/sumInts");
                    break;
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
