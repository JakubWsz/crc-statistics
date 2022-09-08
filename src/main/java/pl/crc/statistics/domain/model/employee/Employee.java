package pl.crc.statistics.domain.model.employee;

import pl.crc.statistics.domain.model.Address;

import java.math.BigDecimal;

public class Employee {
    private String id;
    private final String firstname;
    private final String lastname;
    private final Address address;
    private final String pesel;
    private final String accountNumber;
    private final BigDecimal salaryAmount;
    private final String contractType;
    private final String position;
    private final String officeId;
    private final boolean deleted;

    public Employee(String id, String firstname, String lastname, Address address, String pesel, String accountNumber,
                    BigDecimal salaryAmount, String contractType, String position, String officeId, boolean deleted) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.pesel = pesel;
        this.accountNumber = accountNumber;
        this.salaryAmount = salaryAmount;
        this.contractType = contractType;
        this.position = position;
        this.officeId = officeId;
        this.deleted = deleted;
    }

    public String getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Address getAddress() {
        return address;
    }

    public String getPesel() {
        return pesel;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getSalaryAmount() {
        return salaryAmount;
    }

    public String getContractType() {
        return contractType;
    }

    public String getPosition() {
        return position;
    }

    public String getOfficeId() {
        return officeId;
    }

    public boolean isDeleted() {
        return deleted;
    }
}
