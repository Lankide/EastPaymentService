package ua.globallogic.eastpaymentservice.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "company")
public class Company implements Serializable{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column (name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "country_code", referencedColumnName = "code")
    private Country country;

    @Column (name = "city")
    private String city;

    @Column (name = "address")
    private String address;

    @Column (name = "phone_number")
    private String phoneNumber;

    @Column (name = "active")
    private boolean active;

    @Column (name = "mechanism_id")
    private Integer mechanismId;


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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Integer getMechanismId() {
        return mechanismId;
    }

    public void setMechanismId(Integer mechanismId) {
        this.mechanismId = mechanismId;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country=" + country +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", active=" + active +
                ", mechanismId=" + mechanismId +
                '}';
    }
}
