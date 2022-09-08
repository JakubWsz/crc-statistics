package pl.crc.statistics.infrastructure.database.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "car")
public class CarDAO {
    @Id
    private String id;
    private final String domainId;
    private final String brand;
    private final String model;
    private final String carType;
    private final String fuelType;
    private final String gearboxType;
    private final String doorNumber;
    private final Double bootCapacity;
    private final String officeId;

    public CarDAO( String domainId, String brand, String model, String carType, String fuelType,
                  String gearboxType, String doorNumber, Double bootCapacity, String officeId) {
        this.domainId = domainId;
        this.brand = brand;
        this.model = model;
        this.carType = carType;
        this.fuelType = fuelType;
        this.gearboxType = gearboxType;
        this.doorNumber = doorNumber;
        this.bootCapacity = bootCapacity;
        this.officeId = officeId;
    }

    public String getId() {
        return id;
    }

    public String getDomainId() {
        return domainId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getCarType() {
        return carType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getGearboxType() {
        return gearboxType;
    }

    public String getDoorNumber() {
        return doorNumber;
    }

    public Double getBootCapacity() {
        return bootCapacity;
    }

    public String getOfficeId() {
        return officeId;
    }
}
