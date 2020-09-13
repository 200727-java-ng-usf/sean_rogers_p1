package com.revature.servlets;

import com.revature.dao.ErsReimbursementsDAO;
import com.revature.dao.ErsUsersDAO;
import com.revature.model.ErsReimbursement;
import com.revature.model.ErsUser;
import com.revature.servlets.employee.ViewAllReimbursementsByEmployeeServlet;
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
import java.util.ArrayList;

public class LoginForwardingServletTest {

    ErsUsersDAO mockErsUsersDAO = Mockito.mock(ErsUsersDAO.class);
    LoginForwardingServlet sut;

    @Before
    public void setup() {
        sut = new LoginForwardingServlet();
        sut.setDAO(mockErsUsersDAO);
    }

    @After
    public void teardown() {
        sut = null;
    }

    @Test
    public void loginWithEmployee() throws ServletException, IOException {

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
        RequestDispatcher mockRequestDispatcher = Mockito.mock(RequestDispatcher.class);

        Mockito.when(mockRequest.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(mockRequest.getParameter("username")).thenReturn("seanrog5");
        Mockito.when(mockRequest.getParameter("password")).thenReturn("seanrog5password");
        Mockito.when(mockErsUsersDAO.getUserByUsernameAndPassword("seanrog5", "seanrog5password")).thenReturn(user);
        Mockito.when(mockRequest.getRequestDispatcher(Mockito.anyString())).thenReturn(mockRequestDispatcher);

        sut.doPost(mockRequest, mockResponse);

    }

    @Test
    public void loginWithManager() throws ServletException, IOException {

        ErsUser user = new ErsUser();
        user.setId(3);
        user.setUsername("seanrog3");
        user.setPassword("seanrog3password");
        user.setFirstName("sean3");
        user.setLastName("rogers3");
        user.setEmail("seanmikerog3@gmail.com");
        user.setUserRoleId(2);

        HttpServletRequest mockRequest = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = Mockito.mock(HttpServletResponse.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        RequestDispatcher mockRequestDispatcher = Mockito.mock(RequestDispatcher.class);

        Mockito.when(mockRequest.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(mockRequest.getParameter("username")).thenReturn("seanrog3");
        Mockito.when(mockRequest.getParameter("password")).thenReturn("seanrog3password");
        Mockito.when(mockErsUsersDAO.getUserByUsernameAndPassword("seanrog3", "seanrog3password")).thenReturn(user);
        Mockito.when(mockRequest.getRequestDispatcher(Mockito.anyString())).thenReturn(mockRequestDispatcher);

        sut.doPost(mockRequest, mockResponse);

    }

    @Test
    public void loginWithAdmin() throws ServletException, IOException {

        ErsUser user = new ErsUser();
        user.setId(1);
        user.setUsername("seanrog1");
        user.setPassword("seanrog1password");
        user.setFirstName("sean1");
        user.setLastName("rogers1");
        user.setEmail("seanmikerog1@gmail.com");
        user.setUserRoleId(1);

        HttpServletRequest mockRequest = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = Mockito.mock(HttpServletResponse.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        RequestDispatcher mockRequestDispatcher = Mockito.mock(RequestDispatcher.class);

        Mockito.when(mockRequest.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(user);
        Mockito.when(mockRequest.getParameter("username")).thenReturn("seanrog1");
        Mockito.when(mockRequest.getParameter("password")).thenReturn("seanrog1password");
        Mockito.when(mockErsUsersDAO.getUserByUsernameAndPassword("seanrog1", "seanrog1password")).thenReturn(user);
        Mockito.when(mockRequest.getRequestDispatcher(Mockito.anyString())).thenReturn(mockRequestDispatcher);

        sut.doPost(mockRequest, mockResponse);

    }

}
