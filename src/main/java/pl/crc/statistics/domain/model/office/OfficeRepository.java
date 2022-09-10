package pl.crc.statistics.domain.model.office;

import pl.crc.statistics.domain.model.car.Car;

public interface OfficeRepository {
    Office save(Office office);
    void delete(String id);
}
