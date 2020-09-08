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

@WebServlet("/submitnewreimbursement")
public class SubmitNewReimbursementServlet extends HttpServlet {

    ErsReimbursementsDAO ersReimbursementsDAO = new ErsReimbursementsDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        ErsUser user = (ErsUser)session.getAttribute("user");
        ErsReimbursement reimbursement = new ErsReimbursement();

        reimbursement.setAmount(Double.parseDouble(req.getParameter("amount")));
        reimbursement.setDescription(req.getParameter("description"));
        reimbursement.setAuthorId(user.getId());
        reimbursement.setReimbStatusId(1);
        reimbursement.setReimbTypeId(Integer.parseInt(req.getParameter("reimbursementType")));

        if(ersReimbursementsDAO.save(reimbursement)) {
            resp.setStatus(201);
            resp.sendRedirect("/project1/viewallreimbursementsbyemployee");
        } else {
            resp.getWriter().write("Error saving reimbursement to database");
        }

    }
}