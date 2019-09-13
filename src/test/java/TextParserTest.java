import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TextParserTest {


    @Test
    public void testFileReader() {

        ArrayList<String> wordList = TextParser.getWordsFromFile("F:\\GameDev\\Other Projects\\RapGenerator\\rap_generator\\src\\main\\resources\\songs\\1.Чайф - Про бобра и барабан.txt");

        System.out.println(wordList);
    }
    @Test
    public void testClearLyrics() {

        TextParser.clearLyrics("songs/1.Чайф - Про бобра и барабан.txt");

        //System.out.println(sentense);
    }
    @Test
    public void testGetIdWordsFromSong() {

        TextParser.getLemmasIdsFromFile("F:\\GameDev\\Other Projects\\RapGenerator\\rap_generator\\src\\main\\resources\\songs\\1.Чайф - Про бобра и барабан.txt");

        //System.out.println(sentense);
    }
    @Test
    public void testIncreasePopularityWordsBySong() {

        TextParser.increaseLemmasPopularityBySong("songs/2.Би2-Родина");

        //System.out.println(sentense);
    }

}