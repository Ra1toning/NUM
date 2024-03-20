import java.util.Date;
public class Book {

    String title;
    String author;
    Date publicationDate;
    String publisher;
    double price;
    int sold;

    Book(String title, String author, Date publicationDate, String publisher, double price, int sold) {
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.publisher = publisher;
        this.price = price;
        this.sold = sold;
    }


    public String getName() {
        return title;
    }

    public void setName(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getPublishingHouse() {
        return publisher;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publisher = publishingHouse;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumberOfBooksSold() {
        return sold;
    }

    public void setNumberOfBooksSold(int sold) {
        this.sold = sold;
    }

    // toString method to represent the book as a string
    @Override
    public String toString() {
        return "Nomiin ner: " + title + "\n" +
                "Zohiolch: " + author + "\n" +
                "Hevlegdsen ognoo: " + publicationDate + "\n" +
                "Hevleliin gazar: " + publisher + "\n" +
                "Nomiin une: " + price + "\n" +
                "Nomiin zaragdsan too: " + sold +"\n";
    }
}