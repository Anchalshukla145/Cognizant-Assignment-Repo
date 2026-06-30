import java.util.Arrays;
import java.util.Comparator;

class Book {

    int bookId;
    String title;
    String author;

    Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    void display() {
        System.out.println(bookId + " " + title + " " + author);
    }
}

class Library {

    // Linear Search
    static Book linearSearch(Book[] books, String title) {

        for (Book book : books) {

            if (book.title.equalsIgnoreCase(title)) {
                return book;
            }
        }

        return null;
    }

    // Binary Search
    static Book binarySearch(Book[] books, String title) {

        int low = 0;
        int high = books.length - 1;

        while (low <= high) {

            int mid = (low + high) / 2;

            int result = books[mid].title.compareToIgnoreCase(title);

            if (result == 0) {
                return books[mid];
            } else if (result < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return null;
    }
}

public class LibraryManagementSystem {

    public static void main(String[] args) {

        Book[] books = {

                new Book(101, "Java", "James Gosling"),
                new Book(102, "Python", "Guido van Rossum"),
                new Book(103, "Algorithms", "CLRS"),
                new Book(104, "Data Structures", "Mark Allen")
        };

        // Sort before Binary Search
        Arrays.sort(books, Comparator.comparing(book -> book.title));

        System.out.println("Books in Library:");

        for (Book book : books) {
            book.display();
        }

        System.out.println();

        Book linearResult = Library.linearSearch(books, "Python");

        if (linearResult != null) {
            System.out.println("Linear Search Result:");
            linearResult.display();
        } else {
            System.out.println("Book Not Found");
        }

        System.out.println();

        Book binaryResult = Library.binarySearch(books, "Python");

        if (binaryResult != null) {
            System.out.println("Binary Search Result:");
            binaryResult.display();
        } else {
            System.out.println("Book Not Found");
        }
    }
}