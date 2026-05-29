package service;

import java.sql.Connection;
import java.sql.PreparedStatement;

import connection.ConnectionManager;

public class BookService {

    public static void addBook(
            int id,
            String title,
            String isbn)
    {

        try(
                Connection con=
                ConnectionManager.getConnection()
        )
        {

            PreparedStatement ps=
                    con.prepareStatement(
            "INSERT INTO Books VALUES(?,?,?,?)");

            ps.setInt(1,id);
            ps.setString(2,title);
            ps.setString(3,isbn);
            ps.setBoolean(4,true);

            ps.executeUpdate();

            System.out.println(
                    "Book added successfully");

        }

        catch(Exception e){

            System.out.println(
                    e.getMessage());

        }

    }

}