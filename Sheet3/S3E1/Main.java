import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Funds f = new Funds("MCSI-Frankfurt");
        f.addStock("PayPal", 5.2, 12);
        f.addStock("Twitter", 7.9, 1);
        f.addStock("Tesla", 5.7, 4);
        ArrayList<Stocks> s = f.getStocks();

        int j = 0;
        for (int i = 0; i < f.getStocks().size(); i++) {
            if(s.get(i).getDividend() >  5) {
                j = j + s.get(i).getQuantity();
            }
        }

        System.out.println("Total number of stock with dividend over 5: " + j);
    }

}
