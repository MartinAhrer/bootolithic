package at.martinahrer.bootolithic.catalog.internal

import at.martinahrer.bootolithic.catalog.Article
import at.martinahrer.bootolithic.catalog.ArticleFactory
import at.martinahrer.bootolithic.catalog.ArticleIdentifier
import at.martinahrer.bootolithic.catalog.CatalogRepositoryIntegrationTestConfiguration
import at.martinahrer.bootolithic.integrationtest.IntegrationTestConfiguration
import at.martinahrer.bootolithic.integrationtest.persistence.AbstractAggregateRootRepositorySpecification
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase
import org.springframework.context.annotation.Import
import org.springframework.test.context.TestPropertySource

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = """
    spring.jpa.show-sql=true
    spring.modulith.runtime.flyway-enabled=true
""")
@Import([IntegrationTestConfiguration, CatalogRepositoryIntegrationTestConfiguration])
class ArticleRepositorySpec extends AbstractAggregateRootRepositorySpecification<Article, ArticleIdentifier> {

    @Autowired
    ArticleFactory entityFactory

    @Autowired
    ArticleRepository repository
}
