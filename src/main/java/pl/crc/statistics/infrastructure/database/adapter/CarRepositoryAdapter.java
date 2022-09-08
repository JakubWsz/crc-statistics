package pl.crc.statistics.infrastructure.database.adapter;

import org.springframework.core.convert.ConversionService;
import pl.crc.statistics.domain.model.car.Car;
import pl.crc.statistics.domain.model.car.CarRepository;
import pl.crc.statistics.infrastructure.database.dao.CarDAO;
import pl.crc.statistics.infrastructure.database.repository.CarRepositoryElasticsearch;

import java.util.Objects;

public class CarRepositoryAdapter implements CarRepository {
    private final CarRepositoryElasticsearch carRepositoryElasticsearch;
    private final ConversionService conversionService;

    public CarRepositoryAdapter(CarRepositoryElasticsearch carRepositoryElasticsearch, ConversionService conversionService) {
        this.carRepositoryElasticsearch = carRepositoryElasticsearch;
        this.conversionService = conversionService;
    }

    @Override
    public Car save(Car car) {
        carRepositoryElasticsearch.save(Objects.requireNonNull(conversionService.convert(car, CarDAO.class)));
        return car;
    }
}
