package at.martinahrer.bootolithic.catalog.internal;

import at.martinahrer.bootolithic.catalog.Size;
import at.martinahrer.bootolithic.persistence.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = ComponentModel.SPRING)
public interface SizeMapper extends EntityMapper<Size> {
    Size map(Size source, @MappingTarget Size object);
}
