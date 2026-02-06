package at.martinahrer.bootolithic.order.internal

import at.martinahrer.bootolithic.integrationtest.IntegrationTestConfiguration
import at.martinahrer.bootolithic.integrationtest.persistence.AbstractAggregateRootRepositorySpecification
import at.martinahrer.bootolithic.order.Order
import at.martinahrer.bootolithic.order.OrderFactory
import at.martinahrer.bootolithic.order.OrderIdentifier
import at.martinahrer.bootolithic.order.OrderRepositoryIntegrationTestConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase
import org.springframework.context.annotation.Import
import org.springframework.test.context.TestPropertySource

@DataJpaTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = """
    spring.jpa.show-sql=true
""")
@Import([IntegrationTestConfiguration, OrderRepositoryIntegrationTestConfiguration])
class OrderRepositorySpec extends AbstractAggregateRootRepositorySpecification<Order, OrderIdentifier> {

    @Autowired
    OrderFactory entityFactory

    @Autowired
    OrderRepository repository
}
