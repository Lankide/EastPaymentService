package ua.globallogic.eastpaymentservice.domain;


public class BankAccount extends Account {
    private Integer bankId;
    private String bankName;

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "bankId=" + bankId +
                "} " + super.toString();
    }
}
