public class Book extends Publication {

    private String author;
    private int ISBN;

    Book(String title, String language, double price, String author, int ISBN) {
        super(title, language, price);
        this.author = author;
        this.ISBN = ISBN;
    }

    public void print() {
        super.print();
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + ISBN);
    }

}
