package service;

import java.sql.Connection;
import java.sql.PreparedStatement;

import connection.ConnectionManager;

public class InsertData {

    public static void insertSampleData() {

        try(Connection con=
                ConnectionManager.getConnection())
        {

            try{
                PreparedStatement book1=
                        con.prepareStatement(
                "INSERT INTO Books VALUES(?,?,?,?)");

                book1.setInt(1,102);
                book1.setString(2,
                        "Database Systems");
                book1.setString(3,
                        "ISBN002");
                book1.setBoolean(4,true);

                book1.executeUpdate();

                System.out.println(
                        "Book inserted");

            }
            catch(Exception e){

                System.out.println(
                        "Book already exists");
            }

        }
        catch(Exception e){

            System.out.println(
                    e.getMessage());
        }
    }
}