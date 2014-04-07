package ua.globallogic.eastpaymentservice.domain;

public class UserSearchCriteria {

    private String name;
    private String email;
    private Country country;
    private String phoneNumber;

    public UserSearchCriteria() {
    }

    public UserSearchCriteria(String name) {
        this.name = name;
    }

    public boolean isEmpty() {
        return !((name != null && !name.equals(""))
                || (email != null && !email.equals(""))
                || country != null
                || (phoneNumber != null && !phoneNumber.equals("")));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "UserSearchCriteria{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", country=" + country +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
