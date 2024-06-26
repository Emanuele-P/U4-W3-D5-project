package ep2024.dao;

import ep2024.entities.Loan;
import ep2024.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class LoanDAO {
    private final EntityManager em;

    public LoanDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Loan loan) {
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(loan);
            transaction.commit();
            System.out.println("----------The loan: " + loan + " has been saved correctly!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Loan findById(UUID id) {
        Loan loan = em.find(Loan.class, id);
        if (loan == null) {
            throw new NotFoundException(id);
        }
        return loan;
    }

    public Loan findByIdAndDelete(UUID id) {
        try {
            EntityTransaction t = em.getTransaction();
            Loan found = em.find(Loan.class, id);
            if (found != null) {
                t.begin();
                em.remove(found);
                t.commit();
                System.out.println("----------The loan with id: " + id + " has been removed");
                return found;
            } else System.out.println("----------The loan has not been found");
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Loan> findLoansByUser(UUID userId) {
        TypedQuery<Loan> query = em.createQuery("SELECT l FROM Loan l WHERE l.user.id = :userId", Loan.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    public List<Loan> findExpiredLoans() {
        TypedQuery<Loan> query = em.createQuery("SELECT l FROM Loan l WHERE l.expectedReturnDate < :currentDate AND l.returnDate IS NULL", Loan.class);
        query.setParameter("currentDate", LocalDate.now());
        return query.getResultList();
    }
}
