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

/**
 * Updates the user specified in request.parameter "username".
 * Sets the ErsUser user properties to the request.parameter "password", "firstname", "lastname", "email", and "role".
 * Passes user into ErsUsersDAO ersUsersDAO.getUserByUsername(user.getUsername) as the argument.
 * If username is not in database, throw UsernameNotFoundException
 *
 * Validation checks for user input is done in the .jsp file
 */
@WebServlet("/updateuserservlet")
public class UpdateUserServlet extends HttpServlet {

    ErsUsersDAO ersUsersDAO = new ErsUsersDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        ErsUser user = new ErsUser();

        user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        user.setEmail(req.getParameter("email"));
        if(req.getParameter("role") == null || req.getParameter("role").equals("")) {
            user.setUserRoleId(0);
        } else {
            user.setUserRoleId(Integer.parseInt(req.getParameter("role")));
        }

        if(ersUsersDAO.getUserByUsername(user.getUsername()) == null) {
            throw new UsernameNotFoundException();
        }

        ersUsersDAO.update(user);


    }

    public void setDAO(ErsUsersDAO dao) {
        ersUsersDAO = dao;
    }

}
