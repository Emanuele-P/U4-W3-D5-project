package ep2024.dao;

import ep2024.entities.Book;
import ep2024.entities.Catalogue;
import ep2024.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
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

    public Catalogue findByIdAndDelete(UUID id) {
        try {
            EntityTransaction t = em.getTransaction();
            Catalogue found = em.find(Catalogue.class, id);
            if (found != null) {
                t.begin();
                em.remove(found);
                t.commit();
                System.out.println("----------The item with id: " + id + " has been removed");
                return found;
            } else System.out.println("----------The item with id: " + id + " has not been found");
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Catalogue> findByPublicationYear(int year) {
        TypedQuery<Catalogue> query = em.createNamedQuery("find_by_publication_year", Catalogue.class);
        query.setParameter("year", year);
        return query.getResultList();
    }

    public List<Book> findByAuthor(String author) {
        TypedQuery<Book> query = em.createNamedQuery("find_by_author", Book.class);
        query.setParameter("author", author.toLowerCase());
        return query.getResultList();
    }

    public List<Catalogue> findByTitle(String title) {
        TypedQuery<Catalogue> query = em.createNamedQuery("find_by_title", Catalogue.class);
        query.setParameter("nTitle", "%" + title.toLowerCase() + "%");
        return query.getResultList();
    }

}
