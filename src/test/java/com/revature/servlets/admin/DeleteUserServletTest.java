package com.revature.servlets.admin;

import com.revature.dao.ErsUsersDAO;
import com.revature.exceptions.NotAuthorizedException;
import com.revature.exceptions.UsernameNotFoundException;
import com.revature.model.ErsUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteUserServletTest {

    ErsUsersDAO mockErsUsersDAO = Mockito.mock(ErsUsersDAO.class);
    DeleteUserServlet sut;

    @Before
    public void setup() {
        sut = new DeleteUserServlet();
        sut.setDAO(mockErsUsersDAO);
    }

    @After
    public void teardown() {
        sut = null;
    }

    @Test (expected = UsernameNotFoundException.class)
    public void deleteUserThatDoesntExist() throws ServletException, IOException {
        HttpServletRequest mockRequest = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = Mockito.mock(HttpServletResponse.class);
        RequestDispatcher mockRequestDispatcher = Mockito.mock(RequestDispatcher.class);
        HttpSession session = Mockito.mock(HttpSession.class);

        Mockito.when(mockRequest.getParameter("username")).thenReturn("seanrog-1");
        Mockito.when(mockRequest.getParameter("password")).thenReturn("seanrog1password");
        Mockito.when(mockRequest.getParameter("firstname")).thenReturn("sean1");
        Mockito.when(mockRequest.getParameter("lastname")).thenReturn("rogers1");
        Mockito.when(mockRequest.getParameter("email")).thenReturn("seanrog1@gmail.com");
        Mockito.when(mockRequest.getParameter("role")).thenReturn("1");

        ErsUser adminUser = new ErsUser();
        adminUser.setId(1);
        adminUser.setUsername("seanrog1");
        adminUser.setPassword("seanrog1password");
        adminUser.setFirstName("sean1");
        adminUser.setLastName("rogers1");
        adminUser.setEmail("seanmikerog1@gmail.com");
        adminUser.setUserRoleId(1);

        Mockito.when(session.getAttribute("user")).thenReturn(adminUser);
        Mockito.when(mockRequest.getSession()).thenReturn(session);

        Mockito.when(mockRequest.getRequestDispatcher(Mockito.anyString())).thenReturn(mockRequestDispatcher);
        Mockito.when(mockErsUsersDAO.getUserByUsername("seanrog-1")).thenReturn(null);

        sut.doPost(mockRequest, mockResponse);

    }

    @Test
    public void deleteExistingUser() throws ServletException, IOException {
        HttpServletRequest mockRequest = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = Mockito.mock(HttpServletResponse.class);
        RequestDispatcher mockRequestDispatcher = Mockito.mock(RequestDispatcher.class);
        HttpSession session = Mockito.mock(HttpSession.class);

        Mockito.when(mockRequest.getParameter("username")).thenReturn("seanrog1");
        Mockito.when(mockRequest.getParameter("password")).thenReturn("seanrog1password");
        Mockito.when(mockRequest.getParameter("firstname")).thenReturn("sean1");
        Mockito.when(mockRequest.getParameter("lastname")).thenReturn("rogers1");
        Mockito.when(mockRequest.getParameter("email")).thenReturn("seanrog1@gmail.com");
        Mockito.when(mockRequest.getParameter("role")).thenReturn("1");

        ErsUser adminUser = new ErsUser();
        adminUser.setId(1);
        adminUser.setUsername("seanrog1");
        adminUser.setPassword("seanrog1password");
        adminUser.setFirstName("sean1");
        adminUser.setLastName("rogers1");
        adminUser.setEmail("seanmikerog1@gmail.com");
        adminUser.setUserRoleId(1);

        Mockito.when(session.getAttribute("user")).thenReturn(adminUser);
        Mockito.when(mockRequest.getSession()).thenReturn(session);

        ErsUser targetUser = new ErsUser();

        targetUser.setId(1);
        targetUser.setUsername("seanrog1");
        targetUser.setPassword("seanrog1password");
        targetUser.setFirstName("sean1");
        targetUser.setLastName("rog1");
        targetUser.setEmail("seanmikerog1@gmail.com");
        targetUser.setUserRoleId(1);

        Mockito.when(mockErsUsersDAO.getUserByUsername("seanrog1")).thenReturn(targetUser);
        Mockito.when(mockRequest.getRequestDispatcher(Mockito.anyString())).thenReturn(mockRequestDispatcher);

        sut.doPost(mockRequest, mockResponse);

    }

    @Test (expected = NotAuthorizedException.class)
    public void deleteExistingUserAsAnUnauthorizedUser() throws ServletException, IOException {
        HttpServletRequest mockRequest = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = Mockito.mock(HttpServletResponse.class);
        RequestDispatcher mockRequestDispatcher = Mockito.mock(RequestDispatcher.class);
        HttpSession session = Mockito.mock(HttpSession.class);

        Mockito.when(mockRequest.getParameter("username")).thenReturn("seanrog1");
        Mockito.when(mockRequest.getParameter("password")).thenReturn("seanrog1password");
        Mockito.when(mockRequest.getParameter("firstname")).thenReturn("sean1");
        Mockito.when(mockRequest.getParameter("lastname")).thenReturn("rogers1");
        Mockito.when(mockRequest.getParameter("email")).thenReturn("seanrog1@gmail.com");
        Mockito.when(mockRequest.getParameter("role")).thenReturn("1");

        ErsUser adminUser = new ErsUser();
        adminUser.setId(3);
        adminUser.setUsername("seanrog3");
        adminUser.setPassword("seanrog3password");
        adminUser.setFirstName("sean3");
        adminUser.setLastName("rogers3");
        adminUser.setEmail("seanmikerog3@gmail.com");
        adminUser.setUserRoleId(2);

        Mockito.when(session.getAttribute("user")).thenReturn(adminUser);
        Mockito.when(mockRequest.getSession()).thenReturn(session);

        ErsUser targetUser = new ErsUser();

        targetUser.setId(1);
        targetUser.setUsername("seanrog1");
        targetUser.setPassword("seanrog1password");
        targetUser.setFirstName("sean1");
        targetUser.setLastName("rog1");
        targetUser.setEmail("seanmikerog1@gmail.com");
        targetUser.setUserRoleId(1);

        Mockito.when(mockErsUsersDAO.getUserByUsername("seanrog1")).thenReturn(targetUser);
        Mockito.when(mockRequest.getRequestDispatcher(Mockito.anyString())).thenReturn(mockRequestDispatcher);

        sut.doPost(mockRequest, mockResponse);

    }

}
