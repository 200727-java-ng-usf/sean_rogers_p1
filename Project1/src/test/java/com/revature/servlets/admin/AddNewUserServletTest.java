package com.revature.servlets.admin;

import com.revature.dao.ErsUsersDAO;
import com.revature.exceptions.EmailAlreadyTakenException;
import com.revature.exceptions.UsernameAlreadyTakenException;
import com.revature.model.ErsUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddNewUserServletTest {

    private ErsUsersDAO mockErsUsersDAO = Mockito.mock(ErsUsersDAO.class);
    AddNewUserServlet sut;

    @Before
    public void setup() {
        sut = new AddNewUserServlet();
        sut.setDAO(mockErsUsersDAO);

    }

    @After
    public void teardown() {
        sut = null;
    }

    @Test(expected = UsernameAlreadyTakenException.class)
    public void addNewUserWithUsernameThatAlreadyExists() {
        HttpServletRequest mockRequest = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = Mockito.mock(HttpServletResponse.class);

        Mockito.when(mockRequest.getParameter("username")).thenReturn("seanrog1");
        Mockito.when(mockRequest.getParameter("password")).thenReturn("seanrog1password");
        Mockito.when(mockRequest.getParameter("firstname")).thenReturn("sean1");
        Mockito.when(mockRequest.getParameter("lastname")).thenReturn("rogers1");
        Mockito.when(mockRequest.getParameter("email")).thenReturn("seanmikerog1@gmail.com");
        Mockito.when(mockRequest.getParameter("role")).thenReturn("1");

        ErsUser newUser = new ErsUser();
        newUser.setId(1);
        newUser.setUsername("seanrog1");
        newUser.setPassword("seanrog1password");
        newUser.setFirstName("sean1");
        newUser.setLastName("rogers1");
        newUser.setEmail("seanmikerog1@gmail.com");
        newUser.setUserRoleId(1);

        Mockito.when(mockErsUsersDAO.getUserByUsername("seanrog1")).thenReturn(newUser);

        try{
            sut.doPost(mockRequest, mockResponse);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test(expected = EmailAlreadyTakenException.class)
    public void addNewUserWithEmailThatAlreadyExists() {
        HttpServletRequest mockRequest = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = Mockito.mock(HttpServletResponse.class);

        Mockito.when(mockRequest.getParameter("username")).thenReturn("seanrog1");
        Mockito.when(mockRequest.getParameter("password")).thenReturn("seanrog1password");
        Mockito.when(mockRequest.getParameter("firstname")).thenReturn("sean1");
        Mockito.when(mockRequest.getParameter("lastname")).thenReturn("rogers1");
        Mockito.when(mockRequest.getParameter("email")).thenReturn("seanmikerog1@gmail.com");
        Mockito.when(mockRequest.getParameter("role")).thenReturn("1");

        ErsUser newUser = new ErsUser();
        newUser.setId(1);
        newUser.setUsername("seanrog1");
        newUser.setPassword("seanrog1password");
        newUser.setFirstName("sean1");
        newUser.setLastName("rogers1");
        newUser.setEmail("seanmikerog1@gmail.com");
        newUser.setUserRoleId(1);

        Mockito.when(mockErsUsersDAO.getUserByUsername("seanrog-1")).thenReturn(null);

        Mockito.when(mockErsUsersDAO.getUserByEmail("seanmikerog1@gmail.com")).thenReturn(newUser);

        try{
            sut.doPost(mockRequest, mockResponse);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
