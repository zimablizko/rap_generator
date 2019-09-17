import Models.Sentenсe;
import Models.Song;
import Models.Word;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SongParser {
    public static Song createSong(String fileName) throws IOException {
        ArrayList<Sentenсe> sentenceList = new ArrayList<>();
        List<String> sentences= TextParser.getSentencesFromFile(fileName);
        for (String sentenceString : sentences){
            ArrayList<Word> wordList = new ArrayList<>();
            String[] words = sentenceString.split(" ");
            for (String word : words) wordList.add(new Word(word));
            sentenceList.add(new Sentenсe(wordList));
        }
        Song song = new Song(sentenceList);
        return song;
    }
}
