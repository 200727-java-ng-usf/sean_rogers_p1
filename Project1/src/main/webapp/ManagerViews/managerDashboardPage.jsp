<jsp:include page="../header.jsp" />
    <header>Welcome Manger: ${user.firstName}</header>

    <form action="viewallreimbursements" method="GET">
        <label>View All Reimbursements<label>
        <input type="submit" />
    </form><br>

<jsp:include page="../footer.jsp" />