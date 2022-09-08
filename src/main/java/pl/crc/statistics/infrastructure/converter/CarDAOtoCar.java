package pl.crc.statistics.infrastructure.converter;

import org.springframework.core.convert.converter.Converter;
import pl.crc.statistics.domain.model.car.Car;
import pl.crc.statistics.infrastructure.database.dao.CarDAO;

public class CarDAOtoCar implements Converter<CarDAO, Car> {
    @Override
    public Car convert(CarDAO carDAO) {
        return new Car(carDAO.getDomainId(), carDAO.getBrand(), carDAO.getModel(), carDAO.getCarType(),
                carDAO.getFuelType(), carDAO.getGearboxType(), carDAO.getDoorNumber(), carDAO.getBootCapacity(),
                carDAO.getOfficeId());
    }
}
