import DB.DBClient;
import Models.Lemma;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class SentensesTest {

    @Test
    public void testGenerate() {
        Sentenses sn=new Sentenses();
        String sentense=sn.generate();
        System.out.println(sentense);
    }
    @Test
    public void testGetWordString() {
        Sentenses sn=new Sentenses();
        Lemma noun = Lemma.noun(1,1,1,1,0);
        String sentense= DBClient.getRandomWord(noun);
        System.out.println(sentense);
    }
    @Test
    public void testGetSentenceByTemplate() {
        //Sentenses sn=new Sentenses();
        //Lemma noun = Lemma.noun(1,1,1,1,0);
        String sentense= DBClient.getSentenceByTemplate(5);
        System.out.println(sentense);
    }

    //Тест парсинга предложения в шаблоны
    @Test
    public void testGetSentenceTemplateFromText() {
        String sentense= TextParser.getSentenceTemplateFromText("Владимирский централ - ветер северный");

        System.out.println(sentense);
    }

    //Тест увеличения рейтинга популярности слова
    @Test
    public void testIncreaseWordPopularity() {

        TextParser.increaseWordPopularity("ёж");

        //System.out.println(sentense);
    }

}