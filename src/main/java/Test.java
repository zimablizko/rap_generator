import ru.narod.macrocosm.mcr.Ray;

public class Test {
    StringBuilder buf = new StringBuilder();
    Ray r=new Ray();
    r.a("тест", buf);
    System.out.print(buf.toString());
}
