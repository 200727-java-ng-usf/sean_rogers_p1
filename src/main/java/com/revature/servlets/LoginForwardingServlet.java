package com.revature.servlets;

import com.revature.dao.ErsUsersDAO;
import com.revature.exceptions.UsernamePasswordMismatchException;
import com.revature.model.ErsUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * logs in the user if proper credentials are provided. Redirects user to the appropriate view depending on user ROLE
 */
@WebServlet("/LoginForwardingServlet")
public class LoginForwardingServlet extends HttpServlet {

    ErsUsersDAO ersUsersDAO = new ErsUsersDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        // get the user with matching credentials
        ErsUser user = ersUsersDAO.getUserByUsernameAndPassword(
                req.getParameter("username"),
                req.getParameter("password")
        );

        // validate user
        if(user == null) {
            System.out.println("Username or password incorrect");
            throw new UsernamePasswordMismatchException();
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            //Why is this here?
            req.setAttribute("user", user);

            // if user is valid, forward to appropriate dashboard
            if(user.getUserRoleId() == 1) {

                req.getRequestDispatcher("adminDashboardPage.jsp").forward(req, resp);
            }
            if(user.getUserRoleId() == 2) {
                req.getRequestDispatcher("ManagerViews/managerDashboardPage.jsp").forward(req, resp);
            }
            if(user.getUserRoleId() == 3) {
                req.getRequestDispatcher("EmployeeViews/employeeDashboardPage.jsp").forward(req, resp);
            }

        }
    }

    /**
     * sets the data access object. used for the need to mock ErsUsersDAO when unit testing
     * @param dao
     */
    public void setDAO(ErsUsersDAO dao) {
        ersUsersDAO = dao;
    }

}
