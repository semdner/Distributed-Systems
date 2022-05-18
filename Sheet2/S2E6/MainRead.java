import java.lang.reflect.Array;
import java.util.ArrayList;
import java.io.*;

public class MainRead {

    public static void main(String[] args) {

        ArrayList<Display> arr = new ArrayList<Display>();

        try {
            FileInputStream fs = new FileInputStream("file.ser");
            ObjectInputStream is = new ObjectInputStream(fs);
            arr = (ArrayList<Display>)is.readObject();

            int j = 0;

            for (Display i : arr) {
                arr.get(j).print();
                System.out.println();
                j++;
            }
        } catch(IOException | ClassNotFoundException e) {
            System.err.println(e.toString());
        }

    }

}
