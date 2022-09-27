import java.util.Scanner;

public class Main {

    public static void main(String[] argv) {

        int i = 1;

        while(true) {
            Scanner sc = new Scanner(System.in);
            int line = sc.nextInt();
            MyThread mt = new MyThread(i);
            mt.start();
            i++;
        }
    }

}
