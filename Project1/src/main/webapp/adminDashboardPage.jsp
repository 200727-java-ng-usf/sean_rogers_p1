<body>
    <header>Welcome administrator: ${user.firstName}</header>

    <form action="addnewuser" method="POST">
        <label>Add new user<label>
        <input type="text" name="username" placeholder="Username" required/>
        <input type="text" name="password" placeholder="password" required/>
        <input type="text" name="firstName" placeholder="First Name" required/>
        <input type="text" name="lastName" placeholder="Last Name" required/>
        <input type="text" name="email" placeholder="Email" required/>
        <input type="radio" name="role" value="1" required/>
        <label for="admin">Admin</label><br>
        <input type="radio" name="role" value="2" required/>
        <label for="manager">Manager</label>
        <input type="radio" name="role" value="3" required/>
        <label for="employee">Employee</label>
        <input type="submit" />
    </form><br>
    <form action="updateuserservlet" method="POST">
        <label>Update target user</label>
        <input type="text" name="username" placeholder="Username" required/>
                <input type="text" name="password" placeholder="password" />
                <input type="text" name="firstName" placeholder="First Name" />
                <input type="text" name="lastName" placeholder="Last Name" />
                <input type="text" name="email" placeholder="Email" />
                <input type="radio" name="role" value="1" />
                <label for="admin">Admin</label><br>
                <input type="radio" name="role" value="2" />
                <label for="manager">Manager</label>
                <input type="radio" name="role" value="3" />
                <label for="employee">Employee</label>
                <input type="submit" />
    </form><br>
    <form action="DeleteUserServlet" method="POST">
        <label>Delete target user</label>
        <input type="text" name="username" placeholder="Delete User" />
        <input type="submit" />
    </form>

</body>