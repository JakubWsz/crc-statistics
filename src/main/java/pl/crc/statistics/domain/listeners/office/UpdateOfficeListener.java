package pl.crc.statistics.domain.listeners.office;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pl.crc.statistics.domain.model.office.Office;
import pl.crc.statistics.domain.model.office.OfficeRepository;

@Component
public class UpdateOfficeListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateOfficeListener.class);
    private final OfficeRepository officeRepository;
    private final ObjectMapper objectMapper;

    public UpdateOfficeListener(OfficeRepository officeRepository, ObjectMapper objectMapper) {
        this.officeRepository = officeRepository;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "update-office", groupId = "group-1")
    public void listen(String value) {
        LOGGER.info("mapping office {} ", value);
        try {
            officeRepository.update(objectMapper.readValue(value, Office.class));
        } catch (Exception e) {
            LOGGER.error("Exception occurred during mapping office data: ", e);
        }
    }
}
