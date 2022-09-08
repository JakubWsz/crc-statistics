package pl.crc.statistics.infrastructure.database.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import pl.crc.statistics.infrastructure.database.dao.CarDAO;

import java.util.Optional;

@Repository
public interface CarRepositoryElasticsearch extends ElasticsearchRepository<CarDAO,String> {
    Optional<CarDAO> findByDomainId(String id);
}
