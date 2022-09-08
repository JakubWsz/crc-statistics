package pl.crc.statistics.domain.model;

public class Address {
    private final String streetAddress;
    private final String postalCode;
    private final String cityName;

    public Address(String streetAddress, String postalCode, String cityName) {
        this.streetAddress = streetAddress;
        this.postalCode = postalCode;
        this.cityName = cityName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCityName() {
        return cityName;
    }
}
