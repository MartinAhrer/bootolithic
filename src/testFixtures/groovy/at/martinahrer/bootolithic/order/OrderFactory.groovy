package at.martinahrer.bootolithic.order

import at.martinahrer.bootolithic.test.AbstractAssociationAdapter
import at.martinahrer.bootolithic.test.AbstractEntityFactory
import at.martinahrer.bootolithic.test.EntityFactory
import at.martinahrer.bootolithic.test.NullAssociationAdapter

import java.time.LocalDate

@EntityFactory
class OrderFactory extends AbstractEntityFactory<Order> {
    OrderPositionFactory orderPositionFactory = new OrderPositionFactory(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty())
    CustomerFactory customerFactory = new CustomerFactory()
    AddressFactory addressFactory = new AddressFactory()

    AbstractAssociationAdapter associationAdapter = new NullAssociationAdapter()

    OrderFactory(Optional<OrderPositionFactory> orderPositionFactory, Optional<CustomerFactory> customerFactory, Optional<AddressFactory> addressFactory, Optional<AbstractAssociationAdapter> associationAdapter) {
        orderPositionFactory.ifPresent { this.orderPositionFactory = it }
        customerFactory.ifPresent { this.customerFactory = it }
        addressFactory.ifPresent { this.addressFactory = it }
        associationAdapter.ifPresent { this.associationAdapter = it }
    }

    static OrderFactory of() {
        return new OrderFactory(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty())
    }

    @Override
    protected Map newDefaults(Map args) {
        def id = new OrderIdentifier()
        def defaults = [
            id: args.id ? args.id : id,
            orderNumber: OrderNumber.of("123456789"),
            requestedDeliveryDate: LocalDate.now().plusDays(1),
            customer: customerFactory.newObject(),
            address: addressFactory.newObject()
        ]
        defaults << args
        return defaults
    }

    @Override
    protected void addChildren(Map args, Order object) {
        addPositionsChildren(args, object)
    }

    private void addPositionsChildren(Map args, object) {
        ArrayList<OrderPosition> positions = args.positions ? args.positions : [orderPositionFactory.newObject()]
        positions.each {
            object.addPosition(it)
        }
    }

    @Override
    protected Order newInstance() {
        return new Order()
    }
}