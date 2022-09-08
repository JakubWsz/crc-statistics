package pl.crc.statistics.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);
    private final ObjectMapper objectMapper;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void push(Object object, String topic) {
        try {
            ListenableFuture<SendResult<String, String>> futureSendResult
                    = kafkaTemplate.send(topic,1,"1", objectMapper.writeValueAsString(object));
            futureSendResult.addCallback(new ListenableFutureCallback<>() {
                @Override
                public void onFailure(Throwable ex) {
                    LOGGER.error("Exception occurred during sending data to statistics", ex);
                }

                @Override
                public void onSuccess(SendResult<String, String> result) {
                    LOGGER.info("Sent data to statistics, result '{}'",result);
                    LOGGER.debug("Sent '{}' to statistics with offset '{}'", result.getProducerRecord().value(),
                            result.getRecordMetadata().offset());
                }
            });
        } catch (Exception e) {
            LOGGER.error("Exception occurred during sending data to statistics", e);
        }
    }
}