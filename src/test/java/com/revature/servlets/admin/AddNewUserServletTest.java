package com.revature.servlets.admin;

import com.revature.dao.ErsUsersDAO;
import com.revature.exceptions.EmailAlreadyTakenException;
import com.revature.exceptions.NotAuthorizedException;
import com.revature.exceptions.UsernameAlreadyTakenException;
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
        HttpSession session = Mockito.mock(HttpSession.class);
        RequestDispatcher mockRequestDispatcher = Mockito.mock(RequestDispatcher.class);

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
        HttpSession session = Mockito.mock(HttpSession.class);

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

    @Test (expected = NotAuthorizedException.class)
    public void addNewUserUsingAnUnauthorizedUser() throws ServletException, IOException {

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        HttpSession session = Mockito.mock(HttpSession.class);

        ErsUser adminUser = new ErsUser();
        adminUser.setId(2);
        adminUser.setUsername("seanrog3");
        adminUser.setPassword("seanrog3password");
        adminUser.setFirstName("sean3");
        adminUser.setLastName("rogers3");
        adminUser.setEmail("seanmikerog3@gmail.com");
        adminUser.setUserRoleId(2);

        Mockito.when(session.getAttribute("user")).thenReturn(adminUser);
        Mockito.when(request.getSession()).thenReturn(session);

        sut.doPost(request, response);

    }

    //Add New User That Doesnt Exist And Using An Authorized User To Add New User
    @Test
    public void addNewUserThatDoesntExistAndUsingAnAuthorizedUserToAddNewUser() throws ServletException, IOException {

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        RequestDispatcher mockRequestDispatcher = Mockito.mock(RequestDispatcher.class);

        ErsUser newUser = new ErsUser();
        newUser.setId(999);
        newUser.setUsername("nonExistentSeanrog1");
        newUser.setPassword("nonExistentSeanrog1password");
        newUser.setFirstName("nonExistentSean1");
        newUser.setLastName("nonExistentRogers1");
        newUser.setEmail("nonExistentSeanrog1@gmail.com");
        newUser.setUserRoleId(3);

        Mockito.when(request.getParameter("username")).thenReturn("nonExistentSeanrog1");
        Mockito.when(request.getParameter("password")).thenReturn("nonExistentSeanrog1password");
        Mockito.when(request.getParameter("firstname")).thenReturn("nonExistentSean1");
        Mockito.when(request.getParameter("lastname")).thenReturn("nonExistentRogers1");
        Mockito.when(request.getParameter("email")).thenReturn("nonExistentSeanrog1");
        Mockito.when(request.getParameter("role")).thenReturn("3");

        ErsUser adminUser = new ErsUser();
        adminUser.setId(1);
        adminUser.setUsername("seanrog1");
        adminUser.setPassword("seanrog1password");
        adminUser.setFirstName("sean1");
        adminUser.setLastName("rogers1");
        adminUser.setEmail("seanmikerog1@gmail.com");
        adminUser.setUserRoleId(1);

        Mockito.when(session.getAttribute("user")).thenReturn(adminUser);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getRequestDispatcher(Mockito.anyString())).thenReturn(mockRequestDispatcher);

        sut.doPost(request, response);

    }



}
