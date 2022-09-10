package pl.crc.statistics.infrastructure.converter;

import org.springframework.core.convert.converter.Converter;
import pl.crc.statistics.domain.model.client.Client;
import pl.crc.statistics.infrastructure.database.dao.ClientDAO;

public class ClientToClientDAO implements Converter<Client, ClientDAO> {
    @Override
    public ClientDAO convert(Client client) {
        return new ClientDAO(client.getDomainId(), client.getFirstname(), client.getLastname(), client.getEmail(),
                client.getPassword(), client.getBirthdate());
    }
}
