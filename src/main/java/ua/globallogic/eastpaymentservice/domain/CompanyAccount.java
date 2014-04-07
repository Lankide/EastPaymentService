package ua.globallogic.eastpaymentservice.domain;


public class CompanyAccount extends Account {
    private Integer companyId;
    private String companyName;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "CompanyAccount{" +
                "companyId=" + companyId +
                "} " + super.toString();
    }
}
