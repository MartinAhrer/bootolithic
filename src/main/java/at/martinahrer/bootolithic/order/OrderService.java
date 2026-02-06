package at.martinahrer.bootolithic.order;

import at.martinahrer.bootolithic.order.internal.OrderMapper;
import at.martinahrer.bootolithic.service.AbstractCrudService;
import org.jmolecules.ddd.annotation.Service;
import org.springframework.data.jpa.repository.JpaRepository;

@Service
public class OrderService extends AbstractCrudService<Order, OrderIdentifier> {
    public OrderService(JpaRepository<Order, OrderIdentifier> repository, OrderMapper entityMapper) {
        super(repository, entityMapper);
    }
}
