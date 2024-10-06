public class Book {
    String bcode;
    String title;
    String author;
    String isbn;
    String publisher;
    int quantity;
    int lended; //lended <= quantity
    double price;

    public Book(String bcode, String title, String author, String isbn, String publisher, int quantity, int lended, double price) {
        this.bcode = bcode;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
        this.quantity = quantity;
        this.lended = lended;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%-7s| %-30s| %-20s| %-20s| %-20s| %-10d| %-10d| $%.2f%n", bcode, title,author, isbn, publisher,quantity, lended, price);
    }
    
    public int compareTo(Book other) {
        return this.bcode.compareTo(other.bcode);
    }
    
    
}
