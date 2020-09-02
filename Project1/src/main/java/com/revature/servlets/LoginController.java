package com.revature.servlets;

import javax.servlet.http.HttpServletRequest;

/*
    The purpose:

    Controllers handle the business logic of an endpoint
 */
public class LoginController {

    public static String login(HttpServletRequest req) {
        /*
            you may want to implement route guarding for your endpoints

            for example, you may want to make sure only ADMINs can access admin endpoints
         */

        //ensure that the method is a POST http method, else send them back to the login page
        if(!req.getMethod().equals("POST")) {
            return "/html/login.html";
        }

        // acquire the form data
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        /*
         * for YOUR project, you won't hardcode "cheese" and "louise" you'll go to
         * the DB and find the ACTUAL password that should be used, based on the username
         * they typed in
         */
        if(!(username.equals("cheese") && password.equals("louise"))) {
            //this logic will trigger when the username & password are incorrect
            return "/api/wrongcreds";
        } else {
            /*
                in YOUR project, you'll probably be storing the entire user object in the
                session (which will ALSO container whether or not it is a manager or employee)
             */
            req.getSession().setAttribute("loggedUsername", username);
            req.getSession().setAttribute("loggedPassword", password);
            return "/api/home";
        }

        //return null;
    }

}
