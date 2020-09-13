<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*"%>
<%@page import="com.revature.model.ErsReimbursement"%>

<jsp:include page="../header.jsp" />

    <header>Reimbursements</header>

    <table class="table table-bordered table-dark-border-color">
        <th>Amount</th> <th>Submitted</th> <th>Resolved</th> <th>Description</th> <th>Receipt</th> <th>AuthorId</th> <th>Resolver</th>
        <th>Status</th> <th>Type</th> <th>Submit</th>
    <%
        List<ErsReimbursement> reimbursements = (ArrayList<ErsReimbursement>)request.getAttribute("reimbursements");

        for(ErsReimbursement reimbursement : reimbursements) {

            %><tr class="table-info"><%

            if(reimbursement.getReimbStatusId() == 1) { %>
                <td> <%=reimbursement.getAmount()%> </td>
                <td> <%=reimbursement.getSubmitted()%> </td>
                <td> <%=reimbursement.getResolved()%> </td>
                <td> <%=reimbursement.getDescription()%> </td>
                <td> <%=reimbursement.getReceipt()%> </td>
                <td> <%=reimbursement.getAuthorId()%> </td>
                <td> <%=reimbursement.getResolverId()%> </td>
                <td> <%=reimbursement.getReimbStatusId()%> </td>
                <td> <%=reimbursement.getReimbTypeId()%> </td>
                <td><form action="processreimbursement" method="POST">
                    <input type="radio" name="processOption" value="2" />
                    <label for="Approve">Approve</label><br>
                    <input type="radio" name="processOption" value="3" />
                    <label for="Deny">Deny</label><br>
                    <input type="hidden" name="reimbursementId" value=<%=reimbursement.getReimbId()%> />
                    <input type="hidden" name="receipt" value=<%=reimbursement.getAmount()%> />
                    <input type="submit" />
                </form></td>
                <br><br>
                <%
            } else {
            %><td> <%=reimbursement.getAmount()%> </td>
            <td> <%=reimbursement.getSubmitted()%> </td>
            <td> <%=reimbursement.getResolved()%> </td>
            <td> <%=reimbursement.getDescription()%> </td>
            <td> <%=reimbursement.getReceipt()%> </td>
            <td> <%=reimbursement.getAuthorId()%> </td>
            <td> <%=reimbursement.getResolverId()%> </td>
            <td> <%=reimbursement.getReimbStatusId()%> </td>
            <td> <%=reimbursement.getReimbTypeId()%> </td>
            <td></td>
            <br><br>
            <%}%>
        </tr>
        <%}%>
    </table>

    <br>
    <br>
    <form class="form-group" action="viewallreimbursementsbytype" method="GET">
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
    <form class="form-group" action="viewallreimbursementsbystatus" method="GET">
        <input type="radio" name="reimbursementStatus" value="1" />
        <label for="pending">Pending</label><br>
        <input type="radio" name="reimbursementStatus" value="2" />
        <label for="approved">Approved</label><br>
        <input type="radio" name="reimbursementStatus" value="3" />
        <label for="denied">Denied</label><br>
        <input type="submit" />
    </form>
    <br>
    <form class="form-group" action="viewallreimbursements" method="GET">
        <label>View All Reimbursements<label>
        <input type="submit" />
    </form><br>

<jsp:include page="../header.jsp" />