package com.revature.servlets.admin;

import com.revature.dao.ErsUsersDAO;
import com.revature.exceptions.UsernameNotFoundException;
import com.revature.model.ErsUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

        Mockito.when(mockRequest.getParameter("username")).thenReturn("seanrog-1");
        Mockito.when(mockRequest.getParameter("password")).thenReturn("seanrog1password");
        Mockito.when(mockRequest.getParameter("firstname")).thenReturn("sean1");
        Mockito.when(mockRequest.getParameter("lastname")).thenReturn("rogers1");
        Mockito.when(mockRequest.getParameter("email")).thenReturn("seanrog1@gmail.com");
        Mockito.when(mockRequest.getParameter("role")).thenReturn("1");

        Mockito.when(mockErsUsersDAO.getUserByUsername("seanrog-1")).thenReturn(null);

        sut.doPost(mockRequest, mockResponse);

    }

    @Test
    public void deleteExistingUser() throws ServletException, IOException {
        HttpServletRequest mockRequest = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = Mockito.mock(HttpServletResponse.class);

        Mockito.when(mockRequest.getParameter("username")).thenReturn("seanrog1");
        Mockito.when(mockRequest.getParameter("password")).thenReturn("seanrog1password");
        Mockito.when(mockRequest.getParameter("firstname")).thenReturn("sean1");
        Mockito.when(mockRequest.getParameter("lastname")).thenReturn("rogers1");
        Mockito.when(mockRequest.getParameter("email")).thenReturn("seanrog1@gmail.com");
        Mockito.when(mockRequest.getParameter("role")).thenReturn("1");

        ErsUser user = new ErsUser();

        user.setId(1);
        user.setUsername("seanrog1");
        user.setPassword("seanrog1password");
        user.setFirstName("sean1");
        user.setLastName("rog1");
        user.setEmail("seanmikerog1@gmail.com");
        user.setUserRoleId(1);

        Mockito.when(mockErsUsersDAO.getUserByUsername("seanrog1")).thenReturn(user);

        sut.doPost(mockRequest, mockResponse);

    }

}
