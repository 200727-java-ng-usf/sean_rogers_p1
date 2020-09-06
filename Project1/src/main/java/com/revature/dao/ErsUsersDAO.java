package com.revature.dao;

import com.revature.model.ErsUser;
import com.revature.utilities.DAOUtilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ErsUsersDAO {



    public List<ErsUser> getAll() {

        //DAOUtilities daoUtilities = new DAOUtilities();

        List<ErsUser> users = new ArrayList<>();

        try (Connection connection = DAOUtilities.getConnection();
             PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM ers_users");){

            ResultSet rs = pstmt.executeQuery();			// Queries the database

            // So long as the ResultSet actually contains results...
            while (rs.next()) {
                // We need to populate a Book object with info for each row from our query result
                ErsUser user = new ErsUser();

                // Each variable in our Book object maps to a column in a row from our results.
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
        }

        // return the list of Book objects populated by the DB.
        return users;
    }

    public ErsUser getUserByUsernameAndPassword(String username, String password) {
        ErsUser user = null;

        try (Connection connection = DAOUtilities.getConnection();
             PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM ers_users where username = ? and password = ?");){

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();			// Queries the database

            // So long as the ResultSet actually contains results...
            while (rs.next()) {
                // We need to populate a Book object with info for each row from our query result
                user = new ErsUser();

                // Each variable in our Book object maps to a column in a row from our results.
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
            PreparedStatement pstmt = connection.prepareStatement(sql);

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
        }

        return false;
    }

    public boolean update(ErsUser user) {
        try(Connection connection = DAOUtilities.getConnection();) {

            ErsUser updatedUser = null;
            String sql = "SELECT * FROM ers_users where username = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);

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
                    "where username = ?";
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
        }

        return false;
    }


    /*------------------------------------------------------------------------------------------------*/


//    @Override
//    public List<Book> getBooksByTitle(String title) {
//
//        List<Book> books = new ArrayList<>();
//
//        try {
//            connection = DAOUtilities.getConnection();
//            String sql = "SELECT * FROM Books WHERE title LIKE ?";	// Note the ? in the query
//            stmt = connection.prepareStatement(sql);
//
//            // This command populate the 1st '?' with the title and wildcards, between ' '
//            stmt.setString(1, "%" + title + "%");
//
//            ResultSet rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                Book book = new Book();
//
//                book.setIsbn13(rs.getString("isbn_13"));
//                book.setAuthor(rs.getString("author"));
//                book.setTitle(rs.getString("title"));
//                book.setPublishDate(rs.getDate("publish_date").toLocalDate());
//                book.setPrice(rs.getDouble("price"));
//                book.setContent(rs.getBytes("content"));
//
//                books.add(book);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            closeResources();
//        }
//
//        return books;
//    }
//
//
//    /*------------------------------------------------------------------------------------------------*/
//
//
//    @Override
//    public List<Book> getBooksByAuthor(String author) {
//        List<Book> books = new ArrayList<>();
//
//        try {
//            connection = DAOUtilities.getConnection();
//            String sql = "SELECT * FROM Books WHERE author LIKE ?";
//            stmt = connection.prepareStatement(sql);
//
//            stmt.setString(1, "%" + author + "%");
//
//            ResultSet rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                Book book = new Book();
//
//                book.setIsbn13(rs.getString("isbn_13"));
//                book.setAuthor(rs.getString("author"));
//                book.setTitle(rs.getString("title"));
//                book.setPublishDate(rs.getDate("publish_date").toLocalDate());
//                book.setPrice(rs.getDouble("price"));
//                book.setContent(rs.getBytes("content"));
//
//                books.add(book);
//
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            closeResources();
//        }
//
//        return books;
//    }
//
//
//    /*------------------------------------------------------------------------------------------------*/
//
//
//    @Override
//    public List<Book> getBooksLessThanPrice(double price) {
//        List<Book> books = new ArrayList<>();
//
//        try {
//            connection = DAOUtilities.getConnection();
//            String sql = "SELECT * FROM Books WHERE price < ?";
//            stmt = connection.prepareStatement(sql);
//
//            stmt.setDouble(1, price);;
//
//            ResultSet rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                Book book = new Book();
//
//                book.setIsbn13(rs.getString("isbn_13"));
//                book.setAuthor(rs.getString("author"));
//                book.setTitle(rs.getString("title"));
//                book.setPublishDate(rs.getDate("publish_date").toLocalDate());
//                book.setPrice(rs.getDouble("price"));
//                book.setContent(rs.getBytes("content"));
//
//                books.add(book);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            closeResources();
//        }
//
//        return books;
//    }
//
//
//    /*------------------------------------------------------------------------------------------------*/
//
//
//    @Override
//    public Book getBookByISBN(String isbn) {
//        Book book = null;
//
//        try {
//            connection = DAOUtilities.getConnection();
//            String sql = "SELECT * FROM Books WHERE isbn_13 = ?";
//            stmt = connection.prepareStatement(sql);
//
//            stmt.setString(1, isbn);
//
//            ResultSet rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                book = new Book();
//                book.setIsbn13(rs.getString("isbn_13"));
//                book.setAuthor(rs.getString("author"));
//                book.setTitle(rs.getString("title"));
//                book.setPublishDate(rs.getDate("publish_date").toLocalDate());
//                book.setPrice(rs.getDouble("price"));
//                book.setContent(rs.getBytes("content"));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            closeResources();
//        }
//
//        return book;
//    }
//
//
//    /*------------------------------------------------------------------------------------------------*/
//
//
//    @Override
//    public boolean addBook(Book book) {
//
//        try {
//            connection = DAOUtilities.getConnection();
//            String sql = "INSERT INTO Books VALUES (?, ?, ?, ?, ?, ?)"; // Were using a lot of ?'s here...
//            stmt = connection.prepareStatement(sql);
//
//            // But that's okay, we can set them all before we execute
//            stmt.setString(1, book.getIsbn13());
//            stmt.setString(2, book.getTitle());
//            stmt.setString(3, book.getAuthor());
//            stmt.setDate(4, Date.valueOf(book.getPublishDate()));
//            stmt.setDouble(5, book.getPrice());
//
//            stmt.setBytes(6, book.getContent());
//
//            // If we were able to add our book to the DB, we want to return true.
//            // This if statement both executes our query, and looks at the return
//            // value to determine how many rows were changed
//            if (stmt.executeUpdate() != 0)
//                return true;
//            else
//                return false;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            closeResources();
//        }
//    }
//
//
//    /*------------------------------------------------------------------------------------------------*/
//
//
//    @Override
//    public boolean updateBook(Book book) {
//        try {
//            connection = DAOUtilities.getConnection();
//            String sql = "UPDATE Books SET title=?, author=?, price=? WHERE isbn_13=?";
//            stmt = connection.prepareStatement(sql);
//
//            stmt.setString(1, book.getTitle());
//            stmt.setString(2, book.getAuthor());
//            stmt.setDouble(3, book.getPrice());
//            stmt.setString(4, book.getIsbn13());
//
//            System.out.println(stmt);
//
//            if (stmt.executeUpdate() != 0)
//                return true;
//            else
//                return false;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            closeResources();
//        }
//
//    }
//
//
//    /*------------------------------------------------------------------------------------------------*/
//
//
//    @Override
//    public boolean deleteBookByISBN(String isbn) {
//        try {
//            connection = DAOUtilities.getConnection();
//            String sql = "DELETE Books WHERE isbn_13=?";
//            stmt = connection.prepareStatement(sql);
//
//            stmt.setString(1, isbn);
//
//            if (stmt.executeUpdate() != 0)
//                return true;
//            else
//                return false;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            closeResources();
//        }
//    }

}
