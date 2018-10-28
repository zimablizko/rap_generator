import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class Sentenses {
    public static int[] partsOfSpeech = {2, 1, 3};
    private static DBConnection db = new DBConnection();
    private static Statement stmt =  db.start();

    String generate() {
       String[] words =null;
        int a=0;
        for (int i = 0; i < partsOfSpeech.length; i++) {
            ArrayList<String> allwords = new ArrayList<String>();
            try {
                ResultSet rs;
                // executing SELECT query
               String query= "select word from words where pos_id="+partsOfSpeech[i];
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    allwords.add(rs.getString(1));
                }
                Random rnd = new Random();
                int rndNum=rnd.nextInt(allwords.size()-1);
                String t=allwords.get(rndNum);
                System.out.println(t);
              //  words[i]=allwords.get(4);
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }


        }
        return "  ";
    }

}
