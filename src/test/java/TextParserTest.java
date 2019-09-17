import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextParserTest {


    @Test
    public void testFileReader() {

        ArrayList<String> wordList = TextParser.getWordsFromFile("1.Чайф - Про бобра и барабан.txt");

        System.out.println(wordList);
    }
    @Test
    public void testClearLyrics() {

        TextParser.clearLyrics("1.Чайф - Про бобра и барабан.txt");

        //System.out.println(sentense);
    }
    @Test
    public void testGetIdWordsFromSong() {

        TextParser.getLemmasIdsFromFile("1.Чайф - Про бобра и барабан.txt");

        //System.out.println(sentense);
    }
    @Test
    public void testIncreasePopularityWordsBySong() {

        TextParser.increaseLemmasPopularityBySong("songs/2.Би2-Родина");

        //System.out.println(sentense);
    }

    //Тест парсинга предложения в шаблоны
    //Возвращает массив слов в предложении
    @Test
    public void testGetSentenceTemplateFromText() {
        String sentence= TextParser.getSentenceTemplateFromText("Владимирский централ - ветер северный");
        System.out.println(sentence);
    }

    //Тест парсинга файла с текстом на отдельные строки
    //Возвращает массив строк
    @Test
    public void testGetSentencesFromFile() throws IOException {
        List<String> sentences= TextParser.getSentencesFromFile("1.Чайф - Про бобра и барабан.txt");
        String[] array = sentences.toArray(new String[0]);
        for (String sentence : array) System.out.println(sentence);

    }

}