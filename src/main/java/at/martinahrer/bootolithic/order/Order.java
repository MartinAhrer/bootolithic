package at.martinahrer.bootolithic.order;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.jmolecules.ddd.types.AggregateRoot;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name = "\"order\"")
@Getter
@Setter// for mapstruct and jackson -> ugly investigation needed
public class Order implements AggregateRoot<Order, OrderIdentifier> {
    @Getter
    @Setter
    private OrderIdentifier id;

    @AttributeOverride(name = "value", column = @Column(name = "order_number"))
    private OrderNumber orderNumber;

    @AttributeOverrides(
        {
            @AttributeOverride(name = "name", column = @Column(name = "customer_name") ),
            @AttributeOverride(name = "email.value", column = @Column(name = "customer_email") ),
            @AttributeOverride(name = "iban.value", column = @Column(name = "customer_iban") ),
        }
    )
    private Customer customer;
    @AttributeOverrides(
        {
            @AttributeOverride(name = "street", column = @Column(name = "address_street") ),
            @AttributeOverride(name = "zipCode", column = @Column(name = "address_zipcode") ),
            @AttributeOverride(name = "city", column = @Column(name = "address_city") ),
            @AttributeOverride(name = "region", column = @Column(name = "address_region") ),
            @AttributeOverride(name = "country", column = @Column(name = "address_country") )
        }
    )
    private Address address;

    private LocalDate requestedDeliveryDate;

    @OneToMany(
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JoinColumn(name = "order_id")
    private List<OrderPosition> positions = new ArrayList<>();

    public Order(OrderIdentifier id) {
        this.id = id;
    }

    @JsonCreator // force Jackson to use this constructor to always get an id
    public Order() {
        this(new OrderIdentifier());
    }

    public void addPosition(OrderPosition position) {
        position.assignTo(this.id);
        this.positions.add(position);
    }

    public void setPositions(List<OrderPosition> positions) {
        positions.forEach(position -> position.assignTo(this.id));
        this.positions = positions;
    }

    @PrePersist
    public void assignOrderIdentifierToPositions() {
        this.setPositions(this.positions);
    }
}
