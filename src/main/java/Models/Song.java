package Models;

import lombok.Data;
import java.util.ArrayList;

// Модель, описывающая объект песни и его методы
@Data
public class Song {
    ArrayList<Sentenсe> sentenceList = new ArrayList<>();

    public Song (ArrayList<Sentenсe> SentenceList){
        sentenceList = SentenceList;
    }
}
