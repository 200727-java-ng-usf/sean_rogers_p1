package com.revature.servlets;

import javax.servlet.http.HttpServletRequest;

public class HomeController {
    public static String home(HttpServletRequest req) {

        // a TON of business logic could go here
        return "/html/home.html";
    }
}
