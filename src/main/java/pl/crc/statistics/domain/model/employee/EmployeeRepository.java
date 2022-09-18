package pl.crc.statistics.domain.model.employee;

public interface EmployeeRepository {
    Employee save(Employee employee);
    void delete (String id);
    Employee update(Employee employee);
}
