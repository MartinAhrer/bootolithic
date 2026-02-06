package at.martinahrer.bootolithic.catalog.internal;

import at.martinahrer.bootolithic.catalog.SizeIdentifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToSizeIdentifierConverter implements Converter<String, SizeIdentifier> {
    @Override
    public SizeIdentifier convert(String id) {
        return SizeIdentifier.of(id);
    }
}
