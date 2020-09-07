package com.revature.servlets.admin;

import com.revature.dao.ErsUsersDAO;
import com.revature.model.ErsUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addnewuser")
public class AddNewUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ErsUsersDAO ersUsersDAO = new ErsUsersDAO();


        ErsUser newUser = new ErsUser();
        newUser.setUsername(req.getParameter("username"));
        newUser.setPassword(req.getParameter("password"));
        newUser.setFirstName(req.getParameter("firstName"));
        newUser.setLastName(req.getParameter("lastName"));
        newUser.setEmail(req.getParameter("email"));
        newUser.setUserRoleId(Integer.parseInt(req.getParameter("role")));

        if(ersUsersDAO.save(newUser)){
            resp.getWriter().write("Success!\n" + newUser + " saved to database!");
        } else {
            resp.getWriter().write("Failed");
        }


        System.out.println("AddNewUserServlet line 27: " + newUser);

    }
}