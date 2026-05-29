package service;

import java.sql.*;

import connection.ConnectionManager;

public class TransactionService {

    public static void processLoan(
            int memberId,
            int bookId)
    {

        Connection con = null;

        try{

            con =
            ConnectionManager.getConnection();

            con.setAutoCommit(false);

            // Check Book Availability

            PreparedStatement checkBook =
                    con.prepareStatement(
            "SELECT available FROM Books WHERE bookId=?");

            checkBook.setInt(1,bookId);

            ResultSet rs =
                    checkBook.executeQuery();

            if(rs.next())
            {
                boolean available =
                        rs.getBoolean(
                        "available");

                if(!available)
                {
                    throw new SQLException(
                            "Book unavailable");
                }
            }
            else
            {
                throw new SQLException(
                        "Book not found");
            }

            // Update Book Status

            PreparedStatement updateBook =
                    con.prepareStatement(
            "UPDATE Books SET available=false WHERE bookId=?");

            updateBook.setInt(
                    1,
                    bookId);

            updateBook.executeUpdate();

            // Savepoint

            Savepoint sp =
                    con.setSavepoint(
                    "LoanSavepoint");

            // Insert Loan

            int loanId =
            (int)(System.currentTimeMillis()%100000);

            PreparedStatement loan =
                    con.prepareStatement(
            "INSERT INTO Loans VALUES(?,?,?,CURRENT_DATE)");

            loan.setInt(
                    1,
                    loanId);

            loan.setInt(
                    2,
                    memberId);

            loan.setInt(
                    3,
                    bookId);

            loan.executeUpdate();

            // Update activeLoans count

            PreparedStatement updateMember =
                    con.prepareStatement(
            "UPDATE Members SET activeLoans=activeLoans+1 WHERE memberId=?");

            updateMember.setInt(
                    1,
                    memberId);

            updateMember.executeUpdate();

            con.commit();

            System.out.println(
                    "Loan processed successfully");

        }

        catch(Exception e){

            try{

                if(con!=null)
                {
                    con.rollback();

                    System.out.println(
                    "Transaction rolled back");
                }

            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }

            System.out.println(
                    e.getMessage());

        }

        finally{

            try{

                if(con!=null)
                    con.close();

            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        }

    }

}