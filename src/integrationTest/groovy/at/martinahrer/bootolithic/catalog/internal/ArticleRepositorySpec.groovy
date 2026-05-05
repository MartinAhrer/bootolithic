package at.martinahrer.bootolithic.catalog.internal

import at.martinahrer.bootolithic.catalog.Article
import at.martinahrer.bootolithic.catalog.ArticleFactory
import at.martinahrer.bootolithic.catalog.ArticleIdentifier
import at.martinahrer.bootolithic.integrationtest.persistence.AbstractAggregateRootRepositorySpecification
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase
import org.springframework.modulith.test.ModuleSlicing
import org.springframework.test.context.TestPropertySource

import static org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase.Replace.NONE

@ModuleSlicing(extraIncludes = 'integrationtest')
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@TestPropertySource(properties = """
    spring.jpa.show-sql=true
""")
class ArticleRepositorySpec extends AbstractAggregateRootRepositorySpecification<Article, ArticleIdentifier> {

    @Autowired
    ArticleFactory entityFactory

    @Autowired
    ArticleRepository repository
}
