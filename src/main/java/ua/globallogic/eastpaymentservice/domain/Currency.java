package ua.globallogic.eastpaymentservice.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table (name = "currency")
public class Currency implements Serializable{

    @Id
    @Column(name = "code")
    private Integer currencyCode;

    @Column (name = "name")
    private String currencyName;

    public Integer getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(Integer currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "currencyCode=" + currencyCode +
                ", currencyName='" + currencyName + '\'' +
                '}';
    }
}
