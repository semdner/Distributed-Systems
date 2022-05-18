import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable {

    private String name;
    private double dividend;
    private int quantity;
    private int method;

    private int fund;

    private ArrayList<String> response = new ArrayList<>();

    public Message() {

    }
    public Message(int method, int fund) {
        this.method = method;
        this.fund = fund;
    }

    public Message(String name, double dividend, int quantity, int method, int fund) {
        this.name = name;
        this.dividend = dividend;
        this.quantity = quantity;
        this.method = method;
        this.fund = fund;
    }

    public String getName() {
        return this.name;
    }

    public double getDivident() {
        return this.dividend;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public int getMethod() {
        return this.method;
    }

    public int getFund() {
        return this.fund;
    }

    public ArrayList<String> getResponse() {
        return response;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDividend(double dividend) {
        this.dividend = dividend;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    public void setFund(int fund) {
        this.fund = fund;
    }

    public void setResponse(String response) {
        this.response.add(response);
    }

}
