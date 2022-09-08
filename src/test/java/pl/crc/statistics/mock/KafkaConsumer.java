package pl.crc.statistics.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pl.crc.statistics.infrastructure.database.dao.CarDAO;
import pl.crc.statistics.infrastructure.database.repository.CarRepositoryElasticsearch;

import java.util.Optional;
import java.util.concurrent.CountDownLatch;

//@Component
public class KafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);
    private final CarRepositoryElasticsearch carRepositoryElasticsearch;
    private CountDownLatch latch = new CountDownLatch(1);
    private final ObjectMapper objectMapper;

    public KafkaConsumer(CarRepositoryElasticsearch carRepositoryElasticsearch, ObjectMapper objectMapper) {
        this.carRepositoryElasticsearch = carRepositoryElasticsearch;
        this.objectMapper = objectMapper;
    }

 //   @KafkaListener(topics = "create-car", groupId = "group-1")
    public void listen(ConsumerRecord<?, ?> consumerRecord) {
        String value = consumerRecord.toString();
        LOGGER.info("mapping car {} ", value);
        try {
            carRepositoryElasticsearch.save(objectMapper.readValue(value, CarDAO.class));
            latch.countDown();
        } catch (Exception e) {
            LOGGER.error("Exception occurred during mapping car data: ", e);
        }
    }

    public void resetLatch() {
        latch = new CountDownLatch(1);
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public Optional<CarDAO> getCar(String domainId){
        return carRepositoryElasticsearch.findByDomainId(domainId);
    }
}
