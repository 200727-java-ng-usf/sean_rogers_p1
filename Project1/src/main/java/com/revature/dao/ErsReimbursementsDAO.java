package com.revature.dao;

import com.revature.model.ErsReimbursement;
import com.revature.model.ErsUser;
import com.revature.utilities.DAOUtilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
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

    public List<ErsReimbursement> getAllByType(int type) {

        List<ErsReimbursement> reimbursements = new ArrayList<>();

        try (Connection connection = DAOUtilities.getConnection();){

            pstmt = connection.prepareStatement("SELECT * FROM ers_reimbursements where reimb_type_id = ?");

            pstmt.setInt(1, type);

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

    public List<ErsReimbursement> getAllByStatus(int status) {

        List<ErsReimbursement> reimbursements = new ArrayList<>();

        try (Connection connection = DAOUtilities.getConnection();){

            pstmt = connection.prepareStatement("SELECT * FROM ers_reimbursements where reimb_status_id = ?");

            pstmt.setInt(1, status);

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

    public boolean update(long reimbId, double receipt, long resolverId, int reimbStatusId) {
        try(Connection connection = DAOUtilities.getConnection();) {

            ErsReimbursement ersReimbursement = null;
            String sql = "SELECT * FROM ers_reimbursements where reimb_id = ?";
            pstmt = connection.prepareStatement(sql);

            pstmt.setLong(1, reimbId);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                ersReimbursement = new ErsReimbursement();
                ersReimbursement.setReimbTypeId(rs.getInt("reimb_id"));
                ersReimbursement.setAmount(rs.getDouble("amount"));
                ersReimbursement.setSubmitted(rs.getTimestamp("submitted"));
                ersReimbursement.setResolved(new Timestamp(Calendar.getInstance().getTime().getTime()));
                ersReimbursement.setDescription(rs.getString("description"));
                ersReimbursement.setReceipt(receipt);
                ersReimbursement.setAuthorId(rs.getInt("author_id"));
                ersReimbursement.setResolverId(resolverId);
                ersReimbursement.setReimbTypeId(rs.getInt("reimb_type_id"));
            }


            sql = "UPDATE ers_reimbursements set resolved = ?, " +
                    "reciept = ?, " +
                    "resolver_id = ?, " +
                    "reimb_status_id = ? " +
                    "where reimb_id = ?";
            pstmt = connection.prepareStatement(sql);


            pstmt.setTimestamp(1, new Timestamp(Calendar.getInstance().getTime().getTime()));
            pstmt.setDouble(2, receipt);
            pstmt.setLong(3, ersReimbursement.getResolverId());
            pstmt.setInt(4, reimbStatusId);
            pstmt.setLong(5, reimbId);

            int rowsInserted = pstmt.executeUpdate();

            if(rowsInserted != 0) {
                return true;
            }

        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            pstmt = null;
        }

        return false;
    }

}
