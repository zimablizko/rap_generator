import DB.DBClient;
import Models.Sentenсe;
import org.junit.jupiter.api.Test;

public class SentenсeTest {

    @Test
    public void testGenerate() {
        Sentenсe sn=new Sentenсe();
        String sentence=sn.generate();
        System.out.println(sentence);
    }

    //Тест генерации предложений из шаблонов
    @Test
    public void testGetSentenceByTemplate() {
        //Models.Sentenсe sn=new Models.Sentenсe();
        //WordPattern noun = WordPattern.noun(1,1,1,1,0);
        String sentense= DBClient.getSentenceByTemplate(10);
        System.out.println(sentense);
        String sentense2= DBClient.getSentenceByTemplate(11);
        System.out.println(sentense2);
        String sentense3= DBClient.getSentenceByTemplate(12);
        System.out.println(sentense3);
        String sentense4= DBClient.getSentenceByTemplate(13);
        System.out.println(sentense4);
    }

    //Тест парсинга предложения в шаблоны
    @Test
    public void testGetSentenceTemplateFromText() {
        String sentense= TextParser.getSentenceTemplateFromText("Владимирский централ - ветер северный");
        System.out.println(sentense);
    }
}