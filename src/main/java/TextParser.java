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
        for (String word: wordsArr) wordList.add(word);
        for (String word: wordList){
            try {
                ResultSet rs;
                // executing SELECT query
                String query = "select 'word', 'pos_id', 'case_id', 'tense_id', 'gender_id', 'num_id', 'anim_id', 'tran_id', 'spec_id', 'person_id', 'incl_id', 'return_id' " +
                        "from words where word=lowercase(" + word+")";
                System.out.println(query);
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    //allwords.add(rs.getString(1));
                }
        }   catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }


    }return wordList.toString();}
}

