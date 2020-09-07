package com.revature.servlets.manager;

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
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/viewallreimbursements")
public class ViewAllReimbursementsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        ErsUser user = (ErsUser) session.getAttribute("user");
        if(user.getUserRoleId() != 2) {

            resp.setStatus(403);

        } else {
            ErsReimbursementsDAO ersReimbursementsDAO = new ErsReimbursementsDAO();

            List<ErsReimbursement> reimbursements = ersReimbursementsDAO.getAll();

            PrintWriter out = resp.getWriter();

            req.setAttribute("reimbursements", reimbursements);
            req.setAttribute("size", reimbursements.size());

            req.getRequestDispatcher("ManagerViews/reimbursementsView.jsp").forward(req, resp);

        /*for(ErsReimbursement reimbursement : reimbursements) {
            out.write(reimbursement + "\n");
        }*/
        }

    }
}
