package pl.crc.statistics.domain.listeners.employee;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pl.crc.statistics.domain.model.employee.Employee;
import pl.crc.statistics.domain.model.employee.EmployeeRepository;

@Component
public class UpdateEmployeeListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateEmployeeListener.class);
    private final EmployeeRepository employeeRepository;
    private final ObjectMapper objectMapper;

    public UpdateEmployeeListener(EmployeeRepository employeeRepository, ObjectMapper objectMapper) {
        this.employeeRepository = employeeRepository;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "update-employee", groupId = "group-1")
    public void listen(String value) {
        LOGGER.info("mapping client {} ", value);
        try {
            employeeRepository.update(objectMapper.readValue(value, Employee.class));
        } catch (Exception e) {
            LOGGER.error("Exception occurred during mapping client data: ", e);
        }
    }
}
