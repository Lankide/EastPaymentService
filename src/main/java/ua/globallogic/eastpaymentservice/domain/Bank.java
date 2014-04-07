package ua.globallogic.eastpaymentservice.domain;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table (name = "bank")
public class Bank implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String name;

    @OneToMany
    @JoinTable(name = "bank_account_of_bank",
               joinColumns = @JoinColumn(name = "bank_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "bank_account_id", referencedColumnName = "id"))
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Account> accounts;

    @OneToMany
    @JoinColumn (name="bank_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Proposal> proposals;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "country_code", referencedColumnName = "code")
    private Country country;

    @Column (name = "active")
    private boolean active;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public boolean isActive() {
        return active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Collection<Account> accounts) {
        this.accounts = accounts;
    }

    public Collection<Proposal> getProposals() {
        return proposals;
    }

    public void setProposals(Collection<Proposal> proposals) {
        this.proposals = proposals;
    }

//    public boolean getActive() {
//        return active;
//    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", accounts=" + accounts +
                ", proposals=" + proposals +
                ", active=" + active +
                '}';
    }
}