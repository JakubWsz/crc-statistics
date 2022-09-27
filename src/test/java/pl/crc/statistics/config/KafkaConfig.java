package pl.crc.statistics.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.test.EmbeddedKafkaBroker;

@EnableKafka
@Configuration
public class KafkaConfig {
    @Bean
    public EmbeddedKafkaBroker embeddedKafkaBroker(){
        return new EmbeddedKafkaBroker(1);
    }
}
