package pl.crc.statistics.infrastructure.database.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import pl.crc.statistics.infrastructure.database.dao.OfficeDAO;

@Repository
public interface OfficeRepositoryElasticsearch extends ElasticsearchRepository<OfficeDAO,String> {
}
