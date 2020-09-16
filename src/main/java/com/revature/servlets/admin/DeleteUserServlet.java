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
 * DOES NOT DELETE USERS. IT MARKS THEM AS INACTIVE. Ensures the user has a role of ADMIN.
 */
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
        req.setAttribute("message", usernameOfUserToDelete + " has been deleted");
        req.getRequestDispatcher("adminDashboardPage.jsp").forward(req, resp);

    }

    /**
     * sets the data access object. used for the need to mock ErsUsersDAO when unit testing
     * @param dao
     */
    public void setDAO(ErsUsersDAO dao){
        ersUsersDAO = dao;
    }

}
