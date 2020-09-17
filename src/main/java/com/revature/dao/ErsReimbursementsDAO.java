package com.revature.dao;

import com.revature.model.ErsReimbursement;
import com.revature.model.ErsUser;
import com.revature.utilities.DAOUtilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * This class is for Creating, updating, and reading the ers_reimbursements table in postgres. Deleting is not used
 */
public class ErsReimbursementsDAO {

    PreparedStatement pstmt;

    /**
     * Get all reimbursements int the ers_reimbursements table
     * @return all the reimbursements from DB as a pojo
     */
    public List<ErsReimbursement> getAll() {

        //DAOUtilities daoUtilities = new DAOUtilities();

        List<ErsReimbursement> reimbursements = new ArrayList<>();

        try (Connection connection = DAOUtilities.getConnection();){


            pstmt = connection.prepareStatement("SELECT * FROM ers_reimbursements order by reimb_id");

            ResultSet rs = pstmt.executeQuery();			// Queries the database

            // For each row returned, create a new ErsReimbursement pojo, set all of its properties,
            // add it to reimbursements list
            while (rs.next()) {

                ErsReimbursement reimbursement = new ErsReimbursement();

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

    /**
     * Gets all reimbursements by type (Lodging, Food, Travel, Other)
     * @param type
     * @return all the reimbursements from DB as a pojo with specified type
     */
    public List<ErsReimbursement> getAllByType(int type) {

        // all reimbursements with specified type will be added to this list
        List<ErsReimbursement> reimbursements = new ArrayList<>();

        try (Connection connection = DAOUtilities.getConnection();){

            pstmt = connection.prepareStatement("SELECT * FROM ers_reimbursements where reimb_type_id = ? " +
                    "order by reimb_id");

            pstmt.setInt(1, type);

            ResultSet rs = pstmt.executeQuery();			// Queries the database

            // For each row returned, create a new ErsReimbursement pojo, set all of its properties,
            // add it to reimbursements list
            while (rs.next()) {

                ErsReimbursement reimbursement = new ErsReimbursement();


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

    /**
     * Gets all reimbursements by status (Pending, Approved, Denied)
     * @param status
     * @return all the reimbursements from DB as a pojo with specified status
     */
    public List<ErsReimbursement> getAllByStatus(int status) {

        // all reimbursements with specified status will be added to this list
        List<ErsReimbursement> reimbursements = new ArrayList<>();

        try (Connection connection = DAOUtilities.getConnection();){

            pstmt = connection.prepareStatement("SELECT * FROM ers_reimbursements where reimb_status_id = ? " +
                    "order by reimb_id");

            pstmt.setInt(1, status);

            ResultSet rs = pstmt.executeQuery();

            // For each row returned, create a new ErsReimbursement pojo, set all of its properties,
            // add it to reimbursements list
            while (rs.next()) {

                ErsReimbursement reimbursement = new ErsReimbursement();


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


        return reimbursements;
    }

    /**
     * Update the specified reimbursement
     * @param reimbId
     * @param receipt
     * @param resolverId
     * @param reimbStatusId
     * @return true if update successful. false if there was a SQLException or something else went wrong
     */
    public boolean update(long reimbId, double receipt, long resolverId, int reimbStatusId) {
        try(Connection connection = DAOUtilities.getConnection();) {

            ErsReimbursement ersReimbursement = null;
            String sql = "SELECT * FROM ers_reimbursements where reimb_id = ?";
            pstmt = connection.prepareStatement(sql);

            pstmt.setLong(1, reimbId);

            ResultSet rs = pstmt.executeQuery();

            // there should only be one row in rs
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

    /**
     * Updates the reimbursement with containing reimb_id == newErsReimbursement.reimbId
     * @param newErsReimbursement
     * @return true if successful, false if SQLException occured or something else happened
     */
    public boolean update(ErsReimbursement newErsReimbursement) {
        try(Connection connection = DAOUtilities.getConnection();) {

            String sql = "UPDATE ers_reimbursements set amount = ?, " +
                    "description = ?, " +
                    "reimb_type_id = ? " +
                    "where reimb_id = ?";

            pstmt = connection.prepareStatement(sql);

            pstmt.setDouble(1, newErsReimbursement.getAmount());
            pstmt.setString(2, newErsReimbursement.getDescription());
            pstmt.setLong(3, newErsReimbursement.getReimbTypeId());
            pstmt.setLong(4, newErsReimbursement.getReimbId());

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

    /**
     * Gets all reimbursements that were submitted by the specified id of a user
     * @param id
     * @return list of reimbursements
     */
    public List<ErsReimbursement> getAllByAuthorId(long id) {

        // reimbursements submitted by the user with the specified id will be added to this list
        List<ErsReimbursement> reimbursements = new ArrayList<>();

        try (Connection connection = DAOUtilities.getConnection();){

            pstmt = connection.prepareStatement("SELECT * FROM ers_reimbursements where author_id = ? " +
                    "order by reimb_id");

            pstmt.setLong(1, id);

            ResultSet rs = pstmt.executeQuery();


            while (rs.next()) {

                ErsReimbursement reimbursement = new ErsReimbursement();


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

    /**
     * Creates a new reimbursement in DB
     * @param ersReimbursement
     * @return true if successful, false if failed
     */
    public boolean save(ErsReimbursement ersReimbursement) {

        try(Connection connection = DAOUtilities.getConnection()) {

            String sql = "INSERT INTO ers_reimbursements (amount, submitted, description, author_id, reimb_status_id, reimb_type_id) " +
                    "values (?, ?, ?, ?, ?, ?)";
            pstmt = connection.prepareStatement(sql);

            pstmt.setDouble(1, ersReimbursement.getAmount());
            pstmt.setTimestamp(2, new Timestamp(Calendar.getInstance().getTime().getTime()));
            pstmt.setString(3, ersReimbursement.getDescription());
            pstmt.setLong(4, ersReimbursement.getAuthorId());
            pstmt.setLong(5, ersReimbursement.getReimbStatusId());
            pstmt.setLong(6, ersReimbursement.getReimbTypeId());

            int rowsInserted = pstmt.executeUpdate();

            if(rowsInserted != 0) {
                return true;
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
