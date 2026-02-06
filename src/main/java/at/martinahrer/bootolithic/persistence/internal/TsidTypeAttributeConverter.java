package at.martinahrer.bootolithic.persistence.internal;

import io.hypersistence.tsid.TSID;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
class TsidTypeAttributeConverter implements AttributeConverter<TSID, String> {
    @Override
    public String convertToDatabaseColumn(TSID attribute) {
        return attribute == null ? null : attribute.toString();
    }

    @Override
    public TSID convertToEntityAttribute(String dbData) {
        return TSID.from(dbData);
    }
}
