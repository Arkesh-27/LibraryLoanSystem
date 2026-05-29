package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.ConnectionManager;

public class LoanQueryService {

    public static void showLoans(
            int memberId)
    {

        try(
            Connection con =
            ConnectionManager.getConnection()
        )
        {

            PreparedStatement ps =
                    con.prepareStatement(
            "SELECT * FROM Loans WHERE memberId=?");

            ps.setInt(1,memberId);

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
                "Book ID: "
                +rs.getInt("bookId"));

                System.out.println(
                "Return Date: "
                +rs.getDate("returnDate"));

                System.out.println(
                "----------------");
            }

            if(!found)
            {
                System.out.println(
                        "No active loans");
            }

        }

        catch(Exception e){

            System.out.println(
                    e.getMessage());
        }

    }

}