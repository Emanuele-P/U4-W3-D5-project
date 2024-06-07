package ep2024.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(name = "loan_catalogue",
            joinColumns = @JoinColumn(name = "loan_id"),
            inverseJoinColumns = @JoinColumn(name = "catalogue_id")
    )
    private List<Catalogue> items;

    @Column(name = "loan_start_date", nullable = false)
    private LocalDate loanStartDate;
    @Column(name = "expected_return_date", nullable = false)
    private LocalDate expectedReturnDate;
    @Column(name = "return_date")
    private LocalDate returnDate;

    public Loan() {
    }

    public Loan(User user, LocalDate loanStartDate, LocalDate expectedReturnDate, LocalDate returnDate, List<Catalogue> items) {
        this.user = user;
        this.loanStartDate = loanStartDate;
        this.expectedReturnDate = expectedReturnDate;
        this.returnDate = returnDate;
        this.items = items;
    }

    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getLoanStartDate() {
        return loanStartDate;
    }

    public void setLoanStartDate(LocalDate loanStartDate) {
        this.loanStartDate = loanStartDate;
    }

    public LocalDate getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(LocalDate expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public List<Catalogue> getItems() {
        return items;
    }

    public void setItems(List<Catalogue> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", user=" + user +
                ", items=" + items +
                ", loanStartDate=" + loanStartDate +
                ", expectedReturnDate=" + expectedReturnDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
