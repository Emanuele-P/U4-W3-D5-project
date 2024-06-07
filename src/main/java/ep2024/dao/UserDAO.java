package ep2024.dao;

import ep2024.entities.User;
import ep2024.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class UserDAO {

    private final EntityManager em;

    public UserDAO(EntityManager em) {
        this.em = em;
    }

    public void save(User user) {
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(user);
            transaction.commit();
            System.out.println("----------The user: " + user + " has been saved correctly!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public User findById(UUID id) {
        User user = em.find(User.class, id);
        if (user == null) {
            throw new NotFoundException(id);
        }
        return user;
    }

    public void findByIdAndDelete(UUID id) {
        try {
            EntityTransaction t = em.getTransaction();
            User found = em.find(User.class, id);
            if (found != null) {
                t.begin();
                em.remove(found);
                t.commit();
                System.out.println("----------The user with id: " + id + " has been removed");
            } else System.out.println("----------The user has not been found");


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
