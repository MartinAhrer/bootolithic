package at.martinahrer.bootolithic.catalog.internal

import at.martinahrer.bootolithic.catalog.Rebate
import at.martinahrer.bootolithic.catalog.RebateFactory
import at.martinahrer.bootolithic.catalog.RebateIdentifier
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
class RebateRepositorySpec extends AbstractAggregateRootRepositorySpecification<Rebate, RebateIdentifier> {

    @Autowired
    RebateFactory entityFactory

    @Autowired
    RebateRepository repository
}
