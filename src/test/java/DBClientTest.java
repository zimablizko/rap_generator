import DB.DBClient;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class DBClientTest {

    //тест получения количества слогов
    @Test
    public void testGetSyllableCount() {
        //Models.Sentenсe sn=new Models.Sentenсe();
        //WordPattern noun = WordPattern.noun(1,1,1,1,0);
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
