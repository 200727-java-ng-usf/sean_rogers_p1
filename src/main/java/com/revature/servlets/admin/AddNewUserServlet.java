package com.revature.servlets.admin;

import com.revature.dao.ErsUsersDAO;
import com.revature.exceptions.EmailAlreadyTakenException;
import com.revature.exceptions.NotAuthorizedException;
import com.revature.exceptions.UsernameAlreadyTakenException;
import com.revature.model.ErsUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Adds a new user to the database. Used only by users with role of ADMIN
 */
@WebServlet("/addnewuser")
public class AddNewUserServlet extends HttpServlet {

    ErsUsersDAO ersUsersDAO = new ErsUsersDAO();

    /**
     * Checks to see user role == 1 (admin), if it doesn't, throw NotAuthorizedException(). If it does, create user
     * specified with the request parameters sent from the html form in adminDashboardPage.jsp
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        ErsUser user = (ErsUser)session.getAttribute("user");

        if(user.getUserRoleId() != 1) {
            throw new NotAuthorizedException();
        }

        ErsUser newUser = new ErsUser();
        newUser.setUsername(req.getParameter("username"));
        newUser.setPassword(req.getParameter("password"));
        newUser.setFirstName(req.getParameter("firstName"));
        newUser.setLastName(req.getParameter("lastName"));
        newUser.setEmail(req.getParameter("email"));
        newUser.setUserRoleId(Integer.parseInt(req.getParameter("role")));

        if(ersUsersDAO.getUserByUsername(newUser.getUsername()) != null){
            throw new UsernameAlreadyTakenException();
        }

        if(ersUsersDAO.getUserByEmail(newUser.getEmail()) != null){
            throw new EmailAlreadyTakenException();
        }

        ersUsersDAO.save(newUser);
        req.setAttribute("newUser", newUser);
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
