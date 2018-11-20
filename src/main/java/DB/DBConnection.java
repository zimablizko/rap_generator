package DB;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBConnection {

    // JDBC URL, username and password of MySQL server
    private String url;
    private String user;
    private String password;

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;


    public Statement start() {
        String query = "select 1 from dual";

        try {
            Properties prop = new Properties();
            InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("app.properties");
            // load a properties file
            prop.load(input);
            // get the property value and print it out
            url = prop.getProperty("db_url");
            user = prop.getProperty("db_user");
            password = prop.getProperty("db_password");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            stmt = con.createStatement();
            // executing SELECT query
            rs = stmt.executeQuery(query);

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            //  } finally {
            //close connection ,stmt and resultset here
            //       try {
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
