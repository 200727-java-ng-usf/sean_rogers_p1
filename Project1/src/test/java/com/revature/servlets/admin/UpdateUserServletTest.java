package com.revature.servlets.admin;

import com.revature.dao.ErsUsersDAO;
import com.revature.exceptions.UsernameNotFoundException;
import com.revature.model.ErsUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

        Mockito.when(mockRequest.getParameter("username")).thenReturn("seanrog-1");
        Mockito.when(mockRequest.getParameter("password")).thenReturn("seanrog1password");
        Mockito.when(mockRequest.getParameter("firstName")).thenReturn("sean1");
        Mockito.when(mockRequest.getParameter("lastName")).thenReturn("rogers1");
        Mockito.when(mockRequest.getParameter("email")).thenReturn("seanmikerog1@gmail.com");
        Mockito.when(mockRequest.getParameter("role")).thenReturn("1");

        Mockito.when(mockErsUsersDAO.getUserByUsername("seanrog-1")).thenReturn(null);

        sut.doPost(mockRequest,mockResponse);


    }

    @Test
    public void updateUserThatExists() throws ServletException, IOException {

        HttpServletRequest mockRequest = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = Mockito.mock(HttpServletResponse.class);

        Mockito.when(mockRequest.getParameter("username")).thenReturn("seanrog1");
        Mockito.when(mockRequest.getParameter("password")).thenReturn("seanrog1password");
        Mockito.when(mockRequest.getParameter("firstName")).thenReturn("sean1");
        Mockito.when(mockRequest.getParameter("lastName")).thenReturn("rogers1");
        Mockito.when(mockRequest.getParameter("email")).thenReturn("seanmikerog1@gmail.com");
        Mockito.when(mockRequest.getParameter("role")).thenReturn("1");

        ErsUser user = new ErsUser();
        user.setId(1);
        user.setUsername("seanrog1");
        user.setPassword("seanrog1password");
        user.setFirstName("sean1");
        user.setLastName("rogers1");
        user.setEmail("seanmikerog1@gmail.com");
        user.setUserRoleId(1);


        Mockito.when(mockErsUsersDAO.getUserByUsername("seanrog1")).thenReturn(user);

        sut.doPost(mockRequest,mockResponse);


    }

}
