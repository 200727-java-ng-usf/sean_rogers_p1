<jsp:include page="./header.jsp" />

    <form class="form-group" action="LoginForwardingServlet" method="POST">
        <label>Enter Username</label>
        <input type="text" name="username" placeholder="Username" required /><br>
        <label>Enter Password</label>
        <input type="password" name="password" required /> <br>
        <input type="submit" />
    </form>

<jsp:include page="./footer.jsp" />
