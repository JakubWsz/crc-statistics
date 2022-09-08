package pl.crc.statistics.domain.listeners.employee.create;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pl.crc.statistics.domain.listeners.car.create.CreateCarListener;
import pl.crc.statistics.domain.model.employee.Employee;
import pl.crc.statistics.domain.model.employee.EmployeeRepository;

@Component
public class CreateEmployeeListener {
    private static final Logger logger = LoggerFactory.getLogger(CreateEmployeeListener.class);
    private final EmployeeRepository employeeRepository;
    private final ObjectMapper objectMapper;

    public CreateEmployeeListener(EmployeeRepository employeeRepository, ObjectMapper objectMapper) {
        this.employeeRepository = employeeRepository;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "create-employee", groupId = "group-1")
    public void listen(String value) {
        logger.info("mapping employee {} ", value);
        try {
            employeeRepository.save(objectMapper.readValue(value, Employee.class));
        } catch (Exception e) {
            logger.error("Exception occurred during mapping employee data: ", e);
        }
    }
}
