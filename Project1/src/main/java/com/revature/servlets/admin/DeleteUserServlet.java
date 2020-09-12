package com.revature.servlets.admin;

import com.revature.dao.ErsUsersDAO;
import com.revature.exceptions.NotAuthorizedException;
import com.revature.exceptions.UsernameNotFoundException;
import com.revature.model.ErsUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/deleteuserservlet")
public class DeleteUserServlet extends HttpServlet {

    ErsUsersDAO ersUsersDAO = new ErsUsersDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        ErsUser user = (ErsUser)session.getAttribute("user");

        if(user.getUserRoleId() != 1) {
            throw new NotAuthorizedException();
        }

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
