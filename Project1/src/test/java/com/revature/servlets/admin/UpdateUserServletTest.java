package com.revature.servlets.admin;

import com.revature.dao.ErsUsersDAO;
import com.revature.exceptions.NotAuthorizedException;
import com.revature.exceptions.UsernameNotFoundException;
import com.revature.model.ErsUser;
import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateUserServletTest {

    private ErsUsersDAO mockErsUsersDAO = Mockito.mock(ErsUsersDAO.class);
    UpdateUserServlet sut;

    @Before
    public void setup() {
        sut = new UpdateUserServlet();
        sut.setDAO(mockErsUsersDAO);
    }
    @After
    public void teardown() {
        sut = null;
    }

    // seanrog-1 doesn't exist so mockErsUsersDAO.getUserbyUsername("seanrog-1") should throw UsernameNotFoundException
    @Test (expected = UsernameNotFoundException.class)
    public void updateUserThatDoesntExist() throws ServletException, IOException {

        HttpServletRequest mockRequest = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = Mockito.mock(HttpServletResponse.class);
        HttpSession session = Mockito.mock(HttpSession.class);

        Mockito.when(mockRequest.getParameter("username")).thenReturn("seanrog-1");
        Mockito.when(mockRequest.getParameter("password")).thenReturn("seanrog1password");
        Mockito.when(mockRequest.getParameter("firstName")).thenReturn("sean1");
        Mockito.when(mockRequest.getParameter("lastName")).thenReturn("rogers1");
        Mockito.when(mockRequest.getParameter("email")).thenReturn("seanmikerog1@gmail.com");
        Mockito.when(mockRequest.getParameter("role")).thenReturn("1");

        ErsUser adminUser = new ErsUser();
        adminUser.setId(1);
        adminUser.setUsername("seanrog1");
        adminUser.setPassword("seanrog1password");
        adminUser.setFirstName("sean1");
        adminUser.setLastName("rogers1");
        adminUser.setEmail("seanmikerog1@gmail.com");
        adminUser.setUserRoleId(1);

        Mockito.when(mockRequest.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(adminUser);
        Mockito.when(mockErsUsersDAO.getUserByUsername("seanrog-1")).thenReturn(null);

        sut.doPost(mockRequest,mockResponse);


    }

    @Test
    public void updateUserThatExists() throws ServletException, IOException {

        HttpServletRequest mockRequest = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = Mockito.mock(HttpServletResponse.class);
        HttpSession session = Mockito.mock(HttpSession.class);

        Mockito.when(mockRequest.getParameter("username")).thenReturn("seanrog1");
        Mockito.when(mockRequest.getParameter("password")).thenReturn("seanrog1password");
        Mockito.when(mockRequest.getParameter("firstName")).thenReturn("sean1");
        Mockito.when(mockRequest.getParameter("lastName")).thenReturn("rogers1");
        Mockito.when(mockRequest.getParameter("email")).thenReturn("seanmikerog1@gmail.com");
        Mockito.when(mockRequest.getParameter("role")).thenReturn("1");

        ErsUser adminUser = new ErsUser();
        adminUser.setId(1);
        adminUser.setUsername("seanrog1");
        adminUser.setPassword("seanrog1password");
        adminUser.setFirstName("sean1");
        adminUser.setLastName("rogers1");
        adminUser.setEmail("seanmikerog1@gmail.com");
        adminUser.setUserRoleId(1);

        ErsUser user = new ErsUser();
        user.setId(1);
        user.setUsername("seanrog1");
        user.setPassword("seanrog1password");
        user.setFirstName("sean1");
        user.setLastName("rogers1");
        user.setEmail("seanmikerog1@gmail.com");
        user.setUserRoleId(1);

        Mockito.when(mockRequest.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(adminUser);
        Mockito.when(mockErsUsersDAO.getUserByUsername("seanrog1")).thenReturn(user);

        sut.doPost(mockRequest,mockResponse);

    }

    @Test (expected = NotAuthorizedException.class)
    public void updateExistingUserAsAnUnauthorizedUser() throws ServletException, IOException {

        HttpServletRequest mockRequest = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = Mockito.mock(HttpServletResponse.class);
        HttpSession session = Mockito.mock(HttpSession.class);

        ErsUser targetUser = new ErsUser();
        targetUser.setId(5);
        targetUser.setUsername("seanrog5");
        targetUser.setPassword("seanrog5password");
        targetUser.setFirstName("sean5");
        targetUser.setLastName("rogers5");
        targetUser.setEmail("seanmikerog5@gmail.com");
        targetUser.setUserRoleId(3);

        ErsUser adminUser = new ErsUser();
        adminUser.setId(3);
        adminUser.setUsername("seanrog3");
        adminUser.setPassword("seanrog3password");
        adminUser.setFirstName("sean3");
        adminUser.setLastName("rogers3");
        adminUser.setEmail("seanmikerog3@gmail.com");
        adminUser.setUserRoleId(2);

        Mockito.when(mockRequest.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("user")).thenReturn(adminUser);

        sut.doPost(mockRequest,mockResponse);

    }


}
