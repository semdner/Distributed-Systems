import java.util.HashSet;
import java.util.Iterator;

public class Main {

    public static void main(String[] argv) {
        HashSet hs = new HashSet<String>();

        for (int i = 0; i < argv.length; i++) {
            hs.add(argv[i]);
        }

        Iterator i = hs.iterator();

        while(i.hasNext()) {
            System.out.println(i.next());
        }

    }

}
