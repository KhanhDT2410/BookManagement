
import java.io.*;
import java.util.*;

public class BookList {

    Node<Book> head;
    Node<Book> tail;

    public BookList() {
        head = null;
        tail = null;
    }

    boolean isEmpty() {
        return (head == null);
    }

    void clear() {
        head = null;
        tail = null;
    }

    public int getInt(String msg) {
        Scanner sc = new Scanner(System.in);
        int result;
        do {
            try {
                System.out.println(msg);
                String in = sc.nextLine();
                if (in.matches("\\d+[\\.][0]+")) {
                    return Integer.parseInt(in.substring(0, in.indexOf(".")));
                }
                result = Integer.parseInt(in);
                if (result <= 0) {
                    System.out.println("Invalid number. Number must be greater than or equal to 1");
                    continue;
                }
                return result;
            } catch (NumberFormatException e) {
                System.out.println("Invalid format number. Please try again!");
            }
        } while (true);

    }

    public String getString(String msg) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(msg);
            String in = sc.nextLine().trim();
            if (in.isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
            } else {
                return in;
            }
        }
    }

    public void loadDataFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 8) {
                    String bcode = parts[0];
                    String title = parts[1];
                    String author = parts[2];
                    String isbn = parts[3];
                    String publisher = parts[4];
                    int quantity = Integer.parseInt(parts[5]);
                    int lended = Integer.parseInt(parts[6]);
                    double price = Double.parseDouble(parts[7]);

                    Book book = new Book(bcode, title, author, isbn, publisher, quantity, lended, price);

                    addLast(book);
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    // Method to load existing books from a file into a Set
    public Set<String> loadExistingBooks(String filename) {
        Set<String> existingBooks = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] bookData = line.split(",");
                if (bookData.length == 8) {
                    String bcode = bookData[0];
                    existingBooks.add(bcode); // Add bcode to the set
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        return existingBooks;
    }

    // Method to save new books to the file, avoiding duplicates
    public void saveToFile(String filename) {
        Set<String> existingBooks = loadExistingBooks(filename); // Load existing books
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
            Node<Book> current = head;
            while (current != null) {
                Book book = current.data;

                // Check if the book already exists in the file
                if (!existingBooks.contains(book.bcode)) {
                    bw.write(book.bcode + "," + book.title + "," + book.author + "," + book.isbn + ","
                            + book.publisher + "," + book.quantity + "," + book.lended + "," + book.price);
                    bw.newLine(); // Move to the next line in the file
                    existingBooks.add(book.bcode); // Add to the set to prevent future duplicates
                }

                current = current.next;
            }
            System.out.println("Book list saved successfully to " + filename);
        } catch (Exception e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Display list book
    public void display() {
        if (head == null) {
            System.out.println("Book list is empty.");
            return;
        }
        Node<Book> current = head;
        System.out.printf("%-7s| %-30s| %-20s| %-20s| %-20s| %-10s| %-10s| %s%n",
                "Bcode", "Title", "Author", "Isbn", "Publisher", "Quantity", "Lended", "Price");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");
        while (current != null) {
            System.out.print(current.data);
            current = current.next;
        }
    }
    // Add book to the end of the list
    void addLast(Book x) {
        Node<Book> p = new Node(x);
        if (isEmpty()) {
            head = p;
            tail = p;
        } else {
            tail.next = p;
            tail = p;
        }
    }
    
    // Add book 
    void addFirst(Book x) {
        Node<Book> p = new Node<>(x);
        p.next = head;
        head = p;
        System.out.println("Book added successfully to the beggining");

    }
    
    // Search book by bcode
    public Book searchByBcode(String bcode) {
        Node<Book> current = head;
        while (current != null) {
            if (current.data.bcode.compareToIgnoreCase(bcode) == 0) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }
    
    // Display book searched by bcode
    public void displayByBcode(String bcode) {
        Book foundBook = searchByBcode(bcode);
        if (foundBook != null) {
            System.out.println("Book found: " + foundBook);
        } else {
            System.out.println("Book with bcode " + bcode + " not found.");
        }
    }

    Node getPrev(Node p) {
        if (isEmpty()) {
            return null;
        }
        if (p == head) {
            return null;
        }
        Node q = head;
        while (q != null && q.next != p) {
            q = q.next;

        }
        return q;
    }

    void removeFirst() {
        if (head == tail) {
            clear();
        }
        Node p = head;
        head = head.next;
        p.next = null;
    }

    void removeLast() {
        if (head == tail) {
            clear();
        }

        Node p = head;
        while (p != tail) {
            p = p.next;
        }
        p.next = null;
        tail = p;
    }

    void remove(Book x) {

        if (isEmpty()) {
            System.out.println("List is empty, nothing to remove.");
            return;
        }
        Node<Book> current = head;

        while (current != null) {
            if (current.data.equals(x)) {
                if (current == head) {
                    this.removeFirst();
                } else if (current == tail) {
                    this.removeLast();
                } else {
                    this.getPrev(current).next = current.next;
                }
                return;
            }

            current = current.next;
        }
    }

    public void removeByBcode(String bcode) {
        if (isEmpty()) {
            clear();
        }

        Book bookToRemove = searchByBcode(bcode);
        if (bookToRemove != null) {
            if (bookToRemove.lended > 0) {
                System.out.println("Cannot remove book with bcode " + bcode + " because it is being lent");
            } else {
                remove(bookToRemove);
                System.out.println("Book with bcode " + bcode + " has been removed successfully~~~");
            }
        } else {
            System.out.println("Cannot find book with bcode " + bcode);
        }
    }

    void swap(Node<Book> p, Node<Book> q) {
        Book temp = p.data;
        p.data = q.data;
        q.data = temp;
    }

    public void sortByBcode() {
        Node<Book> p = head;
        while (p != null) {
            Node<Book> q = p.next;
            while (q != null) {
                if (p.data.bcode.compareTo(q.data.bcode) > 0) {
                    swap(p, q);
                }
                q = q.next;
            }
            p = p.next;
        }
    }
    
    // Search book by title
    public void searchByTitle(String title) {
        if (title == null || title.isEmpty()) {
            System.out.println("Please enter a valid title to search");
            return;
        }

        boolean found = false;
        Node<Book> current = head;

        System.out.println("Books with title containing: " + title);

        while (current != null) {
            if (current.data.title.toLowerCase().contains(title.toLowerCase())) {
                System.out.println(current.data);
                found = true;
            }
            current = current.next;
        }

        if (!found) {
            System.out.println("There is not book with title: " + title);
        }

    }
    
    // Input book's information
    public Book inputBookDetails(Scanner sc) {
        String bcode;
        while (true) {
            bcode = getString("Enter bcode:");

            if (searchByBcode(bcode) != null) {
                System.out.println("Bcode already exists. Please enter a different bcode.");
            } else {
                break;
            }
        }
        String title = getString("Enter title:");
        
        String author = getString("Enter author:");

        String isbn = getString("Enter ISBN:");

        String publisher = getString("Enter publisher:");

        int quantity = getInt("Enter quantity:");
        
        int lended;
        while(true) {
        lended = getInt("Enter lended:");
        if(lended > quantity){
            System.out.println("Lended must be less than or equal to quantity~");
        } else {
            break;
        }
        }

        double price;
        while(true){
            System.out.println("Enter price:");
            if(sc.hasNextDouble()){
                price = sc.nextDouble();
                if(price < 0) {
                    System.out.println("Price must be equal or greater than 0");
                    continue;
                }
                break;
            } else {
                System.out.println("Invalid input. Price is digit");
                 sc.next();
            }
                 
        }
        sc.nextLine();

        return new Book(bcode, title, author, isbn, publisher, quantity, lended, price);
    }
    
    // Add book after position k
    public void addAfterPositionK(int k, Book book) {
        if (k < 0) {
            System.out.println("Invalid position. Position must be equal or greater than 0~~");
        }
        Node<Book> current = head;
        int index = 0;

        while (current != null && index < k) {
            current = current.next;
            index++;
        }

        if (current == null) {
            System.out.println("Position " + k + " is out of bounds.");
            return;
        }

        Node<Book> newNode = new Node<>(book);
        newNode.next = current.next;  
        current.next = newNode;      

        System.out.println("Book added successfully after position " + k);
    }
    
    // Get book base on index
    Node<Book> getByIndex(int index) {
        Node<Book> p = head;
        int i = 0;
        while (i != index) {
            p = p.next;
            i++;
        }
        return p;
    }

    // Delete the book to the position k of the list
    public void deleteByPositionK(int k) {
        Node<Book> p = this.getByIndex(k);
        this.remove(p.data);
    }

}
