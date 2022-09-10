package pl.crc.statistics.domain.listeners.office.delete;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pl.crc.statistics.domain.model.office.OfficeRepository;

@Component
public class DeleteOfficeListener {
    private static final Logger logger = LoggerFactory.getLogger(DeleteOfficeListener.class);
    private final OfficeRepository officeRepository;
    private final ObjectMapper objectMapper;

    public DeleteOfficeListener(OfficeRepository officeRepository, ObjectMapper objectMapper) {
        this.officeRepository = officeRepository;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "delete-office", groupId = "group-1")
    public void listen(String value) {
        logger.info("mapping office {} ", value);
        String officeId = null;
        try {
            officeId = objectMapper.readValue(value, String.class);
        } catch (Exception e) {
            logger.error("Exception occurred during mapping office data: ", e);
        }
        officeRepository.delete(officeId);
    }
}