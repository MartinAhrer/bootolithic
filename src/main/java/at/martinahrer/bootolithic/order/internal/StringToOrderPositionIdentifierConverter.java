package at.martinahrer.bootolithic.order.internal;

import at.martinahrer.bootolithic.order.OrderPositionIdentifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToOrderPositionIdentifierConverter implements Converter<String, OrderPositionIdentifier> {
    @Override
    public OrderPositionIdentifier convert(String id) {
        return OrderPositionIdentifier.of(id);
    }
}
