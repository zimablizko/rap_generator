import DB.DBConnection;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//Парсер текста песен

class TextParser {

    private static DBConnection db = new DBConnection();
    private static Statement stmt = db.start();

    public static String getSentenceTemplateFromText(String rawText) {
        //TODO: допилить регулярку, чтобы ловила спецсимволы
        String[] wordsArr = rawText.split(" ");
        ArrayList<String> wordList = new ArrayList<String>();
        ArrayList<String> finalList = new ArrayList<String>();
        for (String word : wordsArr) wordList.add(word);
        System.out.println(wordList.size());
        for (String word : wordList) {
            try {
                ResultSet rs;
                String query = "select word, pos_id, case_id, tense_id, gender_id, num_id, anim_id, tran_id, spec_id, person_id, incl_id, return_id " +
                        "from words where word=lower('" + word + "')";
                //System.out.println(query);
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    finalList.add(rs.getString(1));
                }
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }
        return finalList.toString();
    }

    //Методы парсинга текстовых файлов целиком для изменения популярности слов

    public static void increasePopularityWordsBySong(String fileName) {
        ArrayList<Integer> xmlIdList = getWordsIdsFromFile(fileName);
        if (!xmlIdList.isEmpty()) {
            try {
                String query = "update lemmas set popularity = popularity + 1 " +
                        "where lemma_id in(" + xmlIdList.toString().substring(1, xmlIdList.toString().length() - 1) + ")";
                stmt.execute(query);
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }
    }

    public static ArrayList<Integer> getWordsIdsFromFile(String fileName) {
        ArrayList<Integer> xmlIdList = new ArrayList<Integer>();
        ArrayList<String> songWords = getWordsFromFile(fileName);
        String query = "select DISTINCT xml_id from words where word in (";

        for (String word : songWords) {

            query += "'" + word + "',";
        }
        query=query.substring(0, query.length() - 1);
        query+=")";
        try {
            ResultSet rs;
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                xmlIdList.add(rs.getInt(1));
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        System.out.println(xmlIdList.toString());
        return xmlIdList;
    }

    public static ArrayList<String> getWordsFromFile(String fileName) {
        ArrayList<String> songWords = new ArrayList<String>();
        String tmpFileName = clearLyrics(fileName);
        try (Scanner scanner = new Scanner(new File(tmpFileName))) {
            while (scanner.hasNext()) {
                songWords.add(scanner.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return songWords;
    }

    public static String clearLyrics(String fileName) {
        FileWriter writer = null;
        String tmpFileName = fileName.substring(0, fileName.indexOf(".")) + ".tmp.txt";
        try {
            writer = new FileWriter(tmpFileName, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.length() > 0) {
                    if (!line.substring(0, 1).contains("[")) { //пропускаем первую строчку
                        line = line.toLowerCase();
                        line = line.replaceAll(",", " ");
                        line = line.replaceAll("-", "");
                        line = line.replaceAll("—", "");
                        line = line.replaceAll("\"", "");
                        line = line.replaceAll("\'", "");
                        line = line.replaceAll("\\.", "");
                        line = line.replaceAll("\\?", "");
                        line = line.replaceAll("\\!", "");
                        line = line.replaceAll(":", "");

                        writer.write(line);
                        writer.append('\n');

                    }
                }
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tmpFileName;
    }

    //Методы парсинга текстовых файлов построчно для добавления в базу

    //COMING SOON
}

