package com.revature.servlets;

//import com.example.model.SuperVillain;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MarsController {
    public static void marsFinder(HttpServletRequest req, HttpServletResponse res) throws IOException {
        SuperVillain mars = new SuperVillain("Mars", "Sandstorm", 400_000);
        res.getWriter().write(new ObjectMapper().writeValueAsString(mars));
    }
}
