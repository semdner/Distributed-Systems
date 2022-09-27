import java.util.ArrayList;

public class Main {

    public static void main(String[] argv) {
        ArrayList<Publication> arr = new ArrayList<Publication>();
        arr.add(new Publication("Harry Potter 1", "English", 5.99));
        arr.add(new Publication("Harry Potter 2", "English", 6.99));
        arr.add(new Book("Harry Potter 3", "English", 5.99, "J.K Rowling", 123345));

        int j = 0;

        for (Publication i : arr) {
            arr.get(j).print();
            j++;
        }

    }


}
