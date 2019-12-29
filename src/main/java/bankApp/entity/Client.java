package bankApp.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private int id;

    @Column(name = "FIRST_NAME")
    @NotNull(message = "is required!")
    private String firstName;

    @Column(name = "last_name")
    @NotNull(message = "is required!")
    private String lastName;

    @Column(name = "address")
    @NotNull(message = "is required!")
    private String address;

    @Column(name = "phone")
    @NotNull(message = "is required!")
    @Pattern(regexp = "\\+\\d{11}", message = "must match +12345678901")
    private String phone;

    @OneToMany (mappedBy = "client", // "client" - field in ValidateAccount
        orphanRemoval=true, // delete account in List<ValidateAccount> & save this Client object - causes account removal from db (see AccountDaoImpl.delete())
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL) // delete client - delete accounts
    private List<Account> accounts;

    public Client() {
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + firstName + '\'' +
                ", address='" + address + '\'' +
                ", accounts.size=" + accounts.size() +
                '}';
    }
}
