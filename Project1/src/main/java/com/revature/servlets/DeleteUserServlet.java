package com.revature.servlets;

import com.revature.dao.ErsUsersDAO;
import com.revature.model.ErsUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteuserservlet")
public class DeleteUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ErsUsersDAO ersUsersDAO = new ErsUsersDAO();
        String usernameOfUserToDelete = req.getParameter("username");

        if(ersUsersDAO.delete(usernameOfUserToDelete)) {
            resp.getWriter().write("Success!\n" + usernameOfUserToDelete + " deleted from database!");
        } else {
            resp.getWriter().write("Failed");
        }

    }
}
