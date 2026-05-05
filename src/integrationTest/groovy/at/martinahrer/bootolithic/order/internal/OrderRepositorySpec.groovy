package at.martinahrer.bootolithic.order.internal

import at.martinahrer.bootolithic.integrationtest.persistence.AbstractAggregateRootRepositorySpecification
import at.martinahrer.bootolithic.order.Order
import at.martinahrer.bootolithic.order.OrderFactory
import at.martinahrer.bootolithic.order.OrderIdentifier
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase
import org.springframework.modulith.test.ModuleSlicing
import org.springframework.test.context.TestPropertySource

import static org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase.Replace.NONE

@ModuleSlicing(extraIncludes = ['integrationtest', 'catalog'])
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@TestPropertySource(properties = """
    spring.jpa.show-sql=true
""")
class OrderRepositorySpec extends AbstractAggregateRootRepositorySpecification<Order, OrderIdentifier> {

    @Autowired
    OrderFactory entityFactory

    @Autowired
    OrderRepository repository
}
