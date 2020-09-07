<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*"%>
<%@page import="com.revature.model.ErsReimbursement"%>

<html>
<head>
    <title>Reimbursements View</title>
</head>
<body>
    <header>Reimbursements</header>

    <%
        List<ErsReimbursement> reimbursements = (ArrayList<ErsReimbursement>)request.getAttribute("reimbursements");
        for(ErsReimbursement reimbursement : reimbursements) {


            if(reimbursement.getReimbStatusId() == 1) { %>
                <%=reimbursement%>
                <form action="processreimbursement" method="POST">
                    <input type="radio" name="processOption" value="2" />
                    <label for="Approve">Approve</label><br>
                    <input type="radio" name="processOption" value="3" />
                    <label for="Deny">Deny</label>
                    <input type="hidden" name="reimbursementId" value=<%=reimbursement.getReimbId()%> />
                    <input type="hidden" name="receipt" value=<%=reimbursement.getAmount()%> />
                    <input type="submit" />
                </form>
                <br><br>
                <%
            } else {
            %><%=reimbursement%><br><br><%
            }
        }
    %>

    <br>
    <br>
    <form action="viewallreimbursementsbytype" method="GET">
        <input type="radio" name="reimbursementType" value="1" />
        <label for="lodging">Lodging</label><br>
        <input type="radio" name="reimbursementType" value="2" />
        <label for="travel">Travel</label><br>
        <input type="radio" name="reimbursementType" value="3" />
        <label for="food">Food</label><br>
        <input type="radio" name="reimbursementType" value="4" />
        <label for="other">Other</label><br>
        <input type="submit" />
    </form>
    <br>
    <form action="viewallreimbursementsbystatus" method="GET">
        <input type="radio" name="reimbursementStatus" value="1" />
        <label for="pending">Pending</label><br>
        <input type="radio" name="reimbursementStatus" value="2" />
        <label for="approved">Approved</label><br>
        <input type="radio" name="reimbursementStatus" value="3" />
        <label for="denied">Denied</label><br>
        <input type="submit" />
    </form>
    <br>
    <form action="viewallreimbursements" method="GET">
        <label>View All Reimbursements<label>
        <input type="submit" />
    </form><br>




</body>
</html>
