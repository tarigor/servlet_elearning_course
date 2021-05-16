package by.epam.servletCourse.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnectionToDatabase() {
        Connection connection = null;
        try {
            //load the driver class
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("The mysql driver has been registered!");
            //get hold of the driver manager
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hplus", "root", "755019");
        } catch (ClassNotFoundException e) {
            System.out.println("The mysql driver has been not found");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed!Check output console!");
            e.printStackTrace();
        }
        if (connection != null) {
            System.out.println("The connection with database has been successfully established!");
        }

        return connection;
    }
}
