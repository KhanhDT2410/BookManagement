import java.io.*;
import java.time.LocalDate;
import java.util.*;



class LendingList {
    private LinkedList<Lending> lendingList;

    public LendingList() {
        lendingList = new LinkedList<>();
    }

    // 3.1 Load data from lendings.txt
    public void loadFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(", ");
                String bcode = data[0];
                String rcode = data[1];
                int state = Integer.parseInt(data[2]);
                LocalDate ldate = LocalDate.parse(data[3]);
                LocalDate rdate = data[4].equals("null") ? null : LocalDate.parse(data[4]);
                lendingList.add(new Lending(bcode, rcode, state, ldate, rdate));
            }
            System.out.println("Lending data loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error loading file: " + e.getMessage());
        }
    }

    // 3.2 Lend book
    public void lendBook(String bcode, String rcode, BookList bookList, ReaderList readerList) {
        Book book = bookList.searchByBcode(bcode);
        Reader reader = readerList.searchByRcode(rcode);

        if (book == null || reader == null) {
            System.out.println("Book or reader not found.");
            return;
        }

        if (book.getQuantity() <= 0) {
            System.out.println("Book not available for lending.");
            return;
        }

        // Set lending information
        Lending newLending = new Lending(bcode, rcode, 1, LocalDate.now(), null);
        lendingList.addFirst(newLending);

        // Update book quantity and lended count
        book.setQuantity(book.getQuantity() - 1);
        book.setLended(book.getLended() + 1);

        System.out.println("Book lent successfully.");
    }

    // 3.3 Display all lending books
    public void displayLendings() {
        if (lendingList.isEmpty()) {
            System.out.println("No lending records available.");
        } else {
            for (Lending lending : lendingList) {
                System.out.println(lending);
            }
        }
    }

    // 3.4 Save lending list to file
    public void saveToFile(String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (Lending lending : lendingList) {
                pw.println(lending.toString());
            }
            System.out.println("Lending list saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving to file: " + e.getMessage());
        }
    }

    // 3.5 Sort by bcode + rcode
    public void sortByBcodeRcode() {
        lendingList.sort(Comparator.comparing((Lending l) -> l.bcode).thenComparing(l -> l.rcode));
        System.out.println("Lending list sorted by bcode and rcode.");
    }

    // 3.6 Return book by bcode and rcode
    public void returnBook(String bcode, String rcode, BookList bookList) {
        for (Lending lending : lendingList) {
            if (lending.bcode.equals(bcode) && lending.rcode.equals(rcode) && lending.state == 1) {
                lending.state = 2;
                lending.rdate = LocalDate.now();

                // Update book quantity
                Book book = bookList.searchByBcode(bcode);
                if (book != null) {
                    book.setQuantity(book.getQuantity() + 1);
                    book.setLended(book.getLended() - 1);
                }

                System.out.println("Book returned successfully.");
                return;
            }
        }
        System.out.println("Lending record not found or book already returned.");
    }
}

