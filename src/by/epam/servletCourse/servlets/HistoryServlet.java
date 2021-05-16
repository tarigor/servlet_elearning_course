package by.epam.servletCourse.servlets;

import by.epam.servletCourse.bean.History;
import by.epam.servletCourse.dao.ApplicationDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/history")
public class HistoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationDao dao = new ApplicationDao();
        String userName = (String) req.getSession().getAttribute("userName");
        ArrayList<History> historyList = (ArrayList<History>) dao.getHistory(userName);
        historyList.forEach(history -> System.out.println("ddd"+history));
        req.setAttribute("historyList", historyList);
        req.setAttribute("userName", userName);
        req.getRequestDispatcher("/html/history.jsp").include(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
