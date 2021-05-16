package by.epam.servletCourse.servlets;

import by.epam.servletCourse.dao.ApplicationDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //dispatch the request to login.jsp resource
        RequestDispatcher dispatcher = req.getRequestDispatcher("/html/login.jsp");
        dispatcher.include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get the username from the login form
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        //call dao for validation logic
        ApplicationDao dao = new ApplicationDao();
        boolean isValid = dao.validateUser(userName, password);
        //check is invalid and set up error mes
        if (isValid) {
            //set up the Http session
            HttpSession session = req.getSession();
            //set the username as an attribute
            session.setAttribute("userName", userName);
            //forward to home jsp
            req.getRequestDispatcher("/html/home.jsp").forward(req, resp);
        } else {
            String errorMessage = "Invalid Credentials, please login again1";
            req.setAttribute("error", errorMessage);
            req.getRequestDispatcher("/html/login.jsp").forward(req, resp);
        }
    }
}
