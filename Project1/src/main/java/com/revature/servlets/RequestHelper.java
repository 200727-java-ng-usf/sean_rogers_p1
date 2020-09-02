package com.revature.servlets;


/*import com.example.contoller.HomeController;
import com.example.contoller.LoginController;*/

import javax.servlet.http.HttpServletRequest;

public class RequestHelper {

    public static String process(HttpServletRequest req) {

        System.out.println("THIS is the current URI active: " + req.getRequestURI());

        switch(req.getRequestURI()) {
            case "/FrontControllerExample/api/login":


                System.out.println("in login case");
//                return "/html/home.html";

                return LoginController.login(req);


            case "/FrontControllerExample/api/home":
                System.out.println("in home case");

                //not modularized
//                return "/html/login.html";

                return HomeController.home(req);
            default:
                System.out.println("in default");
                return "/html/badlogin.html";
        }


//        return "/html/home.html";
    }

}
