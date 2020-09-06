<body>
    <header>Welcome Manger: ${user.firstName}</header>

    <form action="viewallreimbursements" method="GET">
        <label>View All Reimbursements<label>
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
    <form action="deleteuserservlet" method="POST">
        <label>Delete target user</label>
        <input type="text" name="username" placeholder="Delete User" />
        <input type="submit" />
    </form>

</body>