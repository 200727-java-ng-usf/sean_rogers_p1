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




</body>
</html>
