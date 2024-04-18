
import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private String title;
    private boolean available;

    public Book(String title) {
        this.title = title;
        this.available = true; // Initially, the book is available
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return available;
    }

    public void borrow() {
        if (available) {
            available = false;
            System.out.println("You have borrowed the book: " + title);
        } else {
            System.out.println("Sorry, the book is not available for borrowing.");
        }
    }

    public void returnBook() {
        available = true;
        System.out.println("You have returned the book: " + title);
    }
}

class Library {
    private String address;
    private ArrayList<Book> books;

    public Library(String address) {
        this.address = address;
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void printAvailableBooks() {
        System.out.println("Available books at " + address + ":");
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println("- " + book.getTitle());
            }
        }
    }

    public void borrowBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                book.borrow();
                return;
            }
        }
        System.out.println("Sorry, the book \"" + title + "\" is not available in this library.");
    }

    public void returnBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                book.returnBook();
                return;
            }
        }
        System.out.println("You can't return a book that doesn't belong to this library.");
    }
}

public class LibrarySystem {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Create libraries
        Library library1 = new Library("Mountain Caves Library 1");
        Library library2 = new Library("Mountain Caves Library 2");

        // Add books to libraries
        library1.addBook(new Book("Java Programming"));
        library1.addBook(new Book("Python Basics"));
        library1.addBook(new Book("Data Structures and Algorithms"));

        library2.addBook(new Book("Introduction to Artificial Intelligence"));
        library2.addBook(new Book("Web Development with JavaScript"));
        library2.addBook(new Book("Machine Learning"));

        int choice;
        do {
            System.out.println();
            System.out.println(
                    "\n------------------------------------------------------------\nWelcome to Mountain Caves Library System");
            System.out.println("------------------------------------------------------------");
            System.out.println("1. Print available books");
            System.out.println("2. Borrow a book");
            System.out.println("3. Return a book");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    printAvailableBooks(library1);
                    printAvailableBooks(library2);
                    break;
                case 2:
                    borrowBook(scanner, library1);
                    borrowBook(scanner, library2);
                    break;
                case 3:
                    returnBook(scanner, library1);
                    returnBook(scanner, library2);
                    break;
                case 4:
                    System.out.println("Thank you for using Mountain Caves Library System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }

    private static void printAvailableBooks(Library library) {
        library.printAvailableBooks();
    }

    private static void borrowBook(Scanner scanner, Library library) {
        scanner.nextLine();
        System.out.print("Enter the title of the book you want to borrow: ");
        String title = scanner.nextLine();
        library.borrowBook(title);

        System.out.println("Press Enter to continue...");
    }

    private static void returnBook(Scanner scanner, Library library) {
        scanner.nextLine();
        System.out.print("Enter the title of the book you want to return: ");
        String title = scanner.nextLine();
        library.returnBook(title);

        System.out.println("Press Enter to continue...");
    }
}
