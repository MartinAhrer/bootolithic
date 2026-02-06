package at.martinahrer.bootolithic.order.internal;

import at.martinahrer.bootolithic.order.OrderPosition;
import at.martinahrer.bootolithic.persistence.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = ComponentModel.SPRING)
public interface OrderPositionMapper extends EntityMapper<OrderPosition> {
    OrderPosition map(OrderPosition source, @MappingTarget OrderPosition target);
}
