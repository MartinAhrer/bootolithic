package at.martinahrer.bootolithic.order;

import at.martinahrer.bootolithic.catalog.Article;
import at.martinahrer.bootolithic.catalog.ArticleIdentifier;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.jmolecules.ddd.annotation.Association;
import org.jmolecules.ddd.types.Entity;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;

@Setter
@Getter
public class OrderPosition implements Entity<Order, OrderPositionIdentifier> {
    @Getter
    @AttributeOverride(name = "value", column = @Column(name = "id"))
    private final OrderPositionIdentifier id;

    @Association(aggregateType = Order.class)
    @AttributeOverride(name = "id", column = @Column(name = "order_id", nullable = false, updatable = false))
    private OrderIdentifier order; // is this DDD/jMolecules compliant

    // The rate is a snapshot of the rate (ask/bid) at the time of the order position creation
    private BigDecimal rate;
    private long quantity;

    //price = quantity * rate as EUR
    //stored for reproducibility
    @AttributeOverrides({
        @AttributeOverride(name = "amount", column = @Column(name = "price_amount")),
        @AttributeOverride(name = "currency", column = @Column(name = "price_currency")),
    })
    private MonetaryAmount price;

    @Association(aggregateType = Article.class)
    @AttributeOverride(name = "id", column = @Column(name = "article_id"))
    private ArticleIdentifier article;

    public OrderPosition(OrderPositionIdentifier id) {
        this.id = id;
    }

    @JsonCreator // force Jackson to use this constructor to always get an id
    public OrderPosition() {
        this(new OrderPositionIdentifier());
    }

    public void assignTo(OrderIdentifier order) {
        this.order = order;
    }


}
