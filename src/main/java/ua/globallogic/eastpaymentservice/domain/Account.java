package ua.globallogic.eastpaymentservice.domain;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table (name = "bank_account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column (name = "iban")
    private String iban;

    @Column (name = "active")
    private boolean active;

//    @ManyToMany(mappedBy = "accounts")
//    @LazyCollection(LazyCollectionOption.FALSE)
//    private Collection<Bank> banks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

//    public Collection<Bank> getBanks() {
//        return banks;
//    }
//
//    public void setBanks(Collection<Bank> banks) {
//        this.banks = banks;
//    }

    @Override
    public String toString() {
        return "Account{" +
                "active=" + active +
                ", iban='" + iban + '\'' +
                ", id=" + id +
                '}';
    }
}
