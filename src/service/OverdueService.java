package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.ConnectionManager;

public class OverdueService {

    public static void showOverdueBooks()
    {

        try(
            Connection con =
            ConnectionManager.getConnection()
        )
        {

            PreparedStatement ps =
                    con.prepareStatement(
            "SELECT * FROM Loans WHERE returnDate<CURRENT_DATE");

            ResultSet rs =
                    ps.executeQuery();

            boolean found = false;

            while(rs.next())
            {

                found = true;

                System.out.println(
                "Loan ID: "
                +rs.getInt("loanId"));

                System.out.println(
                "Member ID: "
                +rs.getInt("memberId"));

                System.out.println(
                "Book ID: "
                +rs.getInt("bookId"));

                System.out.println(
                "Return Date: "
                +rs.getDate("returnDate"));

                System.out.println(
                "---------------");
            }

            if(!found)
            {
                System.out.println(
                        "No overdue books");
            }

        }

        catch(Exception e){

            System.out.println(
                    e.getMessage());
        }

    }

}