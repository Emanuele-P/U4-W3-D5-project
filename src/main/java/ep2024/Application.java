package ep2024;

import ep2024.dao.CatalogueDAO;
import ep2024.dao.LoanDAO;
import ep2024.dao.UserDAO;
import ep2024.entities.Catalogue;
import ep2024.entities.ElementFactory;
import ep2024.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

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
        for (User user : users) {
            ud.save(user);
        }

//        Loan loan1 = new Loan();
//        loan1.setUser(users.get(0));
//        loan1.setLoanStartDate(LocalDate.now());
//        loan1.setExpectedReturnDate(LocalDate.now().plusDays(30));
//        loan1.setItems();


        System.out.println("Hello World!");
    }
}
