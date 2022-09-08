package pl.crc.statistics.domain.model.office;

import pl.crc.statistics.domain.model.Address;

public class Office {
    private final String id;
    private final Address address;
    private final String websiteURL;
    private final String officeCEO;
    private final boolean deleted;

    public Office(String id, Address address, String websiteURL, String officeCEO, boolean deleted) {
        this.id = id;
        this.address = address;
        this.websiteURL = websiteURL;
        this.officeCEO = officeCEO;
        this.deleted = deleted;
    }

    public String getId() {
        return id;
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
}
