package by.epam.servletCourse.servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletConfig config = getServletConfig();
        System.out.println(config.getInitParameter("URL"));

        String value = req.getParameter("name");
        String htmlResponse = "<html><h3>Hello Igor!!!You will win Green Card!!!Do not give up!!!</h3></html>";
        PrintWriter writer = resp.getWriter();
        writer.write(htmlResponse + " " + value + " " + config.getInitParameter("URL") +
                " " + getServletContext().getInitParameter("DBurl"));
    }
}
