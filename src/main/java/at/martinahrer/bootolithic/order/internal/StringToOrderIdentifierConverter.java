package at.martinahrer.bootolithic.order.internal;

import at.martinahrer.bootolithic.order.OrderIdentifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToOrderIdentifierConverter implements Converter<String, OrderIdentifier> {
    @Override
    public OrderIdentifier convert(String id) {
        return OrderIdentifier.of(id);
    }
}
