package pl.crc.statistics.infrastructure.converter;

import org.springframework.core.convert.converter.Converter;
import pl.crc.statistics.infrastructure.database.dao.CarDAO;
import pl.crc.statistics.domain.model.car.Car;

public class CarToCarDAO implements Converter<Car, CarDAO> {
    @Override
    public CarDAO convert(Car car) {
        return new CarDAO(car.getId(), car.getBrand(), car.getModel(), car.getCarType(), car.getFuelType(),
                car.getGearboxType(), car.getDoorNumber(), car.getBootCapacity(), car.getOfficeId());
    }
}
