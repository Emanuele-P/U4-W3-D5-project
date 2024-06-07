package ep2024.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "catalogue")
public class Catalogue {
    @Id
    @GeneratedValue
    private UUID id;
    private String title;
    private int year;
    private int pages;

    public Catalogue() {
    }

    public Catalogue(String title, int year, int pages) {
        this.title = title;
        this.year = year;
        this.pages = pages;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Catalogue{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", pages=" + pages +
                '}';
    }
}
