import DB.DBClient;
import Models.Lemma;
import org.junit.jupiter.api.Test;

import java.io.IOException;


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
        String sentense= DBClient.getSentenceByTemplate(10);
        System.out.println(sentense);
        String sentense2= DBClient.getSentenceByTemplate(11);
        System.out.println(sentense2);
        String sentense3= DBClient.getSentenceByTemplate(12);
        System.out.println(sentense3);
        String sentense4= DBClient.getSentenceByTemplate(13);
        System.out.println(sentense4);
    }

    @Test
    public void testGetSentenceTemplateFromText() {
        String sentense= TextParser.getSentenceTemplateFromText("Владимирский вокзал - ветер северный");

        String str = new String("qwqw /neeee");
        System.out.println(str.replaceAll("/n",
                "\n"));
        System.out.println(sentense);
    }


    @Test
    public void testGetSyllableCount() {
        //Sentenses sn=new Sentenses();
        //Lemma noun = Lemma.noun(1,1,1,1,0);
        int count= DBClient.getSyllableCount("И твердь не всаживает");
        System.out.println(count);
    }
    @Test
    public void setStressTest() {
        DBClient.setStress();
        System.out.println();
    }
    @Test
    public void setSyllableCountTest() {
        DBClient.setSyllableCount();
        System.out.println();
    }

    @Test
    public void getCSVforStressTest() throws IOException {
        DBClient.getCSVforStress();
        System.out.println();
    }

    @Test
    public void writeStressFromCSVTest() throws IOException {
        DBClient.writeStressFromCSV();
        System.out.println();
    }
}