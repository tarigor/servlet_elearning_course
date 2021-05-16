package by.epam.servletCourse.servlets;

import by.epam.servletCourse.bean.User;
import by.epam.servletCourse.dao.ApplicationDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;

@WebServlet("/register")
public class RegisterUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //collect all form data
        String userName = req.getParameter("username");
        String userPassword = req.getParameter("password");
        String firstName = req.getParameter("fname");
        String lastName = req.getParameter("lname");
        String activity = req.getParameter("activity");
        Integer age = Integer.parseInt(req.getParameter("age"));
        //fill it up in a user bean
        User user = new User(userName, userPassword, firstName, lastName, age, activity);
        //call a DAO layer and save an object in DB
        ApplicationDao dao = new ApplicationDao();
        Integer rows = dao.registerUser(user);
        //prepare an information message for User about success or failure of the operation
        String infoMessage = "";
        if (rows == 0) {
            infoMessage = "Sorry, an error occurred while adding user to DB";
        } else {
            infoMessage = "User registered successfully!";
        }
        //write the message back to the page in client browser
        String page = getHTMLString(req.getServletContext().getRealPath("/html/register.html"), infoMessage);
        resp.getWriter().write(page);
    }

    public String getHTMLString(String filePath, String message) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        String line = "";

        StringBuffer stringBuffer = new StringBuffer();
        while ((line = bufferedReader.readLine()) != null) {
            stringBuffer.append(line);
        }
        bufferedReader.close();

        String page = stringBuffer.toString();

        page = MessageFormat.format(page, message);

        return page;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String page = getHTMLString(req.getServletContext().getRealPath("/html/register.html"), "");
        resp.getWriter().write(page);
    }
}
