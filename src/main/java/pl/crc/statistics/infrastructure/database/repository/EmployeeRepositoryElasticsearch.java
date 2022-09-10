package pl.crc.statistics.infrastructure.database.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import pl.crc.statistics.infrastructure.database.dao.EmployeeDAO;

import java.util.Optional;

@Repository
public interface EmployeeRepositoryElasticsearch extends ElasticsearchRepository<EmployeeDAO,String> {
    Optional<EmployeeDAO> findByDomainId(String id);
}
