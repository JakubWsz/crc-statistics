package pl.crc.statistics.domain.listeners.employee.delete;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pl.crc.statistics.domain.model.employee.EmployeeRepository;

@Component
public class DeleteEmployeeListener {
    private static final Logger logger = LoggerFactory.getLogger(DeleteEmployeeListener.class);
    private final EmployeeRepository employeeRepository;
    private final ObjectMapper objectMapper;

    public DeleteEmployeeListener(EmployeeRepository employeeRepository, ObjectMapper objectMapper) {
        this.employeeRepository = employeeRepository;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "delete-employee", groupId = "group-1")
    public void listen(String value) {
        logger.info("mapping employee {} ", value);
        String employeeId = null;
        try {
            employeeId = objectMapper.readValue(value, String.class);
        } catch (Exception e) {
            logger.error("Exception occurred during mapping employee data: ", e);
        }
        employeeRepository.delete(employeeId);
    }
}