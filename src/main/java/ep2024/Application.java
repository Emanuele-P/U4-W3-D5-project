package ep2024;

import ep2024.dao.CatalogueDAO;
import ep2024.dao.LoanDAO;
import ep2024.dao.UserDAO;
import ep2024.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Arrays;
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

        String title = "Fair";
        List<Catalogue> findByTitle = cd.findByTitle(title);
        System.out.println("Catalogue items with title containing: " + title + ":");
        findByTitle.forEach(System.out::println);

        Catalogue e1 = cd.findById(UUID.fromString("174c2c9c-91bc-4c79-a5ae-fb5fc36ae87a"));
        Catalogue e2 = cd.findById(UUID.fromString("a16ff6c7-bc63-43a7-bc1c-32baafb638ca"));
        List<Catalogue> loanItems1 = Arrays.asList(e1, e2);
        User user1 = ud.findById(UUID.fromString("20a63ba0-7fbc-4417-8a54-347ecfccbecb"));
        Loan loan1 = new Loan(user1, LocalDate.now(), LocalDate.now().plusDays(30), null, loanItems1);
//        ld.save(loan1);

        UUID membershipNumber = UUID.fromString("20a63ba0-7fbc-4417-8a54-347ecfccbecb");
        User user = ud.findById(membershipNumber);
        if (user != null) {
            List<Loan> userLoans = ld.findLoansByUser(user.getMembershipNumber());

            System.out.println("Loaned catalogue items for user " + user.getFirstName() + " " + user.getLastName() + ":");
            for (Loan loan : userLoans) {
                loan.getItems().forEach(System.out::println);
            }
        } else {
            System.out.println("User with membership number " + membershipNumber + " not found.");
        }

        Catalogue e3 = cd.findById(UUID.fromString("7c52f459-d650-495e-819e-50375aadaf26"));
        Catalogue e4 = cd.findById(UUID.fromString("fceef0c2-ba29-4a08-9d84-af723fefd95b"));
        List<Catalogue> loanItems2 = Arrays.asList(e3, e4);
        User user2 = ud.findById(UUID.fromString("d06ebec4-35d7-42cf-a238-89b125081b2c"));
        Loan loan2 = new Loan(user2, LocalDate.now().minusDays(40), LocalDate.now().minusDays(10), null, loanItems2);
//        ld.save(loan2);

        List<Loan> expiredLoans = ld.findExpiredLoans();
        System.out.println("Expired loans:");
        expiredLoans.forEach(loan -> {
            System.out.println("Loan ID: " + loan.getId() + ", User: " + loan.getUser().getFirstName() + " " + loan.getUser().getLastName() + ", Expected Return Date: " + loan.getExpectedReturnDate());
        });

        em.close();
        emf.close();
    }
}
