package com.revature.servlets;

import com.revature.dao.ErsUsersDAO;
import com.revature.model.ErsUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateuserservlet")
public class UpdateUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ErsUsersDAO ersUsersDAO = new ErsUsersDAO();

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

        if(ersUsersDAO.update(user)){
            resp.getWriter().write("Success!\n" + user + " saved to database!");
        } else {
            resp.getWriter().write("Failed");
        }


    }
}
