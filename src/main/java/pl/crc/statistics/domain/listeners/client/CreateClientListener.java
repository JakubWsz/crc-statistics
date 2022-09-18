package pl.crc.statistics.domain.listeners.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pl.crc.statistics.domain.model.client.Client;
import pl.crc.statistics.domain.model.client.ClientRepository;

@Component
public class CreateClientListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateClientListener.class);
    private final ClientRepository clientRepository;
    private final ObjectMapper objectMapper;

    public CreateClientListener(ClientRepository clientRepository, ObjectMapper objectMapper) {
        this.clientRepository = clientRepository;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "create-client", groupId = "group-1")
    public void listen(String value) {
        LOGGER.info("mapping client {} ", value);
        try {
            clientRepository.save(objectMapper.readValue(value, Client.class));
        } catch (Exception e) {
            LOGGER.error("Exception occurred during mapping client data: ", e);
        }
    }
}