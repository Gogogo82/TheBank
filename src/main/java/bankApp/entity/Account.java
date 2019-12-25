package bankApp.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private String id;

    @Column(name = "number")
    private String number;

    @Column(name = "amount")
    private double amount;

    // nullable - if client deleted
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "owner_id")
    private Client client;
//
//    // cascade: not delete transactions if account deleted
//    @OneToMany(mappedBy = "accountTo",
//            fetch = FetchType.LAZY,
//            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
//    private List<Transaction> transactionsTo;
//
//    @OneToMany(mappedBy = "accountFrom",
//            fetch = FetchType.LAZY,
//            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
//    private List<Transaction> transactionsFrom;
//
    public Account() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
//
//    public List<Transaction> getTransactionsTo() {
//        return transactionsTo;
//    }
//
//    public void setTransactionsTo(List<Transaction> transactionsTo) {
//        this.transactionsTo = transactionsTo;
//    }
//
//    public List<Transaction> getTransactionsFrom() {
//        return transactionsFrom;
//    }
//
//    public void setTransactionsFrom(List<Transaction> transactionsFrom) {
//        this.transactionsFrom = transactionsFrom;
//    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    //TODO: check toString in entities
    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", amount=" + amount +
                ", client=" + client +
                '}';
    }
}
