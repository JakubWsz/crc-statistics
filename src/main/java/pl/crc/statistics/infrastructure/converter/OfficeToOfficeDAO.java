package pl.crc.statistics.infrastructure.converter;

import org.springframework.core.convert.converter.Converter;
import pl.crc.statistics.domain.model.office.Office;
import pl.crc.statistics.infrastructure.database.dao.OfficeDAO;

public class OfficeToOfficeDAO implements Converter<Office, OfficeDAO> {
    @Override
    public OfficeDAO convert(Office office) {
        return new OfficeDAO(office.getId(),office.getAddress(),office.getWebsiteURL(),
                office.getOfficeCEO(),office.isDeleted());
    }
}
