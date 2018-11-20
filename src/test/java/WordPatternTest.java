import DB.DBClient;
import Models.WordPattern;
import org.junit.jupiter.api.Test;

public class WordPatternTest {

    //тест получения рандомного существительного
    @Test
    public void testGetRandomNoun() {
        WordPattern noun = WordPattern.noun(1,1,1,1,0);
        String sentence= DBClient.getRandomWord(noun);
        System.out.println(sentence);
    }
}
