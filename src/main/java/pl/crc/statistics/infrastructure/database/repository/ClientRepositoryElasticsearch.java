package pl.crc.statistics.infrastructure.database.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import pl.crc.statistics.infrastructure.database.dao.ClientDAO;

@Repository
public interface ClientRepositoryElasticsearch extends ElasticsearchRepository<ClientDAO,String> {
}
