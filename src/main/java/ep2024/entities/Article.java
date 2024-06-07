package ep2024.entities;

import ep2024.enums.Release;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "articles")
public class Article extends Catalogue {

    @Column(name = "distribution_frequency")
    private Release release;

    public Article() {
    }

    public Article(String title, int year, int pages, Release release) {
        super(title, year, pages);
        this.release = release;
    }

    public Release getRelease() {
        return release;
    }

    public void setRelease(Release release) {
        this.release = release;
    }

    @Override
    public String toString() {
        return "Article {" +
                super.toString() +
                " release: " + release +
                '}';
    }
}
