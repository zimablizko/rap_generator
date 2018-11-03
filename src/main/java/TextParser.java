import DB.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


class TextParser {

    private static DBConnection db = new DBConnection();
    private static Statement stmt = db.start();

    public static String getSentenceTemplateFromText(String rawText){
        String[] wordsArr = rawText.split(" ");
        ArrayList<String> wordList = new ArrayList<String>( );
        ArrayList<String> finalList = new ArrayList<String>( );
        for (String word: wordsArr) wordList.add(word);
        System.out.println(wordList.size());
        for (String word: wordList){
            try {
                ResultSet rs;
                String query = "select word, pos_id, case_id, tense_id, gender_id, num_id, anim_id, tran_id, spec_id, person_id, incl_id, return_id " +
                        "from words where word=lower('"+word +"')";
                //System.out.println(query);
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    finalList.add(rs.getString(1));
                }
        }   catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }


    }return finalList.toString();}

    public static void increaseWordPopularity(String word){
         ArrayList<Integer> xmlIdList = new ArrayList<Integer>();
        try {
            ResultSet rs;
            String query = "select DISTINCT(xml_id) " +
                    "from words where word=lower('"+word +"')";
            System.out.println(query);
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                xmlIdList.add(rs.getInt(1));
            }
        }   catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        System.out.println(xmlIdList.toString());
        if (!xmlIdList.isEmpty()) {
                try {
                    String query = "update words set popularity = popularity + 1 " +
                            "where xml_id in(" + xmlIdList.toString().substring(1,xmlIdList.toString().length()-1) + ")";
                    stmt.execute(query);
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
        }

    }
}

