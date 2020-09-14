package com.revature.utilities;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOUtilities {

    private static Properties props = new Properties();

    static{

        try {

            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream propsInput = loader.getResourceAsStream("application.properties");
            props.load(propsInput);

        } catch (IOException e) {

            e.printStackTrace();
            System.out.println("Failed to load application properties.");

        }
    }

    public DAOUtilities() {
        super();
    }

    public static synchronized Connection getConnection() throws SQLException {
        Connection connection = null;

        try {

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    props.getProperty("url"),
                    props.getProperty("username"),
                    props.getProperty("password")
            );
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        System.getenv("url"),
                        System.getenv("username"),
                        System.getenv("password"));
            } catch (SQLException e) {
                System.out.println("Could not register driver!");
                e.printStackTrace();
            }
        }

        /*if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("Could not register driver!");
                e.printStackTrace();
            }
        }*/

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
