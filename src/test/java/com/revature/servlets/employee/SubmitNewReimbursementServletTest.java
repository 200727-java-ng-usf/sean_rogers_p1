package com.revature.servlets.employee;

import com.revature.dao.ErsReimbursementsDAO;
import com.revature.model.ErsUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SubmitNewReimbursementServletTest {

    ErsReimbursementsDAO mockErsReimbursementsDAO = Mockito.mock(ErsReimbursementsDAO.class);
    SubmitNewReimbursementServlet sut;

    @Before
    public void setup() {
        sut = new SubmitNewReimbursementServlet();
        sut.setDAO(mockErsReimbursementsDAO);
    }

    @After
    public void teardown() {
        sut = null;
    }

    @Test
    public void addSubmitNewReimbursement() throws ServletException, IOException {

        ErsUser user = new ErsUser();
        user.setId(5);
        user.setUsername("seanrog5");
        user.setPassword("seanrog5password");
        user.setFirstName("sean5");
        user.setLastName("rogers5");
        user.setEmail("seanmikerog5@gmail.com");
        user.setUserRoleId(3);

        HttpServletRequest mockRequest = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = Mockito.mock(HttpServletResponse.class);
        HttpSession session = Mockito.mock(HttpSession.class);

        Mockito.when(mockRequest.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(mockRequest.getParameter("amount")).thenReturn("23.13");
        Mockito.when(mockRequest.getParameter("description")).thenReturn("description5");
        Mockito.when(mockRequest.getParameter("reimbursementType")).thenReturn("2");
        Mockito.when(mockErsReimbursementsDAO.save(Mockito.any())).thenReturn(true);

        sut.doPost(mockRequest, mockResponse);

    }

}
