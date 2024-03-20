import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LibraryCatalog {
    // номын мэдээллийг хадгалахын тулд HashMap үүсгэнээ
    private Map<String, Book> bookByName = new HashMap<>();
    // Хуудасны тооноос хамааран номын нэрийг хадгалахын тулд HashSet үүсгэнээ
    private Map<Integer, Set<String>> bookByPages = new HashMap<>();
    public void addBook(String name, int pages) {
        Book book = new Book(name, pages);
        bookByName.put(name, book);
        bookByPages.computeIfAbsent(pages, k -> new HashSet<>()).add(name);
    }
    public Book searchName(String bookName) {
        return bookByName.get(bookName);
    }
    public Set<String> searchPages(int numPages) {
        return bookByPages.get(numPages);
    }

    public static void main(String[] args) {
        LibraryCatalog catalog = new LibraryCatalog();

        catalog.addBook("Ном1", 100);
        catalog.addBook("Ном2", 200);
        catalog.addBook("Ном3", 300);
        catalog.addBook("Ном4", 400);
        catalog.addBook("Ном5", 200);

        // Номын нэрээр нь хайна
        String searchName = "Ном2";
        Book book = catalog.searchName(searchName);
        if (book != null) {
            System.out.println("Номоор нь хайлт хийхэд: " + book.getName());
        } else {
            System.out.println("Тийм нэртэй ном олдсонгүй ээ! " + searchName);
        }

        // хуудасны тоогоор нь хайлт хийхэд
        int searchPages = 200;
        Set<String> books = catalog.searchPages(searchPages);
        if (books != null) {
            System.out.println(searchPages + " хуудсаар нь хайлт хийхэд: " + books);
        } else {
            System.out.println("Тийм хуудастай ном олдсонгү ээ!");
        }
    }
}

class Book {
    private String name;
    private int pages;

    public Book(String name, int pages) {
        this.name = name;
        this.pages = pages;
    }

    public String getName() {
        return name;
    }

    public int getPages() {
        return pages;
    }
}
