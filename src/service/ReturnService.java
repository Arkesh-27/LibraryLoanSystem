package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.ConnectionManager;

public class ReturnService {

    public static void returnBook(
            int bookId)
    {

        try(
            Connection con =
            ConnectionManager.getConnection()
        )
        {

            // Get Member ID

            PreparedStatement getMember =
                    con.prepareStatement(
            "SELECT memberId FROM Loans WHERE bookId=?");

            getMember.setInt(1,bookId);

            ResultSet rs =
                    getMember.executeQuery();

            int memberId = 0;

            if(rs.next())
            {
                memberId =
                rs.getInt("memberId");
            }

            // Update Book

            PreparedStatement updateBook =
                    con.prepareStatement(
            "UPDATE Books SET available=true WHERE bookId=?");

            updateBook.setInt(1,bookId);

            int rows =
                    updateBook.executeUpdate();

            if(rows>0)
            {

                // Decrease activeLoans

                PreparedStatement updateMember =
                        con.prepareStatement(
                "UPDATE Members SET activeLoans=activeLoans-1 WHERE memberId=?");

                updateMember.setInt(
                        1,
                        memberId);

                updateMember.executeUpdate();

                System.out.println(
                        "Book returned successfully");
            }
            else
            {
                System.out.println(
                        "Book not found");
            }

        }

        catch(Exception e){

            System.out.println(
                    e.getMessage());
        }

    }

}