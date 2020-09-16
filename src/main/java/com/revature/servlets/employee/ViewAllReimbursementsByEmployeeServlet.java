package com.revature.servlets.employee;

import com.revature.dao.ErsReimbursementsDAO;
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
 * Gets all reimbursements from DAO with the id of the current session user, then forwards to EmployeeViews/reimbursementsView.jsp
 */
@WebServlet("/viewallreimbursementsbyemployee")
public class ViewAllReimbursementsByEmployeeServlet extends HttpServlet {

    ErsReimbursementsDAO ersReimbursementsDAO = new ErsReimbursementsDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        ErsUser user = (ErsUser)session.getAttribute("user");

        List<ErsReimbursement> ersReimbursements = ersReimbursementsDAO.getAllByAuthorId(user.getId());

        req.setAttribute("reimbursements", ersReimbursements);

        req.getRequestDispatcher("EmployeeViews/reimbursementsView.jsp").forward(req, resp);

    }

    /**
     * sets the data access object. used for the need to mock ErsUsersDAO when unit testing
     * @param dao
     */
    public void setDAO(ErsReimbursementsDAO dao){
        ersReimbursementsDAO = dao;
    }
}
