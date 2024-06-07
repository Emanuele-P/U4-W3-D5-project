package ep2024.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
@NamedQuery(name = "find_by_author", query = "SELECT b FROM Book b WHERE LOWER(b.author) = LOWER(:author)")
public class Book extends Catalogue {

    private String author;
    private String genre;

    public Book() {
    }

    public Book(String title, int year, int pages, String author, String genre) {
        super(title, year, pages);
        this.author = author;
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book {" +
                super.toString() +
                " author: '" + author + '\'' +
                ", genre: '" + genre + '\'' +
                '}';
    }
}
