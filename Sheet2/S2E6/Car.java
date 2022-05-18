import java.io.Serializable;
public class Car implements Display, Serializable {

    private String colour;
    private double hpower, weight;

    public Car(String colour, double hpower, double weight) {
        this.colour = colour;
        this.hpower = hpower;
        this.weight = weight;
    }

    public void print() {
        System.out.println("colour: " + colour);
        System.out.println("horsepower: " + hpower);
        System.out.println("weight: " + weight);
    }

}
