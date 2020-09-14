package com.revature.servlets.manager;

import com.revature.dao.ErsReimbursementsDAO;
import com.revature.exceptions.NotAuthorizedException;
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

public class ViewAllReimbursementsByStatusServletTest {

    private ErsReimbursementsDAO mockErsReimbursementsDAO = Mockito.mock(ErsReimbursementsDAO.class);
    private ViewAllReimbursementsByStatusServlet sut;

    @Before
    public void setup() {

        sut = new ViewAllReimbursementsByStatusServlet();
        sut.setDAO(mockErsReimbursementsDAO);

    }

    @After
    public void teardown() {
        sut = null;
    }

    @Test(expected = NotAuthorizedException.class)
    public void testUsingUnauthorizedUser() throws ServletException, IOException {

        HttpServletRequest mockRequest = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = Mockito.mock(HttpServletResponse.class);
        HttpSession session = Mockito.mock(HttpSession.class);

        ErsUser manager = new ErsUser();
        manager.setId(5);
        manager.setUsername("seanrog5");
        manager.setPassword("seanrog5password");
        manager.setFirstName("sean5");
        manager.setLastName("rogers5");
        manager.setEmail("seanmikerog5@gmail.com");
        manager.setUserRoleId(3);

        Mockito.when(session.getAttribute("user")).thenReturn(manager);
        Mockito.when(mockRequest.getSession()).thenReturn(session);

        sut.doGet(mockRequest, mockResponse);

    }

    @Test (expected = NotAuthorizedException.class)
    public void testUsingAuthorizedUser() throws ServletException, IOException {

        HttpServletRequest mockRequest = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = Mockito.mock(HttpServletResponse.class);
        HttpSession session = Mockito.mock(HttpSession.class);

        ErsUser manager = new ErsUser();
        manager.setId(5);
        manager.setUsername("seanrog5");
        manager.setPassword("seanrog5password");
        manager.setFirstName("sean5");
        manager.setLastName("rogers5");
        manager.setEmail("seanmikerog5@gmail.com");
        manager.setUserRoleId(3);

        Mockito.when(mockRequest.getParameter("processOptions")).thenReturn("2");
        Mockito.when(mockRequest.getParameter("reimbursementStatus")).thenReturn("2");

        Mockito.when(session.getAttribute("user")).thenReturn(manager);
        Mockito.when(mockRequest.getSession()).thenReturn(session);

        Mockito.when(mockErsReimbursementsDAO.update(3, 0.0, 5,2)).thenReturn(true);

        sut.doGet(mockRequest, mockResponse);

    }

}
