import java.util.*;

public class Main {
    public final static Scanner sc = new Scanner(System.in);
    public static BookList bookList;

    public static int getInt(String msg, int min, int max) {
        int result;
        do {
            try {
                System.out.println(msg);
                String in = sc.nextLine();
                if (checkInt(in) != -1) {
                    return checkInt(in);
                }
                result = Integer.parseInt(in);
                if (result < min || result > max) {
                    System.err.println("Invalid number. Number must be between " + min + " and " + max);
                    continue;
                }
                return result;
            } catch (Exception e) {
                System.err.println("Invalid format number. Please try again!");
            }
        } while (true);
    }

    public static int checkInt(String in) {
        if (in.matches("\\d+[\\.][0]+")) {
            return Integer.parseInt(in.substring(0, in.indexOf(".")));
        }
        return -1;
    }

    public static void displayBookListMenu() {
        int choice;
        do {
            choice = getInt("\n========== Book List Management =========="
                    + "\n1. Load data from file"
                    + "\n2. Input & add to the end"
                    + "\n3. Display all books"
                    + "\n4. Save book list to file"
                    + "\n5. Search by bcode"
                    + "\n6. Delete by bcode"
                    + "\n7. Sort by bcode"
                    + "\n8. Input & add to the beginning"
                    + "\n9. Input & add after position k"
                    + "\n10. Delete by position k"
                    + "\n11. Search by title"
                    + "\n12. Exit"
                    + "\nEnter your choice: ", 1, 12);

            switch (choice) {
                case 1:
                    System.out.println("Loading data from file...");
                    bookList.loadDataFromFile("resource/Book.txt");
                    break;

                case 2:
                    System.out.println("Enter book details to add");
                    Book newBook = bookList.inputBookDetails(sc);
                    bookList.addLast(newBook);
                    break;

                case 3:
                    System.out.println("Displaying all books:");
                    bookList.display();
                    break;

                case 4:
                    System.out.println("Saving book list to file...");
                    bookList.saveToFile("resource/Book.txt");
                    System.out.println("Save successful!");
                    break;

                case 5:
                    System.out.print("Enter bcode to search: ");
                    String bcode = sc.nextLine();
                    Book foundBook = bookList.searchByBcode(bcode);
                    if (foundBook != null) {
                        System.out.println("Book found: " + foundBook);
                    } else {
                        System.out.println("Book not found!");
                    }
                    break;

                case 6:
                    System.out.print("Enter bcode to delete: ");
                    String deleteBcode = sc.nextLine();
                    bookList.removeByBcode(deleteBcode);
                    break;

                case 7:
                    System.out.println("Sorting books by bcode...");
                    bookList.sortByBcode();
                    break;

                case 8:
                    System.out.println("Enter book details to add to the beginning:");
                    Book firstBook = bookList.inputBookDetails(sc);
                    bookList.addFirst(firstBook);
                    break;

                case 9:
                    System.out.print("Enter position k: ");
                    int k = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    Book bookAfterK = bookList.inputBookDetails(sc);
                    bookList.addAfterPositionK(k, bookAfterK);
                    break;

                case 10:
                    System.out.print("Enter position k to delete: ");
                    int positionToDelete = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    bookList.deleteByPositionK(positionToDelete);
                    break;

                case 11:
                    System.out.print("Enter title to search: ");
                    String title = sc.nextLine();
                    bookList.searchByTitle(title);
                    break;

                case 12:
                    System.out.println("Returning to the main menu...");
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        } while (true);
    }

    public static void displayLendingListMenu() {
        int choice;
        LendingList le = new LendingList();
        do {
            choice = getInt("\n========== Lending List Management =========="
                    + "\n1. Load data from file"
                    + "\n2. Input & add to the end"
                    + "\n3. Display all lending books"
                    + "\n4. Save lending list to file"
                    + "\n5. Search by bcode"
                    + "\n6. Delete by bcode"
                    + "\n7. Sort by bcode"
                    + "\n8. Exit"
                    + "\nEnter your choice: ", 1, 8);

            switch (choice) {
                case 1:
                    System.out.println("Loading data from file...");
                    // Replace with correct file path for lending data
                   le.loadFromFile("resource/Lendings.txt");
                    break;

                case 2:
                    System.out.println("Enter lending details to add");
                    // Input lending details and add
                    break;

                case 3:
                    System.out.println("Displaying all lending records...");
                    
                    break;

                case 4:
                    System.out.println("Saving lending list to file...");
                    // Save lending list to file
                    le.saveToFile("resource/Lendings.txt");
                    break;

                case 5:
                    System.out.print("Enter bcode to search: ");
                    // Search lending records by bcode
                    break;

                case 6:
                    System.out.print("Enter bcode to delete: ");
                    // Delete lending by bcode
                    break;

                case 7:
                    System.out.println("Sorting lending records by bcode...");
                    // Sort lending list
                    break;

                case 8:
                    System.out.println("Returning to the main menu...");
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        } while (true);
    }

    public static void displayReaderListMenu() {
        int choice;
        do {
            choice = getInt("\n========== Reader List Management =========="
                    + "\n1. Load data from file"
                    + "\n2. Input & add to the end"
                    + "\n3. Display all readers"
                    + "\n4. Save reader list to file"
                    + "\n5. Search by rcode"
                    + "\n6. Delete by rcode"
                    + "\n7. Sort by rcode"
                    + "\n8. Exit"
                    + "\nEnter your choice: ", 1, 8);

            switch (choice) {
                case 1:
                    System.out.println("Loading data from file...");
                    // Replace with correct file path for reader data
                    break;

                case 2:
                    System.out.println("Enter reader details to add:");
                    // Input reader details and add
                    break;

                case 3:
                    System.out.println("Displaying all readers...");
                    // Display reader records
                    break;

                case 4:
                    System.out.println("Saving reader list to file...");
                    // Save reader list to file
                    break;

                case 5:
                    System.out.print("Enter rcode to search: ");
                    // Search reader records by rcode
                    break;

                case 6:
                    System.out.print("Enter rcode to delete: ");
                    // Delete reader by rcode
                    break;

                case 7:
                    System.out.println("Sorting reader records by rcode...");
                    // Sort reader list
                    break;

                case 8:
                    System.out.println("Returning to the main menu...");
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        } while (true);
    }

    public static void main(String[] args) {
      BookList bookList = new BookList();
        bookList.loadDataFromFile("resource/Book.txt");

        // Create instances of LendingList and ReaderList
          LendingList le = new LendingList();
        le.loadFromFile("resource/Lendings.txt");

        ReaderList reader = new ReaderList();
        reader.loadFromFile("resource/Readers.txt");

        int choice;
        while (true) {
            choice = getInt("\n========== Library Management System =========="
                    + "\n1. Manage book list"
                    + "\n2. Manage lending list"
                    + "\n3. Manage reader list"
                    + "\n4. Exit"
                    + "\nEnter your choice: ", 1, 4);

            switch (choice) {
                case 1:
                    displayBookListMenu();
                    break;
                case 2:
                    displayLendingListMenu();
                    break;
                case 3:
                     displayReaderListMenu();
                     break;
                case 4: 
return;
            }
        }
    }
}
