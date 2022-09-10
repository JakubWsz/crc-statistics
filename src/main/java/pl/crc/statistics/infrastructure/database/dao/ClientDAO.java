package pl.crc.statistics.infrastructure.database.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDate;

@Document(indexName = "client")
public class ClientDAO {
    @Id
    private  String id;
    private final String domainId;
    private final String firstname;
    private final String lastname;
    private final String email;
    private final String password;
    private final LocalDate birthdate;

    public ClientDAO(String domainId, String firstname, String lastname, String email, String password,
                     LocalDate birthdate) {
        this.domainId = domainId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.birthdate = birthdate;
    }

    public String getId() {
        return id;
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
}
