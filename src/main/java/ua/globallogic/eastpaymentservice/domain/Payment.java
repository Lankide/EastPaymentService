package ua.globallogic.eastpaymentservice.domain;

import java.io.Serializable;

public class Payment implements Serializable{
    private User user;
    private String companyFrom;
    private String companyTo;
    private String account;
    private double from;
    private double to;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCompanyFrom() {
        return companyFrom;
    }

    public void setCompanyFrom(String companyFrom) {
        this.companyFrom = companyFrom;
    }

    public String getCompanyTo() {
        return companyTo;
    }

    public void setCompanyTo(String companyTo) {
        this.companyTo = companyTo;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "user=" + user +
                ", companyFrom='" + companyFrom + '\'' +
                ", companyTo='" + companyTo + '\'' +
                ", account='" + account + '\'' +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}
