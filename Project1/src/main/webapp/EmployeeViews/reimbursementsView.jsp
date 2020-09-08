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
                <%=reimbursement%><br>
                <form action="updatereimbursement" method="POST">
                    <input type="text" name="amount" placeholder="Amount" value=<%=reimbursement.getAmount()%> />
                    <input type="text" name="description" placeholder="description" value=<%=reimbursement.getDescription()%> />

                    <label for="reimbursementType">Lodging</label>
                    <input type="radio" name="reimbursementType" value="1" <%if(reimbursement.getReimbTypeId() == 1) {%>checked<%}%> />

                    <label for="reimbursementType">Travel</label>
                    <input type="radio" name="reimbursementType" value="2" <%if(reimbursement.getReimbTypeId() == 2) {%>checked<%}%> />

                    <label for="reimbursementType">Food</label>
                    <input type="radio" name="reimbursementType" value="3" <%if(reimbursement.getReimbTypeId() == 3) {%>checked<%}%> />

                    <label for="reimbursementType">Other</label>
                    <input type="radio" name="reimbursementType" value="4" <%if(reimbursement.getReimbTypeId() == 4) {%>checked<%}%> />

                    <input type="hidden" name="reimbursementId" value=<%=reimbursement.getReimbId()%> />
                    <input type="submit" value="update"/>
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
