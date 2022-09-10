package pl.crc.statistics.infrastructure.database.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import pl.crc.statistics.domain.model.employee.Employee;
import pl.crc.statistics.domain.model.employee.EmployeeRepository;
import pl.crc.statistics.infrastructure.database.dao.EmployeeDAO;
import pl.crc.statistics.infrastructure.database.repository.EmployeeRepositoryElasticsearch;

import java.util.Objects;
import java.util.Optional;

public class EmployeeRepositoryAdapter implements EmployeeRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeRepositoryAdapter.class);
    private final EmployeeRepositoryElasticsearch employeeRepositoryElasticsearch;
    private final ConversionService conversionService;

    public EmployeeRepositoryAdapter(EmployeeRepositoryElasticsearch employeeRepositoryElasticsearch,
                                     ConversionService conversionService) {
        this.employeeRepositoryElasticsearch = employeeRepositoryElasticsearch;
        this.conversionService = conversionService;
    }

    @Override
    public Employee save(Employee employee) {
        employeeRepositoryElasticsearch.save(Objects.requireNonNull(conversionService.convert(
                employee, EmployeeDAO.class)));
        LOGGER.info("employee successfully saved '{}'",employee);
        return employee;
    }

    @Override
    public void delete(String id) {
        Optional<EmployeeDAO> optionalEmployeeDAO = employeeRepositoryElasticsearch.findByDomainId(id);
        if (optionalEmployeeDAO.isPresent()){
            EmployeeDAO employeeDAO = optionalEmployeeDAO.get();
            employeeDAO.markAsDeleted();
            employeeRepositoryElasticsearch.save(employeeDAO);
            LOGGER.info("employee successfully deleted '{}'",employeeDAO.getDomainId());
        }
    }
}
