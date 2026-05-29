package service;

import java.sql.Connection;
import java.sql.PreparedStatement;

import connection.ConnectionManager;

public class MemberService {

    public static void addMember(
            int id,
            String name)
    {

        try(
            Connection con=
            ConnectionManager.getConnection()
        )
        {

            PreparedStatement ps=
                    con.prepareStatement(
            "INSERT INTO Members VALUES(?,?,?)");

            ps.setInt(1,id);
            ps.setString(2,name);
            ps.setInt(3,0);

            ps.executeUpdate();

            System.out.println(
                    "Member added successfully");

        }

        catch(Exception e){

            System.out.println(
                    e.getMessage());
        }

    }
}