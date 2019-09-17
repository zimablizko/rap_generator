import Models.Song;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

public class SongParserTest {


    @Test
    public void testCreateSong() throws IOException {
        Song song = SongParser.createSong("1.Чайф - Про бобра и барабан.txt");
        System.out.println(song);
    }

}