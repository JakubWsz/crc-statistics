package pl.crc.statistics.infrastructure.database.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;
import pl.crc.statistics.domain.model.client.Client;
import pl.crc.statistics.domain.model.client.ClientRepository;
import pl.crc.statistics.infrastructure.database.dao.ClientDAO;
import pl.crc.statistics.infrastructure.database.repository.ClientRepositoryElasticsearch;

import java.util.Objects;

@Component
public class ClientRepositoryAdapter implements ClientRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientRepositoryAdapter.class);
    private final ClientRepositoryElasticsearch clientRepositoryElasticsearch;
    private final ConversionService conversionService;

    public ClientRepositoryAdapter(ClientRepositoryElasticsearch clientRepositoryElasticsearch,
                                   ConversionService conversionService) {
        this.clientRepositoryElasticsearch = clientRepositoryElasticsearch;
        this.conversionService = conversionService;
    }


    @Override
    public Client save(Client client) {
        clientRepositoryElasticsearch.save(Objects.requireNonNull(conversionService.convert(client, ClientDAO.class)));
        LOGGER.info("client successfully saved '{}'", client);
        return client;
    }

    @Override
    public Client update(Client client) {
        clientRepositoryElasticsearch.save(Objects.requireNonNull(conversionService.convert(client, ClientDAO.class)));
        LOGGER.info("client successfully updated '{}'", client);
        return client;
    }


}
