package by.epam.servletCourse.servlets;

import by.epam.servletCourse.bean.Product;
import by.epam.servletCourse.dao.ApplicationDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AddProduct")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get the HttpSession object
        HttpSession session = req.getSession();
        //create a cart as ArrayList for the user
        List<String> cart = (List<String>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        //add the selected product to the cart
        if (req.getParameter("product") != null) {
            cart.add(req.getParameter("product"));
        }
        session.setAttribute("cart", cart);
        //get search criteria from search servlet
        String search = (String) session.getAttribute("search");
        //get the search result from the DAO
        ApplicationDao dao = new ApplicationDao();
        List<Product> products = dao.searchProducts(search);
        //set the search results in request scope
        req.setAttribute("products", products);
        //forward to searchResults.jsp
        req.getRequestDispatcher("/html/search.jsp").forward(req, resp);
    }


}
