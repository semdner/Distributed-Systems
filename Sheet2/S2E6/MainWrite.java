import java.util.ArrayList;
import java.io.*;

public class MainWrite {

    public static void main(String[] args) {

        ArrayList<Display> arr = new ArrayList<Display>();
        arr.add(new Publication("Harry Potter 1", "English", 5.99));
        arr.add(new Book("Harry Potter 2", "English", 5.99, "J.K Rowling", 123345));
        arr.add(new Car("White", 200, 1500));

        try {
            FileOutputStream fs = new FileOutputStream("file.ser");
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(arr);
            os.close();
        } catch(IOException e) {
            System.err.println(e.toString());
        }

    }

}
