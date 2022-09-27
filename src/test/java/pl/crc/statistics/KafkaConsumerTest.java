package pl.crc.statistics;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import pl.crc.statistics.domain.listeners.office.CreateOfficeListener;
import pl.crc.statistics.domain.model.Address;
import pl.crc.statistics.domain.model.office.Office;
import pl.crc.statistics.domain.model.office.OfficeRepository;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

@EmbeddedKafka
@SpringBootTest(properties = "spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@RunWith(MockitoJUnitRunner.class)
class KafkaConsumerTest {

    private static final String TOPIC_NAME = "create-office";

    private Producer<String, String> producer;

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @Autowired
    private ObjectMapper objectMapper;

    @SpyBean
    private CreateOfficeListener createOfficeListener;

    @MockBean
    private OfficeRepository officeRepository;

    @Captor
    ArgumentCaptor<String> officeArgumentCaptor;


    @BeforeAll
    void setUp() {
        Map<String, Object> configs = new HashMap<>(KafkaTestUtils.producerProps(embeddedKafkaBroker));
        producer = new DefaultKafkaProducerFactory<>(configs, new StringSerializer(),
                new StringSerializer()).createProducer();
    }

    @AfterAll
    void shutdown() {
        producer.close();
    }

    @Test
    void testLogKafkaMessages() throws JsonProcessingException {
        // Write a message (John Wick user) to Kafka using a test producer
        String uuid = "11111";
        Address address = new Address("Szkolna 17", "23-145", "Grajewo");
        String message = objectMapper.writeValueAsString(new Office(uuid, address,
                "https://www.fura.pl/grajewo", "Marcin Krasucki", false));
        producer.send(new ProducerRecord<>(TOPIC_NAME, 0, uuid, message));
        producer.flush();


        verify(createOfficeListener, timeout(5000).times(1))
                .listen(message);

        String office = officeArgumentCaptor.getValue();
        assertNotNull(office);
    }
}
