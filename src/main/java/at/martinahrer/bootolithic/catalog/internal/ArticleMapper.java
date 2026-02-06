package at.martinahrer.bootolithic.catalog.internal;

import at.martinahrer.bootolithic.catalog.Article;
import at.martinahrer.bootolithic.persistence.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = ComponentModel.SPRING)
public interface ArticleMapper extends EntityMapper<Article> {
    Article map(Article source, @MappingTarget Article target);
}
