package com.revature.dao;

import com.revature.model.ErsReimbursement;
import com.revature.model.ErsUser;
import com.revature.utilities.DAOUtilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ErsReimbursementsDAO {

    PreparedStatement pstmt;

    public List<ErsReimbursement> getAll() {

        //DAOUtilities daoUtilities = new DAOUtilities();

        List<ErsReimbursement> reimbursements = new ArrayList<>();

        try (Connection connection = DAOUtilities.getConnection();){


            pstmt = connection.prepareStatement("SELECT * FROM ers_reimbursements");

            ResultSet rs = pstmt.executeQuery();			// Queries the database

            // So long as the ResultSet actually contains results...
            while (rs.next()) {
                // We need to populate a Book object with info for each row from our query result
                ErsReimbursement reimbursement = new ErsReimbursement();

                // Each variable in our Book object maps to a column in a row from our results.
                reimbursement.setReimbId(rs.getInt("reimb_id"));
                reimbursement.setAmount(rs.getDouble("amount"));
                reimbursement.setSubmitted(rs.getTimestamp("submitted"));
                reimbursement.setResolved(rs.getTimestamp("resolved"));
                reimbursement.setDescription((rs.getString("description")));
                reimbursement.setReceipt(rs.getDouble("reciept"));
                reimbursement.setAuthorId(rs.getInt("author_id"));
                reimbursement.setResolverId(rs.getInt("resolver_id"));
                reimbursement.setReimbStatusId(rs.getInt("reimb_status_id"));
                reimbursement.setReimbTypeId(rs.getInt("reimb_type_id"));

                reimbursements.add(reimbursement);

            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pstmt = null;
        }

        // return the list of Book objects populated by the DB.
        return reimbursements;
    }

}
