<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*"%>
<%@page import="com.revature.model.ErsReimbursement"%>

<jsp:include page="../header.jsp" />


    <header>Reimbursements</header>

    <table class="table table-bordered table-dark-border-color">
    <th>Amount</th> <th>Submitted</th> <th>Resolved</th> <th>Description</th> <th>Receipt</th> <th>Resolver</th>
    <th>Status</th> <th>Type</th> <th>Submit</th>
    <%
        List<ErsReimbursement> reimbursements = (ArrayList<ErsReimbursement>)request.getAttribute("reimbursements");


        for(ErsReimbursement reimbursement : reimbursements) {

            %><tr class="table-info"><%

            if(reimbursement.getReimbStatusId() == 1) { %>
                <form action="updatereimbursement" method="POST">
                    <td><input type="text" name="amount" placeholder="Amount" size="10" value=<%=reimbursement.getAmount()%> required /></td>
                    <td><%=reimbursement.getSubmitted()%> </td>
                    <td>Unresolved</td>
                    <td><input type="text" name="description" placeholder="description" value=<%=reimbursement.getDescription()%> required /></td>
                    <td>No Receipt</td>
                    <td>No Resolver</td>
                    <td>Pending</td>
                    <td><label for="reimbursementType">Lodging</label>
                    <input type="radio" name="reimbursementType" value="1" <%if(reimbursement.getReimbTypeId() == 1) {%>checked<%}%> /><br>

                    <label for="reimbursementType">Travel</label>
                    <input type="radio" name="reimbursementType" value="2" <%if(reimbursement.getReimbTypeId() == 2) {%>checked<%}%> /><br>

                    <label for="reimbursementType">Food</label>
                    <input type="radio" name="reimbursementType" value="3" <%if(reimbursement.getReimbTypeId() == 3) {%>checked<%}%> /><br>

                    <label for="reimbursementType">Other</label>
                    <input type="radio" name="reimbursementType" value="4" <%if(reimbursement.getReimbTypeId() == 4) {%>checked<%}%> /></td>

                    <td><input type="submit" class="btn btn-success" value="Update"/></td>
                    <input type="hidden" name="reimbursementId" value=<%=reimbursement.getReimbId()%> />

                </form>
                <br><br>

            <% } else { %>
            <td><%=reimbursement.getAmount()%></td>
            <td><%=reimbursement.getSubmitted()%></td>
            <td><%=reimbursement.getResolved()%></td>
            <td><%=reimbursement.getDescription()%></td>
            <td><%=reimbursement.getReceipt()%></td>
            <td><%=reimbursement.getResolverId()%></td>
            <td><%=reimbursement.getReimbStatusId()%></td>
            <td><%=reimbursement.getReimbTypeId()%></td>
            <td></td>
            <br><br>
            <% } %>
            </tr>
        <%}%>

    </table>


<jsp:include page="../header.jsp" />