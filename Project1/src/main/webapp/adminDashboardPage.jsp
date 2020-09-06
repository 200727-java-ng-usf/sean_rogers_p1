<body>
    <header>Welcome administrator: ${user.firstName}</header>

    <form action="AddNewUserServlet" method="POST">
        <label>Add new user<label>
        <input type="text" name="username" placeholder="New User" />
        <input type="submit" />
    </form>
    <form action="UpdateUserServlet" method="POST">
        <label>Update target user</label>
        <input type="text" name="username" placeholder="Update User" />
        <input type="submit" />
    </form>
    <form action="DeleteUserServlet" method="POST">
        <label>Delete target user</label>
        <input type="text" name="username" placeholder="Delete User" />
        <input type="submit" />
    </form>

</body>