package com.revature.servlets.admin;

import com.revature.dao.ErsUsersDAO;
import com.revature.exceptions.UsernameNotFoundException;
import com.revature.model.ErsUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteuserservlet")
public class DeleteUserServlet extends HttpServlet {

    ErsUsersDAO ersUsersDAO = new ErsUsersDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String usernameOfUserToDelete = req.getParameter("username");

        if(ersUsersDAO.getUserByUsername(usernameOfUserToDelete) == null) {
            throw new UsernameNotFoundException();
        }

        ersUsersDAO.delete(usernameOfUserToDelete);

    }

    public void setDAO(ErsUsersDAO dao){
        ersUsersDAO = dao;
    }

}
