package connection;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseSetup {

    public static void createTables() {

        try(
            Connection con =
                    ConnectionManager.getConnection();

            Statement st =
                    con.createStatement()
        )
        {

            st.executeUpdate(
            "CREATE TABLE Members("+
            "memberId INT PRIMARY KEY,"+
            "name VARCHAR(50),"+
            "activeLoans INT)");

            st.executeUpdate(
            "CREATE TABLE Books("+
            "bookId INT PRIMARY KEY,"+
            "title VARCHAR(100),"+
            "isbn VARCHAR(20),"+
            "available BOOLEAN)");

            st.executeUpdate(
            "CREATE TABLE Loans("+
            "loanId INT PRIMARY KEY,"+
            "memberId INT REFERENCES Members(memberId),"+
            "bookId INT REFERENCES Books(bookId),"+
            "returnDate DATE)");

            // Indexes

            st.executeUpdate(
            "CREATE INDEX idx_isbn ON Books(isbn)");

            st.executeUpdate(
            "CREATE INDEX idx_member ON Loans(memberId)");

            st.executeUpdate(
            "CREATE INDEX idx_return ON Loans(returnDate)");

            System.out.println(
            "Tables and indexes created successfully");

        }

        catch(Exception e){

            System.out.println(
                    e.getMessage());
        }

    }
}