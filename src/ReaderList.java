import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class ReaderList {
    LinkedList<Reader> readers = new LinkedList<>();

    // 2.1 Load data from file
    public void loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            readers = (LinkedList<Reader>) ois.readObject();
            System.out.println("Loaded " + readers.size() + " readers from " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    // 2.4 Save reader list to file
    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(readers);
            System.out.println("Saved " + readers.size() + " readers to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    // 2.2 Add reader
    public void addReader(Scanner sc) {
        System.out.print("Enter rcode: ");
        String rcode = sc.nextLine().trim();

        System.out.print("Enter name: ");
        String name = sc.nextLine().trim();

        int birthYear = getValidBirthYear(sc);

        readers.add(new Reader(rcode, name, birthYear));
        System.out.println("Reader added successfully.");
    }

    // Helper method to get a valid birth year
    private int getValidBirthYear(Scanner sc) {
        int byear;
        while (true) {
            try {
                System.out.print("Enter birth year: ");
                byear = Integer.parseInt(sc.nextLine().trim());
                if (byear <= 0) {
                    System.out.println("Birth year must be a positive integer. Please try again.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid year.");
            }
        }
        return byear;
    }

    // 2.3 Display data
    public void display() {
        if (readers.isEmpty()) {
            System.out.println("No readers to display.");
        } else {
            System.out.println("Reader List:");
            for (Reader reader : readers) {
                System.out.println(reader);
            }
        }
    }

    // 2.5 Search by rcode
    public Reader searchByRcode(String rcode) {
        for (Reader reader : readers) {
            if (reader.getRcode().equals(rcode)) {
                return reader;
            }
        }
        System.out.println("Reader not found.");
        return null;
    }

    // 2.7 Search by name
    public Reader searchByName(String name) {
        for (Reader reader : readers) {
            if (reader.getName().equalsIgnoreCase(name)) {
                return reader;
            }
        }
        System.out.println("Reader not found.");
        return null;
    }

    // 2.6 Delete by rcode
    public void deleteByRcode(String rcode) {
        Reader reader = searchByRcode(rcode);
        if (reader != null) {
            readers.remove(reader);
            System.out.println("Reader deleted successfully.");
        }
    }
}
