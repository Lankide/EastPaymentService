package ua.globallogic.eastpaymentservice.domain;




import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "mechanism")
public class Mechanism implements Serializable{

    @Id
    @Column (name = "id")
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;

    @Column (name = "name")
    private String name;

    @Column (name = "our_commission")
    private Double ourCommission;

    @Column (name = "fee")
    private Double fee;

    @Column (name = "bank_id")
    private Integer bankId;

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

    public Double getOurCommission() {
        return ourCommission;
    }

    public void setOurCommission(Double ourCommission) {
        this.ourCommission = ourCommission;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    @Override
    public String toString() {
        return "Mechanism{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ourCommission=" + ourCommission +
                ", fee=" + fee +
                ", bankId=" + bankId +
                '}';
    }
}
