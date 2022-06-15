package Receiver;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Funds {

    private String name;
    ArrayList<Stocks> stocks = new ArrayList<>();

    public Funds(String name) {
        this.name = name;
    }

    public Stocks searchStock(String name) {
        for (int i = 0; i < stocks.size(); i++) {
            if(stocks.get(i).getName().equals(name)) {
                return stocks.get(i);
            }
        }
        return null;
    }

    public void addStock(String name, double dividend, int quantity) {
        stocks.add(new Stocks(name, dividend, quantity));
    }

    public ArrayList<Stocks> getStocks() {
        return stocks;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getStocksNames() {
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < stocks.size(); i++) {
            names.add(stocks.get(i).getName());
        }
        return names;
    }

}
