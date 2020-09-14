<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*"%>
<%@page import="com.revature.model.ErsUser"%>

<jsp:include page="./header.jsp" />
    <header>Welcome administrator: ${user.firstName}</header>
    <div class="container">
        <%

            ErsUser newUser = (ErsUser)request.getAttribute("newUser");
            String message = (String)request.getAttribute("message");

            if(request.getAttribute("newUser") != null) {
                %> <%=newUser.getUsername()%> has been added <%
            }

            if(request.getAttribute("message") != null) {
                %> <%=message%> <%
            }
        %>
    </div>
    <form class="form-group" action="addnewuser" method="POST">
        <label>Add new user<label><br>
        <input type="text" name="username" placeholder="Username" required/><br>
        <input type="text" name="password" placeholder="password" required/><br>
        <input type="text" name="firstName" placeholder="First Name" required/><br>
        <input type="text" name="lastName" placeholder="Last Name" required/><br>
        <input type="text" name="email" placeholder="Email" required/><br>
        <input type="radio" name="role" value="1" required/>
        <label for="admin">Admin</label><br>
        <input type="radio" name="role" value="2" required/>
        <label for="manager">Manager</label><br>
        <input type="radio" name="role" value="3" required/>
        <label for="employee">Employee</label><br>
        <input type="submit" />
    </form><br><hr><br>
    <form class="form-group" action="updateuserservlet" method="POST">
        <label>Update target user</label><br>
        <input type="text" name="username" placeholder="Username" required/><br>
        <input type="text" name="password" placeholder="password" /><br>
        <input type="text" name="firstName" placeholder="First Name" /><br>
        <input type="text" name="lastName" placeholder="Last Name" /><br>
        <input type="text" name="email" placeholder="Email" /><br>
        <input type="radio" name="role" value="1" />
        <label for="admin">Admin</label>
        <input type="radio" name="role" value="2" />
        <label for="manager">Manager</label>
        <input type="radio" name="role" value="3" />
        <label for="employee">Employee</label><br>
        <input type="submit" />
    </form><br><hr><br>
    <form class="form-group" action="deleteuserservlet" method="POST">
        <label>Delete target user</label><br>
        <input type="text" name="username" placeholder="Delete User" /><br>
        <input type="submit" /><br>
    </form>
    <jsp:include page="./footer.jsp" />
