package pl.crc.statistics.domain.listeners.car.create;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import pl.crc.statistics.domain.model.car.Car;
import pl.crc.statistics.infrastructure.database.dao.CarDAO;
import pl.crc.statistics.mock.KafkaProducer;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
class CreateCarListenerTest {
    private static final String BRAND_MERCEDES = "Mercedes";
    private static final String MODEL_MERCEDES_EQE = "EQE";
    private static final String CAR_TYPE_PASSENGER = "Passenger";
    private static final String FUEL_TYPE_PETROL = "Petrol";
    private static final String GEARBOX_TYPE_MANUAL = "Manual";
    private static final String DOOR_NUMBER_5 = "_5";
    private static final double BOOT_CAPACITY_350 = 350;

    @Autowired
    private KafkaProducer producer;

    @Autowired
    private CreateCarListener consumer;

//    @Test
//    public void listenerShouldReturnTrue() throws InterruptedException {
//        Car car = new Car(UUID.randomUUID().toString(), BRAND_MERCEDES, MODEL_MERCEDES_EQE, CAR_TYPE_PASSENGER,
//                FUEL_TYPE_PETROL, GEARBOX_TYPE_MANUAL, DOOR_NUMBER_5, BOOT_CAPACITY_350, UUID.randomUUID().toString(), deleted);
//        producer.push(car,"create-car");
//        CarDAO carDAO = null;
////        if (consumer.getCar(car.getId()).isPresent()) {
////            carDAO = consumer.getCar(car.getId()).get();
////        }
////        assertNotNull(carDAO);
////        assertEquals(carDAO.getBrand(), BRAND_MERCEDES);
////        boolean await = consumer.getLatch().await(15, TimeUnit.SECONDS);
// //       Assertions.assertTrue(await);
//        Thread.sleep(15000);
//    }
}