package at.martinahrer.bootolithic.catalog.internal;

import at.martinahrer.bootolithic.catalog.Article;
import at.martinahrer.bootolithic.catalog.ArticleIdentifier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, ArticleIdentifier> {
}
