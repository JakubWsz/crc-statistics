package pl.crc.statistics.domain.listeners.car.create;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pl.crc.statistics.domain.model.car.Car;
import pl.crc.statistics.domain.model.car.CarRepository;

@Component
public class CreateCarListener {
    private static final Logger logger = LoggerFactory.getLogger(CreateCarListener.class);
    private final CarRepository carRepository;
    private final ObjectMapper objectMapper;

    public CreateCarListener(CarRepository carRepository, ObjectMapper objectMapper) {
        this.carRepository = carRepository;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "create-car", groupId = "group-1")
    public void listen(String value) {
        logger.info("mapping car {} ", value);
        try {
            carRepository.save(objectMapper.readValue(value, Car.class));
        } catch (Exception e) {
            logger.error("Exception occurred during mapping car data: ", e);
        }
    }
}