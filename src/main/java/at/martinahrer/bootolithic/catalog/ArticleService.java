package at.martinahrer.bootolithic.catalog;

import at.martinahrer.bootolithic.catalog.internal.ArticleMapper;
import at.martinahrer.bootolithic.catalog.internal.ArticleRepository;
import at.martinahrer.bootolithic.service.AbstractCrudService;
import org.springframework.stereotype.Service;

@Service
public class ArticleService extends AbstractCrudService<Article, ArticleIdentifier> {
    public ArticleService(ArticleRepository repository, ArticleMapper mapper) {
        super(repository, mapper);
    }
}
