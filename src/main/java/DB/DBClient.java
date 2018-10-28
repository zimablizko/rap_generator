package DB;

import Models.Lemma;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

public class DBClient {


    private static DBConnection db = new DBConnection();
    private static Statement stmt = db.start();

    public static String getRandomWord(Lemma lemma) {

        ArrayList<String> allwords = new ArrayList<String>();
        String word = "";
        try {
            ResultSet rs;
            // executing SELECT query
            String query = "select word from words where pos_id=" + lemma.getPos_id();
            if (lemma.getCase_id() != 0)
                query += " and case_id=" + lemma.getCase_id();
            if (lemma.getTense_id() != 0)
                query += " and tense_id=" + lemma.getTense_id();
            if (lemma.getGender_id() != 0)
                query += " and gender_id=" + lemma.getGender_id();
            if (lemma.getNum_id() != 0)
                query += " and num_id=" + lemma.getNum_id();
            if (lemma.getAnim_id() != 0)
                query += " and anim_id=" + lemma.getAnim_id();
            if (lemma.getTran_id() != 0)
                query += " and tran_id=" + lemma.getTran_id();
            if (lemma.getPerson_id() != 0)
                query += " and person_id=" + lemma.getPerson_id();
            if (lemma.getIncl_id() != 0)
                query += " and incl_id=" + lemma.getIncl_id();
            if (lemma.getSpec_id() != 0) {
                query += " and spec_id=" + lemma.getSpec_id();
            }else{
                query += " and spec_id IS NULL";
            }
            System.out.println(query);
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                allwords.add(rs.getString(1));
            }
            Random rnd = new Random();
            int rndNum = rnd.nextInt(allwords.size() - 1);
            word = allwords.get(rndNum);

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return word;
    }

    public static String getSentenceByTemplate(int templateId) {
        String sentence = "";
        ArrayList<String> wordTemplateIdList = new ArrayList<String>();
        try {
            ResultSet rs;
            // executing SELECT query
            String query = "select word_temp_id from sentences_collection where sentence_temp_id=" + templateId+" order by word_order asc";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                wordTemplateIdList.add(rs.getString(1));
        }
        }
        catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }

        ArrayList<String> wordList = new ArrayList<String>();

        for (String wordTemplateId:wordTemplateIdList
             ) {
            try {
                String word = "";
                ResultSet rs2;
                // executing SELECT query
                String query2 = "select word from words w,words_templates wt where wt.id="+wordTemplateId +
                " and (w.pos_id = wt.pos_id OR wt.pos_id IS NULL)"+
                " and (w.case_id =wt.case_id OR wt.case_id IS NULL)"+
                " and (w.tense_id =wt.tense_id OR wt.tense_id IS NULL)"+
                " and (w.gender_id=wt.gender_id OR wt.gender_id IS NULL)"+
                " and (w.num_id=wt.num_id OR wt.num_id IS NULL)"+
                " and (w.anim_id=wt.anim_id OR wt.anim_id IS NULL)"+
                " and (w.tran_id=wt.tran_id OR wt.tran_id IS NULL)"+
                " and (w.person_id=wt.person_id OR wt.person_id IS NULL)"+
                " and (w.incl_id=wt.incl_id OR wt.incl_id IS NULL)"+
                " and w.spec_id IS NULL";
                rs2 = stmt.executeQuery(query2);
                while (rs2.next()) {
                    wordList.add(rs2.getString(1));
                }
                System.out.println(wordList.toString());
                Random rnd = new Random();
                int rndNum = rnd.nextInt(wordList.size() - 1);
                word = wordList.get(rndNum);
                sentence+=word+" ";
                wordList.clear();
            }
            catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }

        return sentence;

    }
}
