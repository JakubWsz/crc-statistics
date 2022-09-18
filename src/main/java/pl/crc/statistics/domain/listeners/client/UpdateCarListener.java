package pl.crc.statistics.domain.listeners.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pl.crc.statistics.domain.model.client.Client;
import pl.crc.statistics.domain.model.client.ClientRepository;

@Component
public class UpdateCarListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateCarListener.class);
    private final ClientRepository clientRepository;
    private final ObjectMapper objectMapper;

    public UpdateCarListener(ClientRepository clientRepository, ObjectMapper objectMapper) {
        this.clientRepository = clientRepository;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "update-client", groupId = "group-1")
    public void listen(String value) {
        LOGGER.info("mapping client {} ", value);
        try {
            clientRepository.update(objectMapper.readValue(value, Client.class));
        } catch (Exception e) {
            LOGGER.error("Exception occurred during mapping client data: ", e);
        }
    }
}