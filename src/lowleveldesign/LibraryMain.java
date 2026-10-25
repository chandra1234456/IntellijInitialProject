package lowleveldesign;

public class LibraryMain {
    public static void main(String[] args) {
  Book obj = new Book("The Pragmatic Programmer", "David Thomas", "978-0135957059");
  boolean param_1 = obj.borrow();
  boolean param_2 = obj.returnBook();
  boolean param_3 = obj.isAvailable();
  String param_4 = obj.getInfo();
        System.out.println(param_1);
        System.out.println(param_2);
        System.out.println(param_3);
        System.out.println(param_4);
  }

}

class Book {
    private final String title;
    private final String isbn;
    private final String author;
    private boolean isAvailable;


    public Book(String title, String author, String isbn) {
        this.author = author;
        this.title = title;
        this.isbn = isbn;
        this.isAvailable = true;
    }

    public boolean borrow() {
        if (!isAvailable) {
            return false;
        }

        isAvailable = false;
        return true;
    }

    public boolean returnBook() {
        if (isAvailable) {
            return false;
        }

        isAvailable = true;
        return true;
    }

    public boolean isAvailable() {
      return isAvailable;
    }

    public String getInfo() {
        return title + " by " + author
                + " (ISBN: " + isbn + ")"
                + " - " + (isAvailable ? "Available" : "Borrowed");
    }
}

