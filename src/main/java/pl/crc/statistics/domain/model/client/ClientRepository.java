package pl.crc.statistics.domain.model.client;

public interface ClientRepository {
    Client save(Client client);
    Client update(Client client);
}
