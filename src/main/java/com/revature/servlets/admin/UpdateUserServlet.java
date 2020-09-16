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

        HttpSession session = req.getSession();
        ErsUser user = (ErsUser)session.getAttribute("user");

        if(user.getUserRoleId() != 1) {
            throw new NotAuthorizedException();
        }

        ErsUser userToUpdate = new ErsUser();

        userToUpdate.setUsername(req.getParameter("username"));
        userToUpdate.setPassword(req.getParameter("password"));
        userToUpdate.setFirstName(req.getParameter("firstName"));
        userToUpdate.setLastName(req.getParameter("lastName"));
        userToUpdate.setEmail(req.getParameter("email"));

        if(req.getParameter("role") == null || req.getParameter("role").equals("")) {
            userToUpdate.setUserRoleId(0);
        } else {
            userToUpdate.setUserRoleId(Integer.parseInt(req.getParameter("role")));
        }

        if(ersUsersDAO.getUserByUsername(userToUpdate.getUsername()) == null) {
            throw new UsernameNotFoundException();
        }

        ersUsersDAO.update(userToUpdate);
        req.setAttribute("message", userToUpdate.getUsername() + " has been updated");
        req.getRequestDispatcher("adminDashboardPage.jsp").forward(req, resp);


    }

    /**
     * sets the data access object. used for the need to mock ErsUsersDAO when unit testing
     * @param dao
     */
    public void setDAO(ErsUsersDAO dao) {
        ersUsersDAO = dao;
    }

}
