public class Stocks {

    private String name;
    private double dividend;
    private int quantity;

    public Stocks(String name, double dividend, int quantity) {
        this.name = name;
        this.dividend = dividend;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getDividend() {
        return dividend;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
