package com.revature.servlets;

/*import com.example.contoller.DannyBoyController;
import com.example.contoller.MarsController;
import com.example.model.SuperVillain;*/
import com.fasterxml.jackson.databind.ObjectMapper;

//import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonRequestHelper {
    public static void process(HttpServletRequest req, HttpServletResponse res) throws IOException {

        System.out.println(req.getRequestURI());

        switch(req.getRequestURI()) {
            case "/FrontControllerExample/json/dannyboy":
                DannyBoyController.dannyFinder(req,res);
                break;
            case "/FrontControllerExample/json/mars":
                MarsController.marsFinder(req,res);
                break;
            default:
                SuperVillain nullVill = new SuperVillain(null, null, 0);
                res.getWriter().write(new ObjectMapper().writeValueAsString(nullVill));
        }
    }
}
