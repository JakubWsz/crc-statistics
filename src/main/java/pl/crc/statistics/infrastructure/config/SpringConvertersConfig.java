package pl.crc.statistics.infrastructure.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.crc.statistics.infrastructure.converter.*;

@Configuration
public class SpringConvertersConfig implements WebMvcConfigurer {
    @Lazy
    @Autowired
    private ConversionService conversionService;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new CarToCarDAO());
        registry.addConverter(new OfficeToOfficeDAO());
        registry.addConverter(new EmployeeToEmployeeDAO());
        registry.addConverter(new CarDAOtoCar());
        registry.addConverter(new ClientToClientDAO());
    }

}
