package at.martinahrer.bootolithic.order.internal;

import at.martinahrer.bootolithic.order.Order;
import at.martinahrer.bootolithic.order.OrderIdentifier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, OrderIdentifier> {
}
