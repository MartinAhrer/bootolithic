package at.martinahrer.bootolithic.order.internal;

import at.martinahrer.bootolithic.order.Order;
import at.martinahrer.bootolithic.persistence.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = ComponentModel.SPRING)
public interface OrderMapper extends EntityMapper<Order> {
    Order map(Order source, @MappingTarget Order target);
}
