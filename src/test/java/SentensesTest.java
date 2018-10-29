import DB.DBClient;
import Models.Lemma;
import org.junit.jupiter.api.Test;


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

    @Test
    public void testGetSentenceTemplateFromText() {
        String sentense= TextParser.getSentenceTemplateFromText("Владимирский вокзал - ветер северный");

        String str = new String("qwqw /neeee");
        System.out.println(str.replaceAll("/n",
                "\n"));
        System.out.println(sentense);
    }
}