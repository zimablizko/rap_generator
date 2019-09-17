import Models.Song;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SongParserTest {


    @Test
    public void testCreateSong() throws IOException {
        Song song = SongParser.createSong("1.Чайф - Про бобра и барабан.txt");
        System.out.println(song);
    }

    @Test
    public void testAllsongsInOneFile() throws IOException {
        String tmpFileName = "D:\\Program\\Git\\Repo\\rap_generator\\src\\main\\resources\\songs\\allsong.tmp.txt";
        FileWriter writer = new FileWriter(tmpFileName);
        final File folder = new File("D:\\Program\\Git\\Repo\\rap_generator\\src\\main\\resources\\songs\\100");
        for (final File fileEntry : folder.listFiles()) {
            System.out.println(fileEntry.getName());
            try (Scanner scanner = new Scanner(TextParser.class.getResourceAsStream("/songs/100/" + fileEntry.getName()))) {
                scanner.nextLine();//пропускаем первую строчку
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.length() > 0 ) {
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
                            line = line.replaceAll("songtext.ru", "");

                            writer.write(line);
                            writer.append(' ');
                        }
                    }
                }
                writer.flush();
            }
        }
    }
}