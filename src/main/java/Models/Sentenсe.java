package Models;

import DB.DBConnection;
import lombok.Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

//класс, описывающий предложения и его методы
@Data
public class Sentenсe {
   int id;
   String endSign;
   String sample;

   ArrayList<Word> wordList = new ArrayList<>();

   public Sentenсe (ArrayList<Word> WordList){
      wordList = WordList;
   }

   /*  public static int[] partsOfSpeech = {2, 1, 3};
   private static DBConnection db = new DBConnection();
    private static Statement stmt = db.start();*/

  /* public String generate() {
        for (int i = 0; i < partsOfSpeech.length; i++) {
            ArrayList<String> allwords = new ArrayList<String>();
            try {
                ResultSet rs;
                String query = "select word from words where pos_id=" + partsOfSpeech[i];
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    allwords.add(rs.getString(1));
                }
                Random rnd = new Random();
                int rndNum = rnd.nextInt(allwords.size() - 1);
                String t = allwords.get(rndNum);
                System.out.println(t);
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }
        return "  ";
    }*/


}