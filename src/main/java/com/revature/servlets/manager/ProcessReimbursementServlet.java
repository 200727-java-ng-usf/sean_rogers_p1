package com.revature.servlets.manager;

import com.revature.dao.ErsReimbursementsDAO;
import com.revature.exceptions.NotAuthorizedException;
import com.revature.model.ErsReimbursement;
import com.revature.model.ErsUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


/**
 * The manager processes the reimbursement that was submitted by an employee.
 */
@WebServlet("/processreimbursement")
public class ProcessReimbursementServlet extends HttpServlet {

    ErsReimbursementsDAO ersReimbursementsDAO = new ErsReimbursementsDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        ErsUser user = (ErsUser) session.getAttribute("user");
        if(user.getUserRoleId() != 2) {

            resp.setStatus(403);
            throw new NotAuthorizedException();

        } else {

            double receipt = 0.0;

            if (req.getParameter("processOption").equals("2")) {
                receipt = Double.parseDouble(req.getParameter("receipt"));
            }

            if (ersReimbursementsDAO.update(Integer.parseInt(req.getParameter("reimbursementId")),
                    receipt,
                    user.getId(),
                    Integer.parseInt(req.getParameter("processOption")))) {
                resp.setStatus(201);
            } else {
                resp.setStatus(500);
            }

            resp.sendRedirect("/viewallreimbursements");

        }

    }

    /**
     * sets the data access object. used for the need to mock ErsUsersDAO when unit testing
     * @param dao
     */
    public void setDAO(ErsReimbursementsDAO dao) {
        ersReimbursementsDAO = dao;
    }

}
