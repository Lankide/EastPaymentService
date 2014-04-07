package ua.globallogic.eastpaymentservice.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "bank_proposal")
public class Proposal implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "currency_code", referencedColumnName = "code")
    private Currency currency;

    @Column (name = "commission")
    private double commission;

//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinColumn(name = "bank_id", referencedColumnName = "id")
//    private Bank bank;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

//    public Bank getBank() {
//        return bank;
//    }
//
//    public void setBank(Bank bank) {
//        this.bank = bank;
//    }

    @Override
    public String toString() {
        return "Proposal{" +
                "commission=" + commission +
                ", currency=" + currency +
                ", id=" + id +
                '}';
    }
}
