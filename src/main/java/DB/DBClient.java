package DB;

import Models.Lemma;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import javafx.util.Pair;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
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
            } else {
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

    public static String getWordByTemplate(String templateId) {
        String word = "";
        ArrayList<String> wordList = new ArrayList<String>();
        try {
            String query2 = "select word from words w,words_templates wt where wt.id=" + templateId +
                    " and (w.pos_id = wt.pos_id OR wt.pos_id IS NULL)" +
                    " and (w.case_id =wt.case_id OR wt.case_id IS NULL)" +
                    " and (w.tense_id =wt.tense_id OR wt.tense_id IS NULL)" +
                    " and (w.gender_id=wt.gender_id OR wt.gender_id IS NULL)" +
                    " and (w.num_id=wt.num_id OR wt.num_id IS NULL)" +
                    " and (w.anim_id=wt.anim_id OR wt.anim_id IS NULL)" +
                    " and (w.tran_id=wt.tran_id OR wt.tran_id IS NULL)" +
                    " and (w.person_id=wt.person_id OR wt.person_id IS NULL)" +
                    " and (w.incl_id=wt.incl_id OR wt.incl_id IS NULL)" +
                    " and (w.return_id=wt.return_id OR wt.return_id IS NULL)" +
                    " and (w.syllable_count=wt.syllable_count OR wt.syllable_count IS NULL)" +
                    " and ((w.spec_id=wt.spec_id OR wt.spec_id IS NULL) AND (w.spec_id IS NULL OR w.spec_id IN (12,13,14)))";
            ResultSet rs2 = stmt.executeQuery(query2);
            while (rs2.next()) {
                wordList.add(rs2.getString(1));
            }
            Random rnd = new Random();
            if (wordList.size() > 0) {
                int rndNum = rnd.nextInt(wordList.size());
                word = wordList.get(rndNum);

            } else {
                System.out.println("НЕТ СЛОВ !!! " + query2);

            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return word;
    }

    public static String getSentenceByTemplate(int templateId) {
        String sentence = "";
        String endSign = "";
        ArrayList<Pair> wordTemplateIdList = new ArrayList<Pair>();
        try {
            ResultSet rs;
            ResultSet esrs;
            // executing SELECT query
            String query = "select word_temp_id,preposition from sentences_collection where sentence_temp_id=" + templateId + " order by word_order asc";
            String endSignQuery = "select end_sign from sentenсes_templates where id=" + templateId;
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                wordTemplateIdList.add(new Pair(rs.getInt(1), rs.getString(2)));
            }
            esrs = stmt.executeQuery(endSignQuery);
            while (esrs.next()) {
                endSign = esrs.getString(1);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }

        for (Pair wordTemplate : wordTemplateIdList) {
            String word = getWordByTemplate(wordTemplate.getKey().toString());
            String preposition;
            if (wordTemplate.getValue() != null) {
                preposition = wordTemplate.getValue().toString() + " ";
            } else preposition = "";
            sentence += preposition + word + " ";
        }
        sentence = sentence.substring(0, 1).toUpperCase() + sentence.substring(1).toLowerCase();
        sentence = sentence.substring(0, sentence.length() - 1) + endSign;
        sentence = sentence.replaceAll("/n", "\n");
        return sentence;

    }

    public static String getSentenceByTemplate(int templateId, int countSyllable) {
        String sentence = getSentenceByTemplate(templateId);
        int count = getSyllableCount(sentence);
        while (count != countSyllable) {
            sentence = getSentenceByTemplate(templateId);
            count = getSyllableCount(sentence);
            // System.out.println(sentence+"   "+count);

        }
        return sentence;

    }

    public static int getSyllableCount(String text) {
        int syllableCount = 0;
        String vowels = "аиеёоуэыюя";
        text = text.toLowerCase();
        for (int i = 0; i < text.length(); i++) {
            String letter = text.substring(i, i + 1);
            if (vowels.contains(letter)) {
                syllableCount++;
            }
        }

        return syllableCount;
    }


    public static void setStress() {
        ArrayList<Pair> wordList = new ArrayList<Pair>();
        try {
            ResultSet rs;
            ResultSet esrs;
            // executing SELECT query
            String query = "select id, word from words where pos_id=16 and stress is null and id<5000000";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                wordList.add(new Pair(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        for (Pair word : wordList) {
            int count = getSyllableCount(word.getValue().toString());
            if (count == 1) {
                String queryUpdate = "update words set stress=1 where id=" + word.getKey().toString();
                try {
                    stmt.execute(queryUpdate);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println("!!! " + word.getValue().toString());
            }
        }
    }


    public static void setSyllableCount() {
        ArrayList<Pair> wordList = new ArrayList<Pair>();
        try {
            ResultSet rs;
            // executing SELECT query  pos_id in (4,5,6,7,8,9,10) and
            String query = "select id, word from words where syllable_count is null and id<1075000";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                wordList.add(new Pair(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        System.out.println(wordList.size());
        for (Pair word : wordList) {
            int count = getSyllableCount(word.getValue().toString());
            String queryUpdate = "update words set syllable_count=" + count + " where id=" + word.getKey().toString();
            try {
                stmt.execute(queryUpdate);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //       System.out.println("!!! " + word.getValue().toString());
        }
    }

    public static void getCSVforStress() throws IOException {
        ArrayList<String> xmlIds = new ArrayList<String>();
        String csv = "data.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(csv));
        String queryXmlId = "select distinct xml_id from words where stress is null and spec_id is null LIMIT 5";

        try {
            ResultSet rs;
            rs = stmt.executeQuery(queryXmlId);
            while (rs.next()) {
                xmlIds.add(rs.getString(1));
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        for (String xmlId:xmlIds ) {
            System.out.println(xmlId);
            try {
                ResultSet rs;
                String queryLemmas = "select xml_id, id, word,stress from words where xml_id=" + xmlId;
                rs = stmt.executeQuery(queryLemmas);
                while (rs.next()) {
                    String[] record = (rs.getString(1) + " , " + rs.getString(2) + " , " + rs.getString(3) + " , " + rs.getString(4)).split(" , ");
                    writer.writeNext(record);
                }
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }
        writer.close();
    }

    public static void writeStressFromCSV() throws IOException {
        CSVReader reader = new CSVReader(new FileReader("data.csv"), ',', '"', 0);
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            if (nextLine != null) {
                String queryUpdate = "update words set stress=" + nextLine[3] + " where id=" + nextLine[1];
                System.out.println(queryUpdate);
                try {
                    stmt.execute(queryUpdate);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
