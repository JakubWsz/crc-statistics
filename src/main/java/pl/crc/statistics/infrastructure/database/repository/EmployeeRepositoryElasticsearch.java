package pl.crc.statistics.infrastructure.database.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import pl.crc.statistics.infrastructure.database.dao.EmployeeDAO;

@Repository
public interface EmployeeRepositoryElasticsearch extends ElasticsearchRepository<EmployeeDAO,String> {
}
