package com.revature.servlets;

//import com.example.model.SuperVillain;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DannyBoyController {
    public static void dannyFinder(HttpServletRequest req, HttpServletResponse res) throws IOException, JsonProcessingException {

        SuperVillain danny = new SuperVillain("Danny Boy", "Technopath", 250_000);
        res.getWriter().write(new ObjectMapper().writeValueAsString(danny));

    }
}
