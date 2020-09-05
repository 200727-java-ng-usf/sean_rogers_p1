package com.revature.utilities;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOUtilities {

    //private static DAOUtilities daoUtilities = new DAOUtilities();

    private static Properties props = new Properties();

    public DAOUtilities() {
        super();
        try{
            System.out.println();
            props.load(new FileReader("C:\\Users\\Mr-Ro\\repos\\reva-repos\\batch-repos\\200727-jang-ng-usf\\sean_rogers_p1\\Project1\\src\\main\\resources\\application.properties"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public static DAOUtilities getInstance() {
        if(daoUtilities == null) {
            daoUtilities = new DAOUtilities();
        }
        return daoUtilities;
    }*/

    public static synchronized Connection getConnection() throws SQLException {
        Connection connection = null;

        try {
//
//            ClassLoader loader = Thread.currentThread().getContextClassLoader();
//            System.out.println("Loader: " + loader.toString());
//            InputStream propsInput = loader.getResourceAsStream("application.properties");
//            System.out.println("Props input: " + propsInput.toString());
//            props.load(new FileReader("application.properties"));
            //props.load(new FileReader("C:\\Users\\Mr-Ro\\repos\\reva-repos\\batch-repos\\200727-jang-ng-usf\\sean_rogers_p1\\Project1\\src\\main\\resources\\application.properties"));
            //System.out.println("Line44: " + new File("C:\\Users\\Mr-Ro\\repos\\reva-repos\\batch-repos\\200727-jang-ng-usf\\sean_rogers_p1\\Project1\\src\\main\\resources\\application.properties").getPath());

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("",
                    "",
                    "");
            /*Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    props.getProperty("url"),
                    props.getProperty("username"),
                    props.getProperty("password")
            );*/
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

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
