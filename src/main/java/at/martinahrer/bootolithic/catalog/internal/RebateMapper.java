package at.martinahrer.bootolithic.catalog.internal;

import at.martinahrer.bootolithic.catalog.Rebate;
import at.martinahrer.bootolithic.persistence.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = ComponentModel.SPRING)
public interface RebateMapper extends EntityMapper<Rebate> {
    Rebate map(Rebate source, @MappingTarget Rebate target);
}
