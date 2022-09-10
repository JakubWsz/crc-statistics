package pl.crc.statistics.infrastructure.database.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import pl.crc.statistics.domain.model.car.Car;
import pl.crc.statistics.domain.model.car.CarRepository;
import pl.crc.statistics.infrastructure.database.dao.CarDAO;
import pl.crc.statistics.infrastructure.database.repository.CarRepositoryElasticsearch;

import java.util.Objects;
import java.util.Optional;

public class CarRepositoryAdapter implements CarRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarRepositoryAdapter.class);
    private final CarRepositoryElasticsearch carRepositoryElasticsearch;
    private final ConversionService conversionService;

    public CarRepositoryAdapter(CarRepositoryElasticsearch carRepositoryElasticsearch, ConversionService conversionService) {
        this.carRepositoryElasticsearch = carRepositoryElasticsearch;
        this.conversionService = conversionService;
    }

    @Override
    public Car save(Car car) {
        carRepositoryElasticsearch.save(Objects.requireNonNull(conversionService.convert(car, CarDAO.class)));
        LOGGER.info("car successfully saved '{}'",car);
        return car;
    }

    @Override
    public void delete(String id) {
       Optional<CarDAO> optionalCarDAO = carRepositoryElasticsearch.findByDomainId(id);
       if (optionalCarDAO.isPresent()){
           CarDAO carDAO = optionalCarDAO.get();
           carDAO.markAsDeleted();
           carRepositoryElasticsearch.save(carDAO);
           LOGGER.info("car successfully deleted '{}'",carDAO.getDomainId());
       }
    }
}
