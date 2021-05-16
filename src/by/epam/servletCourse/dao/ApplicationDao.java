package by.epam.servletCourse.dao;

import by.epam.servletCourse.bean.History;
import by.epam.servletCourse.bean.Product;
import by.epam.servletCourse.bean.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDao {
    public List<Product> searchProducts(String searchString) {

        Product product = new Product();
        List<Product> productList = new ArrayList<>();

        Connection connection = DBConnection.getConnectionToDatabase();

        try {
            Statement statement = connection.createStatement();

            String sql = "select * from products where product_name like '%" + searchString + "%'";

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                product.setProductId(resultSet.getInt("product_id"));
                product.setProductName(resultSet.getString("product_name"));
                product.setProductImagePath(resultSet.getString("image_path"));
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    public Integer registerUser(User user) {
        Integer rowsAffected = 0;
        try {
            //get the connection for the DB
            Connection connection = DBConnection.getConnectionToDatabase();
            //write the insert query
            String sql = "insert into users values(?,?,?,?,?,?)";
            //set parameters with PreparedStatement
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setInt(5, user.getAge());
            statement.setString(6, user.getActivity());
            //execute the statement
            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    public User getProfilesDetails(String userName) {
        User user = null;
        try {
            //get connection to database
            Connection connection = DBConnection.getConnectionToDatabase();
            //write select query to get profile details
            String sql = "select * from users where username=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userName);
            //execute query, get result, pass user details
            ResultSet resultSet = statement.executeQuery();
            while ((resultSet.next())) {
                user = new User();
                user.setUserName(resultSet.getString("username"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getInt("age"));
                user.setActivity(resultSet.getString("activity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean validateUser(String userName, String password) {
        try {
            Connection connection = DBConnection.getConnectionToDatabase();
            String sql = "select * from users where username=? and password=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userName);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            while ((resultSet.next())) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<History> getHistory(String userName) {
        System.out.println("user name->" + userName);
        ArrayList<History> historyList = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnectionToDatabase();
            String sql = "select * from orders where user_name=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userName);
            ResultSet set = statement.executeQuery();
            while (set.next()){
                History history = new History();
                history.setOrderDate(set.getString("order_date"));
                history.setProductName(set.getString("product_name"));
                history.setImagePath(set.getString("image_path"));
                historyList.add(history);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        historyList.forEach(System.out::println);
        return historyList;
    }
}
