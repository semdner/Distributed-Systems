public class Main {

    public static int factorial(int n) {
        int prod = n;
        for (int i = n-1; i >= 1; i--) {
            prod *=  i;
        }
        return prod;
    }

    public static void main(String[] argv) {
        System.out.println(factorial(5));
    }

}
