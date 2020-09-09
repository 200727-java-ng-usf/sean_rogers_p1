<jsp:include page="../header.jsp" />

<body>
    <div class="container">
        <header>Welcome Employee: ${user.firstName}</header>
    </div>

    <div class="container">
        <header>View All Reimbursements</header>
        <form action="viewallreimbursementsbyemployee" method="GET">

            <input type="submit" value="View" class="btn btn-primary"/>
        </form>
    </div>
    <div class="container">
        <header>Create New Reimbursement</header>
        <form action="submitnewreimbursement" method="POST">

            <input type="text" name="amount" placeholder="Amount" required /><br>
            <input type="text" name="description" placeholder="Description" required /><br>

            <label for="reimbursementType">Lodging</label>
            <input type="radio" name="reimbursementType" value="1" required /><br>

            <label for="reimbursementType">Travel</label>
            <input type="radio" name="reimbursementType" value="2" required /><br>

            <label for="reimbursementType">Food</label>
            <input type="radio" name="reimbursementType" value="3" required /><br>

            <label for="reimbursementType">Other</label>
            <input type="radio" name="reimbursementType" value="4" required /><br>

            <input type="submit" value="Create" class="btn btn-success"/>
        </form><br>
    </div>

</body>

<jsp:include page="../footer.jsp" />