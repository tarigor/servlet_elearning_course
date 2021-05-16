package by.epam.servletCourse.servlets;

import by.epam.servletCourse.bean.User;
import by.epam.servletCourse.dao.ApplicationDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/getProfileDetails")
public class getProfileDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get the username from the session
        System.out.println("User name in profile servlet: " + req.getSession().getAttribute("userName"));
        String userName = (String) req.getSession().getAttribute("userName");

        ApplicationDao dao = new ApplicationDao();
        User user = dao.getProfilesDetails(userName);

        req.setAttribute("user", user);
        //forward control to profile.jsp
        req.getRequestDispatcher("/html/profile.jsp").forward(req, resp);
    }
}
