import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {


    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://VH251.spaceweb.ru:3306/nklv94mail_rapgn";
    private static final String user = "nklv94mail_rapgn";
    private static final String password = "1q3w5e2a4s6d";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;



    public Statement start() {
        String query = "select 1 from dual";

        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            stmt = con.createStatement();
            // executing SELECT query
            rs = stmt.executeQuery(query);
            // while (rs.next()) {
            //    int count = rs.getInt(1);
            //    System.out.println("Total number of books in the table : " + count);
            //}

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            //  } finally {
            //close connection ,stmt and resultset here
            ///      try {
            //        con.close();
            //      } catch (SQLException se) { /*can't do anything */ }
            //      try {
            //          stmt.close();
            //       } catch (SQLException se) { /*can't do anything */ }
            //      try {
            //          rs.close();
            //       } catch (SQLException se) { /*can't do anything */ }
        }
        return stmt;


    }

}
