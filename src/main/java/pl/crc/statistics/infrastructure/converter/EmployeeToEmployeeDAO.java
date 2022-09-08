package pl.crc.statistics.infrastructure.converter;

import org.springframework.core.convert.converter.Converter;
import pl.crc.statistics.domain.model.employee.Employee;
import pl.crc.statistics.infrastructure.database.dao.EmployeeDAO;

public class EmployeeToEmployeeDAO implements Converter<Employee, EmployeeDAO> {
    @Override
    public EmployeeDAO convert(Employee employee) {
        return new EmployeeDAO(employee.getId(), employee.getFirstname(), employee.getLastname(), employee.getAddress(),
                employee.getPesel(), employee.getAccountNumber(), employee.getSalaryAmount(),employee.getContractType(),
                employee.getPosition(),employee.getOfficeId(),employee.isDeleted());
    }
}
