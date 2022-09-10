package pl.crc.statistics.domain.model.car;

public class Car{
    private final String id;
    private final String brand;
    private final String model;
    private final String carType;
    private final String fuelType;
    private final String gearboxType;
    private final String doorNumber;
    private final Double bootCapacity;
    private final String officeId;
    private final boolean deleted;

    public Car(String domainId, String brand, String model, String carType, String fuelType, String gearboxType,
               String doorNumber, Double bootCapacity, String officeId, boolean deleted) {
        this.id = domainId;
        this.brand = brand;
        this.model = model;
        this.carType = carType;
        this.fuelType = fuelType;
        this.gearboxType = gearboxType;
        this.doorNumber = doorNumber;
        this.bootCapacity = bootCapacity;
        this.officeId = officeId;
        this.deleted = deleted;
    }

    public String getId() {
        return id;
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

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id='" + id + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", carType='" + carType + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", gearboxType='" + gearboxType + '\'' +
                ", doorNumber='" + doorNumber + '\'' +
                ", bootCapacity=" + bootCapacity +
                ", officeId='" + officeId + '\'' +
                '}';
    }
}