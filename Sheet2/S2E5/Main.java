import java.util.ArrayList;

public class Main {

    public static void main(String[] argv) {

        ArrayList<Display> arr = new ArrayList<Display>();
        arr.add(new Publication("Harry Potter 1", "English", 5.99));
        arr.add(new Book("Harry Potter 2", "English", 5.99, "J.K Rowling", 123345));
        arr.add(new Car("Black", 200, 1500));

        int j = 0;

        for (Display i : arr) {
            arr.get(j).print();
            System.out.println();
            j++;
        }

    }

}
