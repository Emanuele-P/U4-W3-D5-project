package ep2024.dao;

import ep2024.entities.Catalogue;
import ep2024.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class CatalogueDAO {

    private final EntityManager em;

    public CatalogueDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Catalogue catalogue) {
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(catalogue);
            transaction.commit();
            System.out.println("----------The item: " + catalogue + " has been saved correctly!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Catalogue findById(UUID id) {
        Catalogue catalogue = em.find(Catalogue.class, id);
        if (catalogue == null) {
            throw new NotFoundException(id);
        }
        return catalogue;
    }

    public void findByIdAndDelete(UUID id) {
        try {
            EntityTransaction t = em.getTransaction();
            Catalogue found = em.find(Catalogue.class, id);
            if (found != null) {
                t.begin();
                em.remove(found);
                t.commit();
                System.out.println("----------The item with id: " + id + " has been removed");
            } else System.out.println("----------The item has not been found");


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}