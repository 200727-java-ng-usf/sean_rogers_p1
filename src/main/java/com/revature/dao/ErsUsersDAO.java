package com.revature.dao;

import com.revature.model.ErsUser;
import com.revature.utilities.DAOUtilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class creates, reads, and updates ers_users. Users are never deleted, they are marked inactive instead
 */
public class ErsUsersDAO {

    PreparedStatement pstmt;

    /**
     * gets all users from database. This was used during the initial stages of testing. It's not used in production.
     * The list returned excludes inacvtive users
     * @return list of ErsUser
     */
    public List<ErsUser> getAll() {

        //DAOUtilities daoUtilities = new DAOUtilities();

        List<ErsUser> users = new ArrayList<>();

        try (Connection connection = DAOUtilities.getConnection();){


            pstmt = connection.prepareStatement("SELECT * FROM ers_users where is_active = true");

            ResultSet rs = pstmt.executeQuery();			// Queries the database


            while (rs.next()) {

                ErsUser user = new ErsUser();

                user.setId(rs.getInt("ers_user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName((rs.getString("last_name")));
                user.setEmail(rs.getString("email"));
                user.setUserRoleId(rs.getInt("user_role_id"));

                users.add(user);

            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pstmt = null;
        }


        return users;
    }

    /**
     * Gets user from database with the specified username. Will not return inactive users
     * @param username
     * @return ErsUser
     */
    public ErsUser getUserByUsername(String username) {
        ErsUser user = null;

        try (Connection connection = DAOUtilities.getConnection();){


            pstmt = connection.prepareStatement("SELECT * FROM ers_users where username = ? and is_active = true");

            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();			// Queries the database

            // So long as the ResultSet actually contains results...
            while (rs.next()) {
                // We need to populate a Book object with info for each row from our query result
                user = new ErsUser();

                // Each variable in our Book object maps to a column in a row from our results.
                user.setId(rs.getInt("ers_user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName((rs.getString("last_name")));
                user.setEmail(rs.getString("email"));
                user.setUserRoleId(rs.getInt("user_role_id"));

            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pstmt = null;
        }


        return user;
    }

    /**
     * gets the user by the email specified. Will not return inactive users
     * @param email
     * @return ErsUser
     */
    public ErsUser getUserByEmail(String email) {
        ErsUser user = null;

        try (Connection connection = DAOUtilities.getConnection();){


            pstmt = connection.prepareStatement("SELECT * FROM ers_users where email = ? and is_active = true");

            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();			// Queries the database

            // So long as the ResultSet actually contains results...
            while (rs.next()) {
                // We need to populate a Book object with info for each row from our query result
                user = new ErsUser();

                // Each variable in our Book object maps to a column in a row from our results.
                user.setId(rs.getInt("ers_user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName((rs.getString("last_name")));
                user.setEmail(rs.getString("email"));
                user.setUserRoleId(rs.getInt("user_role_id"));

            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pstmt = null;
        }


        return user;
    }

    /**
     * Gets the user with the specified username/password combination. Will not return inactive users
     * @param username
     * @param password
     * @return ErsUser
     */
    public ErsUser getUserByUsernameAndPassword(String username, String password) {
        ErsUser user = null;

        try (Connection connection = DAOUtilities.getConnection();){


            pstmt = connection.prepareStatement("SELECT * FROM ers_users where username = ? and password = ? and is_active = true");

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();			// Queries the database

            // So long as the ResultSet actually contains results...
            while (rs.next()) {
                // We need to populate a Book object with info for each row from our query result
                user = new ErsUser();

                // Each variable in our Book object maps to a column in a row from our results.
                user.setId(rs.getInt("ers_user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName((rs.getString("last_name")));
                user.setEmail(rs.getString("email"));
                user.setUserRoleId(rs.getInt("user_role_id"));

            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pstmt = null;
        }


        return user;
    }

    /**
     * Adds a new user to the database
     * @param newUser to be added to the database
     * @return true if successful, false if failed
     */
    public boolean save(ErsUser newUser) {

        try(Connection connection = DAOUtilities.getConnection();) {

            String sql = "INSERT INTO ers_users (username, \"password\", first_name, last_name, email, user_role_id) " +
                    "values (?, ?, ?, ?, ?, ?)";
            pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, newUser.getUsername());
            pstmt.setString(2, newUser.getPassword());
            pstmt.setString(3, newUser.getFirstName());
            pstmt.setString(4, newUser.getLastName());
            pstmt.setString(5, newUser.getEmail());
            pstmt.setInt(6, newUser.getUserRoleId());

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
     * updates the user in the databse with the user object parameter
     * @param user
     * @return true if successful, false if failed
     */
    public boolean update(ErsUser user) {
        try(Connection connection = DAOUtilities.getConnection();) {

            ErsUser updatedUser = null;
            String sql = "SELECT * FROM ers_users where username = ? and is_active = true";
            pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, user.getUsername());

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                updatedUser = new ErsUser();
                updatedUser.setUsername(rs.getString("username"));
                updatedUser.setPassword(rs.getString("password"));
                updatedUser.setFirstName(rs.getString("first_name"));
                updatedUser.setLastName(rs.getString("last_name"));
                updatedUser.setEmail(rs.getString("email"));
                updatedUser.setUserRoleId(rs.getInt("user_role_id"));
            }

            if(!user.getPassword().equals("")) {
                updatedUser.setPassword(user.getPassword());
            }
            if(!user.getFirstName().equals("")) {
                updatedUser.setFirstName(user.getFirstName());
            }
            if(!user.getLastName().equals("")) {
                updatedUser.setLastName(user.getLastName());
            }
            if(!user.getEmail().equals("")) {
                updatedUser.setEmail(user.getEmail());
            }
            if(user.getUserRoleId() != 0) {
                updatedUser.setUserRoleId(user.getUserRoleId());
            }


            sql = "UPDATE ers_users set \"password\" = ?, first_name = ?, last_name = ?, email = ?, " +
                    "user_role_id = ? " +
                    "where username = ? and is_active = true";
            pstmt = connection.prepareStatement(sql);


            pstmt.setString(1, updatedUser.getPassword());
            pstmt.setString(2, updatedUser.getFirstName());
            pstmt.setString(3, updatedUser.getLastName());
            pstmt.setString(4, updatedUser.getEmail());
            pstmt.setInt(5, updatedUser.getUserRoleId());
            pstmt.setString(6, updatedUser.getUsername());

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
     * DOES NOT DELETE THE USER. It merely marks the user with the specified username as inactive
     * @param username
     * @return true if successful, false if failed
     */
    public boolean delete(String username) {
        try(Connection connection = DAOUtilities.getConnection();) {

            String sql = "UPDATE ers_users set is_active = false WHERE username = ?";
            pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, username);

            if(pstmt.executeUpdate() != 0) {
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
