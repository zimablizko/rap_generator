import DB.DBClient;
import Models.Sentenсe;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SentenсeTest {

   /* @Test
    public void testGenerate() {
        Sentenсe sn=new Sentenсe();
        String sentence=sn.generate();
        System.out.println(sentence);
    }*/

    //Тест генерации предложений из шаблонов
    @Test
    public void testGetSentenceByTemplate() {
        //Models.Sentenсe sn=new Models.Sentenсe();
        //WordPattern noun = WordPattern.noun(1,1,1,1,0);
        String sentence= DBClient.getSentenceByTemplate(10);
        System.out.println(sentence);
        String sentence2= DBClient.getSentenceByTemplate(11);
        System.out.println(sentence2);
        String sentence3= DBClient.getSentenceByTemplate(12);
        System.out.println(sentence3);
        String sentence4= DBClient.getSentenceByTemplate(13);
        System.out.println(sentence4);
    }

    //Тест парсинга предложения в шаблоны
    @Test
    public void testGetSentenceTemplateFromText() {
        String sentence= TextParser.getSentenceTemplateFromText("Владимирский централ - ветер северный");
        System.out.println(sentence);
    }

    //Тест парсинга файла с текстом на отдельные строки
    @Test
    public void testGetSentencesFromFile() throws IOException {
        List<String> sentences= TextParser.getSentencesFromFile("1.Чайф - Про бобра и барабан.txt");
        String[] array = sentences.toArray(new String[0]);
        for (String sentence :array) System.out.println(sentence);

    }
}