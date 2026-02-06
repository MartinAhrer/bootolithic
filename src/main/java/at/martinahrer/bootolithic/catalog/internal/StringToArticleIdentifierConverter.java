package at.martinahrer.bootolithic.catalog.internal;

import at.martinahrer.bootolithic.catalog.ArticleIdentifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToArticleIdentifierConverter implements Converter<String, ArticleIdentifier> {
    @Override
    public ArticleIdentifier convert(String id) {
        return ArticleIdentifier.of(id);
    }
}
