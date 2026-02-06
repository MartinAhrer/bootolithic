package at.martinahrer.bootolithic.order

import at.martinahrer.bootolithic.order.internal.StringToOrderIdentifierConverter
import at.martinahrer.bootolithic.order.internal.StringToOrderPositionIdentifierConverter
import at.martinahrer.bootolithic.test.web.AbstractStandaloneControllerSpec
import at.martinahrer.bootolithic.web.CrudController
import org.apache.commons.lang3.RandomStringUtils
import org.jmolecules.ddd.types.Identifier
import org.springframework.data.domain.PageImpl
import org.springframework.format.support.FormattingConversionService

import java.util.function.Function
import java.util.function.Supplier

class OrderControllerSpec extends AbstractStandaloneControllerSpec {

    String requestUriPrefix = '/public/orders'

    OrderFactory objectFactory = OrderFactory.of()
    OrderFactory resourceFactory = objectFactory

    Supplier<Identifier> identifierSupplier = { -> new OrderIdentifier() }

    @Override
    protected setupConversionService(FormattingConversionService formattingConversionService) {
        super.setupConversionService(formattingConversionService)
        formattingConversionService.addConverter(new StringToOrderIdentifierConverter())
        formattingConversionService.addConverter(new StringToOrderPositionIdentifierConverter())
    }

    @Override
    Function getInjectInvalidResourceProperties() {
        return { Order order ->
            order.orderNumber = OrderNumber
                .of(RandomStringUtils
                    .insecure()
                    .nextNumeric(OrderNumberConstraint.MAX_LENGTH + 1))
        }
    }

    CrudController buildController() {
        OrderService service = Mock(OrderService)

        // should be possible to move that to some generic mock support for CRUD service operations
        def object = objectFactory.newObject(id: identifierValue)
        service.findAll(_) >> new PageImpl([object])
        service.findById(identifierValue) >> Optional.of(object)
        service.findById({ !identifierValue.equals(it) }) >> Optional.empty()
        service.create(_) >> { Order entity ->
            entity
        }
        service.update(_, _) >> { Identifier id, Order entity -> entity }

        return new OrderController(service)
    }
}