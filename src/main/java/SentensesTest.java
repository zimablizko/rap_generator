import org.junit.jupiter.api.Test;


public class SentensesTest {

    @Test
    public void testGenerate() {
        Sentenses sn=new Sentenses();
        String sentense=sn.generate();
        System.out.println(sentense);
    }
}