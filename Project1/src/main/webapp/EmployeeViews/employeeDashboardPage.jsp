<body>
    <header>Welcome Employee: ${user.firstName}</header>

    <form action="viewallreimbursementsbyemployee" method="GET">
        <label>View All Reimbursements<label><br>
        <input type="submit" />
    </form>
    <br><br>
    <form action="submitnewreimbursement" method="POST">
        <label>Submit New Reimbursements<label><br>
        <input type="text" name="amount" placeholder="Amount" required />
        <input type="text" name="description" placeholder="description" required />

        <label for="reimbursementType">Lodging</label>
        <input type="radio" name="reimbursementType" value="1" required />

        <label for="reimbursementType">Travel</label>
        <input type="radio" name="reimbursementType" value="2" required />

        <label for="reimbursementType">Food</label>
        <input type="radio" name="reimbursementType" value="3" required />

        <label for="reimbursementType">Other</label>
        <input type="radio" name="reimbursementType" value="4" required />

        <input type="submit" value="Create"/>
    </form><br>


</body>