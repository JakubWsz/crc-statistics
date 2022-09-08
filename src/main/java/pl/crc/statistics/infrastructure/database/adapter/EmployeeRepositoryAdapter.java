package pl.crc.statistics.infrastructure.database.adapter;

import org.springframework.core.convert.ConversionService;
import pl.crc.statistics.domain.model.employee.Employee;
import pl.crc.statistics.domain.model.employee.EmployeeRepository;
import pl.crc.statistics.infrastructure.database.dao.EmployeeDAO;
import pl.crc.statistics.infrastructure.database.repository.EmployeeRepositoryElasticsearch;

import java.util.Objects;

public class EmployeeRepositoryAdapter implements EmployeeRepository {
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
        return employee;
    }
}
