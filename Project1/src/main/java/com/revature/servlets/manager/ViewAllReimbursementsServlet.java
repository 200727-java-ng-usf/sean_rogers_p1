package com.revature.servlets.manager;

import com.revature.dao.ErsReimbursementsDAO;
import com.revature.model.ErsReimbursement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/viewallreimbursements")
public class ViewAllReimbursementsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ErsReimbursementsDAO ersReimbursementsDAO = new ErsReimbursementsDAO();

        List<ErsReimbursement> reimbursements = ersReimbursementsDAO.getAll();

        PrintWriter out = resp.getWriter();

        for(ErsReimbursement reimbursement : reimbursements) {
            out.write(reimbursement + "\n");
        }

    }
}
