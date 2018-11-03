import org.junit.jupiter.api.Test;

public class TextParserTest {


    @Test
    public void testFileReader() {

        TextParser.fileReader("songs/1.Чайф - Про бобра и барабан.txt");

        //System.out.println(sentense);
    }
    @Test
    public void testClearLyrics() {

        TextParser.clearLyrics("songs/1.Чайф - Про бобра и барабан.txt");

        //System.out.println(sentense);
    }
    @Test
    public void testGetIdWordsFromSong() {

        TextParser.getIdWordsFromSong("songs/1.Чайф - Про бобра и барабан.txt");

        //System.out.println(sentense);
    }
    @Test
    public void testIncreasePopularityWordsBySong() {

        TextParser.increasePopularityWordsBySong("songs/2.Би2-Родина");

        //System.out.println(sentense);
    }

}