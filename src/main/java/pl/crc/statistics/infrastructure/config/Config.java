package pl.crc.statistics.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import pl.crc.statistics.infrastructure.database.adapter.CarRepositoryAdapter;
import pl.crc.statistics.infrastructure.database.adapter.ClientRepositoryAdapter;
import pl.crc.statistics.infrastructure.database.repository.CarRepositoryElasticsearch;
import pl.crc.statistics.infrastructure.database.adapter.EmployeeRepositoryAdapter;
import pl.crc.statistics.infrastructure.database.repository.ClientRepositoryElasticsearch;
import pl.crc.statistics.infrastructure.database.repository.EmployeeRepositoryElasticsearch;
import pl.crc.statistics.infrastructure.database.adapter.OfficeRepositoryAdapter;
import pl.crc.statistics.infrastructure.database.repository.OfficeRepositoryElasticsearch;

//@Configuration
//@EnableElasticsearchRepositories(basePackages = "pl.crc.statistics.infrastructure.database.repository")
public class Config {

    @Bean
   public CarRepositoryAdapter carRepositoryAdapterJpa(CarRepositoryElasticsearch carRepositoryElasticsearch,
                                                       ConversionService conversionService){
        return new CarRepositoryAdapter(carRepositoryElasticsearch, conversionService);
    }

    @Bean
    public OfficeRepositoryAdapter officeRepositoryAdapter(OfficeRepositoryElasticsearch officeRepositoryElasticsearch,
                                                           ConversionService conversionService){
        return new OfficeRepositoryAdapter(officeRepositoryElasticsearch,conversionService);
    }

    @Bean
    public EmployeeRepositoryAdapter employeeRepositoryAdapter(
            EmployeeRepositoryElasticsearch employeeRepositoryElasticsearch,ConversionService conversionService){
        return new EmployeeRepositoryAdapter(employeeRepositoryElasticsearch,conversionService);
    }

    @Bean
    public ClientRepositoryAdapter clientRepositoryAdapter(
            ClientRepositoryElasticsearch clientRepositoryElasticsearch,ConversionService conversionService){
        return new ClientRepositoryAdapter(clientRepositoryElasticsearch,conversionService);
    }
}