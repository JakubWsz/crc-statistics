package pl.crc.statistics.infrastructure.database.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import pl.crc.statistics.domain.model.Address;

import java.time.LocalDateTime;

@Document(indexName = "office")
public class OfficeDAO {
    @Id
    private String id;
    private final String domainId;
    private final Address address;
    private final String websiteURL;
    private final String officeCEO;
    private LocalDateTime modificationDate;
    private boolean deleted;

    public OfficeDAO(String domainId, Address address, String websiteURL, String officeCEO,
                     boolean deleted) {
        this.domainId = domainId;
        this.address = address;
        this.websiteURL = websiteURL;
        this.officeCEO = officeCEO;
        this.deleted = deleted;
    }

    public String getId() {
        return id;
    }

    public String getDomainId() {
        return domainId;
    }

    public Address getAddress() {
        return address;
    }

    public String getWebsiteURL() {
        return websiteURL;
    }

    public String getOfficeCEO() {
        return officeCEO;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void markAsDeleted() {
        this.deleted = true;
    }

    public void updateObject(String id) {
        this.id = id;
        this.modificationDate = LocalDateTime.now();
    }
}
