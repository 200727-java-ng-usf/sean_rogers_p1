package com.revature.utilities;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOUtilities {

    private static Properties props = new Properties();

    public DAOUtilities() {
        super();
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            System.out.println("Loader: " + loader.toString());
            InputStream propsInput = loader.getResourceAsStream("application.properties");
            System.out.println("Props input: " + propsInput.toString());
            props.load(new FileReader("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*try{
            props.load(new FileReader("src/main/resources/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    /*public static DAOUtilities getInstance() {
        return daoUtilities;
    }*/

    public static synchronized Connection getConnection() throws SQLException {
        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");
            //props.load(new FileReader("application.properties"));
            System.out.println(props.toString());

            connection = DriverManager.getConnection(props.getProperty("url"),
                    props.getProperty("username"),
                    props.getProperty("password"));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } /*catch (IOException e) {
            e.printStackTrace();
        }*/

        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("Could not register driver!");
                e.printStackTrace();
            }
        }

        //If connection was closed then retrieve a new connection
        if (connection.isClosed()){
            System.out.println("Opening new connection...");
            connection = DriverManager.getConnection(props.getProperty("url"),
                    props.getProperty("username"),
                    props.getProperty("password"));
        }
        return connection;
    }

}
