package performance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import connection.ConnectionManager;

public class PerformanceEvaluator {

    public static void testInsertPerformance()
    {

        Connection con = null;

        try{

            con =
            ConnectionManager.getConnection();

            // Warm-up

            for(int i=0;i<100;i++)
            {

            }

            // -------------------------
            // Single Insert Test
            // -------------------------

            long start1 =
                    System.nanoTime();

            for(int i=5000;i<5100;i++)
            {

                PreparedStatement ps =
                        con.prepareStatement(
                "INSERT INTO Books VALUES(?,?,?,?)");

                ps.setInt(1,i);

                ps.setString(
                        2,
                        "Book"+i);

                ps.setString(
                        3,
                        "ISBN"+i);

                ps.setBoolean(
                        4,
                        true);

                ps.executeUpdate();
            }

            long end1 =
                    System.nanoTime();

            double singleTime =
            (end1-start1)/1000000.0;


            // -------------------------
            // Batch Insert Test
            // -------------------------

            PreparedStatement batch =
                    con.prepareStatement(
            "INSERT INTO Books VALUES(?,?,?,?)");

            long start2 =
                    System.nanoTime();

            for(int i=6000;i<6100;i++)
            {

                batch.setInt(1,i);

                batch.setString(
                        2,
                        "BatchBook"+i);

                batch.setString(
                        3,
                        "BISBN"+i);

                batch.setBoolean(
                        4,
                        true);

                batch.addBatch();
            }

            batch.executeBatch();

            long end2 =
                    System.nanoTime();

            double batchTime =
            (end2-start2)/1000000.0;


            // -------------------------
            // Statement Test
            // -------------------------

            Statement st =
                    con.createStatement();

            long start3 =
                    System.nanoTime();

            for(int i=7000;i<7100;i++)
            {

                st.executeUpdate(
                "INSERT INTO Books VALUES("
                +i+
                ",'StatementBook"
                +i+
                "','SISBN"
                +i+
                "',true)");
            }

            long end3 =
                    System.nanoTime();

            double statementTime =
            (end3-start3)/1000000.0;


            // -------------------------
            // PreparedStatement Test
            // -------------------------

            PreparedStatement prep =
                    con.prepareStatement(
            "INSERT INTO Books VALUES(?,?,?,?)");

            long start4 =
                    System.nanoTime();

            for(int i=8000;i<8100;i++)
            {

                prep.setInt(1,i);

                prep.setString(
                        2,
                        "PrepBook"+i);

                prep.setString(
                        3,
                        "PISBN"+i);

                prep.setBoolean(
                        4,
                        true);

                prep.executeUpdate();
            }

            long end4 =
                    System.nanoTime();

            double preparedTime =
            (end4-start4)/1000000.0;


            // Throughput

            double singleThroughput =
                    100/singleTime;

            double batchThroughput =
                    100/batchTime;

            double statementThroughput =
                    100/statementTime;

            double preparedThroughput =
                    100/preparedTime;


            // Final Report

            System.out.println(
            "\n===== Performance Report =====");

            System.out.println(
            "Operation\tRecords\tTime(ms)\tThroughput");

            System.out.println(
            "SingleInsert\t100\t"
            +singleTime+"\t"
            +singleThroughput);

            System.out.println(
            "BatchInsert\t100\t"
            +batchTime+"\t"
            +batchThroughput);

            System.out.println(
            "Statement\t100\t"
            +statementTime+"\t"
            +statementThroughput);

            System.out.println(
            "PreparedStmt\t100\t"
            +preparedTime+"\t"
            +preparedThroughput);

        }

        catch(Exception e){

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