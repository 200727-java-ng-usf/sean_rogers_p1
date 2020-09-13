package com.revature.servlets.manager;

import com.revature.dao.ErsReimbursementsDAO;
import com.revature.exceptions.NotAuthorizedException;
import com.revature.model.ErsReimbursement;
import com.revature.model.ErsUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ViewAllReimbursementsServletTest {

    private ErsReimbursementsDAO mockErsReimbursementsDAO = Mockito.mock(ErsReimbursementsDAO.class);
    private ViewAllReimbursementsServlet sut;

    @Before
    public void setup() {

        sut = new ViewAllReimbursementsServlet();
        sut.setDAO(mockErsReimbursementsDAO);

    }

    @After
    public void teardown() {
        sut = null;
    }

    @Test(expected = NotAuthorizedException.class)
    public void viewAllReimbursementsUsingUnauthorizedAccount() throws ServletException, IOException {

        HttpSession session = Mockito.mock(HttpSession.class);
        HttpServletRequest mockRequest = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = Mockito.mock(HttpServletResponse.class);

        ErsUser manager = new ErsUser();
        manager.setId(5);
        manager.setUsername("seanrog5");
        manager.setPassword("seanrog5password");
        manager.setFirstName("sean5");
        manager.setLastName("rogers5");
        manager.setEmail("seanmikerog5@gmail.com");
        manager.setUserRoleId(3);

        Mockito.when(mockRequest.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(manager);

        sut.doGet(mockRequest, mockResponse);

    }

    @Test
    public void viewAllReimbursementsUsingAuthorizedAccount() throws ServletException, IOException {

        ErsUser manager = new ErsUser();
        manager.setId(3);
        manager.setUsername("seanrog3");
        manager.setPassword("seanrog3password");
        manager.setFirstName("sean3");
        manager.setLastName("rogers3");
        manager.setEmail("seanmikerog3@gmail.com");
        manager.setUserRoleId(2);

        HttpSession session = Mockito.mock(HttpSession.class);
        HttpServletRequest mockRequest = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = Mockito.mock(HttpServletResponse.class);
        RequestDispatcher mockRequestDispatcher = Mockito.mock(RequestDispatcher.class);
        PrintWriter mockPrintWriter = Mockito.mock(PrintWriter.class);

        Mockito.when(mockRequest.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(manager);
        Mockito.when(mockRequest.getRequestDispatcher(Mockito.anyString())).thenReturn(mockRequestDispatcher);
        Mockito.when(mockResponse.getWriter()).thenReturn(mockPrintWriter);
        Mockito.when(mockErsReimbursementsDAO.getAll()).thenReturn(new ArrayList<ErsReimbursement>());

        sut.doGet(mockRequest, mockResponse);

    }

}
