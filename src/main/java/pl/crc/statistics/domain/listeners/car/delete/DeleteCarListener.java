package pl.crc.statistics.domain.listeners.car.delete;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pl.crc.statistics.domain.model.car.CarRepository;

@Component
public class DeleteCarListener {
    private static final Logger logger = LoggerFactory.getLogger(DeleteCarListener.class);
    private final CarRepository carRepository;
    private final ObjectMapper objectMapper;

    public DeleteCarListener(CarRepository carRepository, ObjectMapper objectMapper) {
        this.carRepository = carRepository;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "delete-car", groupId = "group-1")
    public void listen(String value) {
        logger.info("mapping car {} ", value);
        String carId = null;
        try {
            carId = objectMapper.readValue(value, String.class);
        } catch (Exception e) {
            logger.error("Exception occurred during mapping car data: ", e);
        }
        carRepository.delete(carId);
    }
}
