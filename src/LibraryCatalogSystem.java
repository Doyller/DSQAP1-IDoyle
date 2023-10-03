import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class LibraryCatalogSystem {
    public static class Library {
        private List<Book> catalog;

        public Library() {
            catalog = new ArrayList<>();
        }

        public void addBook(String title, String author, String isbn) {
            catalog.add(new Book(title, author, isbn));
            System.out.println("Book added to the catalog.");
        }

        public void searchForBook(String query) {
            boolean found = false;
            for (Book book : catalog) {
                if (book.matches(query)) {
                    book.displayInfo();
                    found = true;
                }
            }
            if (!found) {
                System.out.println("Book not found in the catalog.");
            }
        }

        public void checkoutBook(String isbn) {
            for (Book book : catalog) {
                if (book.getIsbn().equals(isbn)) {
                    if (book.isAvailable()) {
                        book.checkout();
                        System.out.println("Book checked out successfully.");
                    } else {
                        System.out.println("Book is already checked out.");
                    }
                    return;
                }
            }
            System.out.println("Book not found in the catalog.");
        }

        public void returnBook(String isbn) {
            for (Book book : catalog) {
                if (book.getIsbn().equals(isbn)) {
                    if (!book.isAvailable()) {
                        book.returnBook();
                        System.out.println("Book returned successfully.");
                    } else {
                        System.out.println("Book is already in the library.");
                    }
                    return;
                }
            }
            System.out.println("Book not found in the catalog.");
        }
    }

    public static class Book {
        private String title;
        private String author;
        private String isbn;
        private boolean available;

        public Book(String title, String author, String isbn) {
            this.title = title;
            this.author = author;
            this.isbn = isbn;
            this.available = true; // Initially, all books are available
        }

        public boolean matches(String query) {
            return title.contains(query) || author.contains(query) || isbn.contains(query);
        }

        public String getIsbn() {
            return isbn;
        }

        public boolean isAvailable() {
            return available;
        }

        public void checkout() {
            available = false;
        }

        public void returnBook() {
            available = true;
        }

        public void displayInfo() {
            System.out.println("Title: " + title);
            System.out.println("Author: " + author);
            System.out.println("ISBN: " + isbn);
            System.out.println("Availability: " + (available ? "Available" : "Checked Out"));
        }
    }

    public static void main(String[] args) {
        Library library = new Library();
        library.addBook("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565");
        library.addBook("To Kill a Mockingbird", "Harper Lee", "9780061120084");
        library.addBook("1984", "George Orwell", "9780451524935");

        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\nWelcome to Doyle's Library Catalog System");
            System.out.println("1. Want to search for a book? Select 1. ");
            System.out.println("2. Want to checkout a book? Select 2. ");
            System.out.println("3. Need to return a book? Select 3");
            System.out.println("4. Add a book to the catalog *ADMIN ONLY*");
            System.out.println("5. Exit");
            System.out.print("Please enter your number selection: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter search query (title, author, or ISBN): ");
                    String query = scanner.nextLine();
                    library.searchForBook(query);
                    break;
                case 2:
                    System.out.print("Enter ISBN of the book to checkout: ");
                    String checkoutIsbn = scanner.nextLine();
                    library.checkoutBook(checkoutIsbn);
                    break;
                case 3:
                    System.out.print("Enter ISBN of the book to return: ");
                    String returnIsbn = scanner.nextLine();
                    library.returnBook(returnIsbn);
                    break;
                case 4:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter ISBN: ");
                    String isbn = scanner.nextLine();
                    library.addBook(title, author, isbn);
                    break;
                case 5:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
