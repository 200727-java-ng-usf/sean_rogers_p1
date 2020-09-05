package com.revature.servlets;

import com.revature.dao.ErsUsersDAO;
import com.revature.model.ErsUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ForwardingMasterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ErsUsersDAO usersDAO = new ErsUsersDAO();
        List<ErsUser> users = usersDAO.getAll();
        System.out.println(users);
        request.getRequestDispatcher(RequestHelper.process(request)).forward(request, response);
//        response.getWriter().write("in get");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(RequestHelper.process(request)).forward(request, response);
//        response.getWriter().write("in post");
    }
}
