package Models;

import lombok.Data;

// Модель, описывающая объект слова и его методы
@Data
public class Word {

    int id;
    int xmlId;
    String value;
    WordPattern wordPattern;
    int stress;
    int syllableCount;
}
