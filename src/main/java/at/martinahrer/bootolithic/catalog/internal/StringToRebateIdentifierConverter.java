package at.martinahrer.bootolithic.catalog.internal;

import at.martinahrer.bootolithic.catalog.RebateIdentifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
@Component
public class StringToRebateIdentifierConverter implements Converter<String, RebateIdentifier> {
    @Override
    public RebateIdentifier convert(String id) {
        return RebateIdentifier.of(id);
    }
}
