package pl.crc.statistics.infrastructure.database.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import pl.crc.statistics.domain.model.office.Office;
import pl.crc.statistics.domain.model.office.OfficeRepository;
import pl.crc.statistics.infrastructure.database.dao.OfficeDAO;
import pl.crc.statistics.infrastructure.database.repository.OfficeRepositoryElasticsearch;

import java.util.Objects;


public class OfficeRepositoryAdapter implements OfficeRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(OfficeRepositoryAdapter.class);
    private final OfficeRepositoryElasticsearch officeRepositoryElasticsearch;
    private final ConversionService conversionService;

    public OfficeRepositoryAdapter(OfficeRepositoryElasticsearch officeRepositoryElasticsearch,
                                   ConversionService conversionService) {
        this.officeRepositoryElasticsearch = officeRepositoryElasticsearch;
        this.conversionService = conversionService;
    }

    @Override
    public Office save(Office office) {
        officeRepositoryElasticsearch.save(Objects.requireNonNull(conversionService.convert(office, OfficeDAO.class)));
        LOGGER.info("office successfully saved '{}'", office);
        return office;
    }

    @Override
    public void delete(String id) {
        officeRepositoryElasticsearch.findByDomainId(id).ifPresent(
                officeDAO -> {
                    officeDAO.markAsDeleted();
                    officeDAO.updateObject(officeDAO.getId());
                    officeRepositoryElasticsearch.save(officeDAO);
                    LOGGER.info("office successfully deleted '{}'", officeDAO.getDomainId());
                });
    }
}
