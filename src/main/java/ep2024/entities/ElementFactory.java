package ep2024.entities;

import com.github.javafaker.Faker;
import ep2024.enums.Release;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class ElementFactory {
    private static Faker faker = new Faker();

    public static List<Catalogue> generateLibraryCatalogue() {
        List<Catalogue> libraryCatalogue = new ArrayList<>();
        libraryCatalogue.addAll(generateBooks());
        libraryCatalogue.addAll(generateArticles());
        return libraryCatalogue;
    }

    public static List<Book> generateBooks() {
        List<Book> books = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            String title = faker.book().title();
            int year = faker.number().numberBetween(1900, 2024);
            int pages = faker.number().numberBetween(100, 900);
            String author = faker.book().author();
            String genre = faker.book().genre();

            Book book = new Book(title, year, pages, author, genre);
            books.add(book);
        }
        return books;
    }

    public static List<Article> generateArticles() {
        List<Article> articles = new ArrayList<>();
        Release[] releases = Release.values();

        for (int i = 0; i < 10; i++) {
            String title = faker.book().title();
            int year = faker.number().numberBetween(1900, 2024);
            int pages = faker.number().numberBetween(30, 250);
            Release release = releases[faker.random().nextInt(releases.length)];

            Article article = new Article(title, year, pages, release);
            articles.add(article);
        }
        return articles;
    }

    public static List<User> generateUsers() {
        List<User> users = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            LocalDate dateOfBirth = faker.date().birthday(18, 90).toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            User user = new User(firstName, lastName, dateOfBirth);
            users.add(user);
        }
        return users;
    }
}
