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

@WebServlet("/updatereimbursement")
public class UpdateReimbursementServlet extends HttpServlet {

    private ErsReimbursementsDAO ersReimbursementsDAO = new ErsReimbursementsDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        ErsReimbursement reimbursement = new ErsReimbursement();

        reimbursement.setReimbId(Long.parseLong(req.getParameter("reimbursementId")));
        reimbursement.setAmount(Double.parseDouble(req.getParameter("amount")));
        reimbursement.setDescription(req.getParameter("description"));
        reimbursement.setReimbTypeId(Integer.parseInt(req.getParameter("reimbursementType")));


        if(ersReimbursementsDAO.update(reimbursement)) {
            resp.setStatus(201);
            resp.sendRedirect("/viewallreimbursementsbyemployee");
        } else {
            resp.getWriter().write("Failed to update reimbursement");
        }

    }

    public void setDAO(ErsReimbursementsDAO dao) {
        ersReimbursementsDAO = dao;
    }
}
