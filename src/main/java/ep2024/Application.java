package ep2024;

import ep2024.dao.CatalogueDAO;
import ep2024.dao.LoanDAO;
import ep2024.dao.UserDAO;
import ep2024.entities.Book;
import ep2024.entities.Catalogue;
import ep2024.entities.ElementFactory;
import ep2024.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.UUID;

public class Application {
    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4-w3-d5");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        CatalogueDAO cd = new CatalogueDAO(em);
        UserDAO ud = new UserDAO(em);
        LoanDAO ld = new LoanDAO(em);

        List<Catalogue> libraryCatalogue = ElementFactory.generateLibraryCatalogue();
//        for (Catalogue catalogue : libraryCatalogue) {
//            cd.save(catalogue);
//        }

        List<User> users = ElementFactory.generateUsers();
//        for (User user : users) {
//            ud.save(user);
//        }

//        Loan loan1 = new Loan();
//        loan1.setUser(users.get(0));
//        loan1.setLoanStartDate(LocalDate.now());
//        loan1.setExpectedReturnDate(LocalDate.now().plusDays(30));
//        loan1.setItems();

        Catalogue foundBook = cd.findById(UUID.fromString("175d1145-9dd0-40b9-8a2e-8fbc406148a5"));
        System.out.println(foundBook);

        Catalogue deleteBook = cd.findByIdAndDelete(UUID.fromString("679e0d0d-a0b2-4218-80ab-6c7538dec3d7"));

        int year = 1904;
        List<Catalogue> findByYear = cd.findByPublicationYear(year);
        System.out.println("Catalogue items published in " + year + ":");
        findByYear.forEach(System.out::println);

        String author = "Isa";
        List<Book> findByAuthor = cd.findByAuthor(author);
        System.out.println("Catalogue items from author {" + author + "}:");
        findByAuthor.forEach(System.out::println);


    }
}
