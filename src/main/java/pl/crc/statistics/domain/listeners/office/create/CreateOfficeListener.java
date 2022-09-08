package pl.crc.statistics.domain.listeners.office.create;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pl.crc.statistics.domain.model.office.Office;
import pl.crc.statistics.domain.model.office.OfficeRepository;

@Component
public class CreateOfficeListener {
    private static final Logger logger = LoggerFactory.getLogger(CreateOfficeListener.class);
    private final OfficeRepository officeRepository;
    private final ObjectMapper objectMapper;

    public CreateOfficeListener(OfficeRepository officeRepository, ObjectMapper objectMapper) {
        this.officeRepository = officeRepository;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "create-office", groupId = "group-1")
    public void listen(String value) {
        logger.info("mapping office {} ", value);
        try {
            officeRepository.save(objectMapper.readValue(value, Office.class));
        } catch (Exception e) {
            logger.error("Exception occurred during mapping office data: ", e);
        }
    }
}
