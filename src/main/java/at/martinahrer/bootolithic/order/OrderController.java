package at.martinahrer.bootolithic.order;

import at.martinahrer.bootolithic.service.AbstractCrudService;
import at.martinahrer.bootolithic.web.AbstractCrudController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(OrderController.REQUEST_MAPPING_URI_PREFIX)
@Validated
public class OrderController extends AbstractCrudController<Order, OrderIdentifier> {
    public static final String REQUEST_MAPPING_URI_PREFIX = "/public/orders";

    public OrderController(AbstractCrudService<Order, OrderIdentifier> service) {
        super(service);
    }
}
