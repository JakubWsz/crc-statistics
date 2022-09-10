package pl.crc.statistics.domain.model.client;

import java.time.LocalDate;

public class Client {
    private final String domainId;
    private final String firstname;
    private final String lastname;
    private final String email;
    private final String password;
    private final LocalDate birthdate;

    public Client(String domainId, String firstname, String lastname, String email, String password,
                  LocalDate birthdate) {
        this.domainId = domainId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.birthdate = birthdate;
    }

    public String getDomainId() {
        return domainId;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    @Override
    public String toString() {
        return "Client{" +
                "domainId='" + domainId + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
}
