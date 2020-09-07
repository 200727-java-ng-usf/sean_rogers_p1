package com.revature.servlets;

import com.revature.dao.ErsUsersDAO;
import com.revature.model.ErsUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginForwardingServlet")
public class LoginForwardingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ErsUsersDAO ersUsersDAO = new ErsUsersDAO();

        // get the user with matching credentials
        ErsUser user = ersUsersDAO.getUserByUsernameAndPassword(
                req.getParameter("username"),
                req.getParameter("password")
        );

        // validate user
        if(user == null) {
            System.out.println("Username or password incorrect");
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
                req.getRequestDispatcher("employeeDashboardPage.jsp").forward(req, resp);
            }

        }
    }
}
