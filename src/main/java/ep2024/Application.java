package ep2024;

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

        List<Catalogue> libraryCatalogue = ElementFactory.generateLibraryCatalogue();
        List<User> users = ElementFactory.generateUsers();

        System.out.println(libraryCatalogue);
        System.out.println(users);

        System.out.println("Hello World!");
    }
}
