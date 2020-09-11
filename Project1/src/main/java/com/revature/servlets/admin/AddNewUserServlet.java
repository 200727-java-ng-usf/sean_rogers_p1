package com.revature.servlets.admin;

import com.revature.dao.ErsUsersDAO;
import com.revature.exceptions.EmailAlreadyTakenException;
import com.revature.exceptions.UsernameAlreadyTakenException;
import com.revature.model.ErsUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addnewuser")
public class AddNewUserServlet extends HttpServlet {

    ErsUsersDAO ersUsersDAO = new ErsUsersDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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

    }

    public void setDAO(ErsUsersDAO dao) {
        ersUsersDAO = dao;
    }

}
