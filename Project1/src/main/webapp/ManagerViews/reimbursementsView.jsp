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
                <form action="processreimbursement">
                    <input type="radio" name="processOption" value="approve" />
                    <label for="admin">Approve</label><br>
                    <input type="radio" name="processOption" value="deny" />
                    <label for="manager">Deny</label>
                    <input type="hidden" name="reimbursementId" value=<c:out value="${reimbursement.getReimbId()}" /> />
                    <input type="submit" />
                </form>
                <br><br>
                <%
            } else {
            %><%=reimbursement%><br><br><%
            }
        }
    %>



</body>
</html>
