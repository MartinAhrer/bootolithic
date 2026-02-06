package at.martinahrer.bootolithic.catalog.internal

import at.martinahrer.bootolithic.catalog.CatalogRepositoryIntegrationTestConfiguration
import at.martinahrer.bootolithic.catalog.Size
import at.martinahrer.bootolithic.catalog.SizeFactory
import at.martinahrer.bootolithic.catalog.SizeIdentifier
import at.martinahrer.bootolithic.integrationtest.IntegrationTestConfiguration
import at.martinahrer.bootolithic.integrationtest.persistence.AbstractAggregateRootRepositorySpecification
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase
import org.springframework.context.annotation.Import
import org.springframework.test.context.TestPropertySource

@DataJpaTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = """
    spring.jpa.show-sql=true
    spring.modulith.runtime.flyway-enabled=true
""")
@Import([IntegrationTestConfiguration, CatalogRepositoryIntegrationTestConfiguration])
class SizeRepositorySpec extends AbstractAggregateRootRepositorySpecification<Size, SizeIdentifier> {
    @Autowired
    SizeFactory entityFactory

    @Autowired
    SizeRepository repository
}
