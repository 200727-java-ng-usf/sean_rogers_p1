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

@WebServlet("/viewallreimbursementsbytype")
public class ViewAllReimbursementsByTypeServlet extends HttpServlet {

    ErsReimbursementsDAO ersReimbursementsDAO = new ErsReimbursementsDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        ErsUser user = (ErsUser)session.getAttribute("user");
        if(user.getUserRoleId() != 2) {
            resp.setStatus(403);
            resp.sendError(403);
            throw new NotAuthorizedException();
        }



        List<ErsReimbursement> reimbursements = ersReimbursementsDAO
                .getAllByType(Integer.parseInt(req.getParameter("reimbursementType")));

        req.setAttribute("reimbursements", reimbursements);
        req.setAttribute("size", reimbursements.size());

        req.getRequestDispatcher("ManagerViews/reimbursementsView.jsp").forward(req, resp);



    }

    public void setDAO(ErsReimbursementsDAO dao) {

        ersReimbursementsDAO = dao;

    }

}
