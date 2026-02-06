package at.martinahrer.bootolithic.catalog;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.jmolecules.ddd.types.AggregateRoot;

import java.math.BigDecimal;


@Getter
@Setter // for mapstruct -> ugly investigation needed
public class Rebate implements AggregateRoot<Rebate, RebateIdentifier> {
    @Setter(AccessLevel.PACKAGE) // to allow services etc set the id
    private RebateIdentifier id;

    @NameConstraint
    @NotNull
    private String name;

    @NotNull
    @PositiveOrZero
    private BigDecimal rebate;


    public Rebate(RebateIdentifier id) {
        this.id = id;
    }

    @JsonCreator // force Jackson to use this constructor to always get an id
    public Rebate() {
        this.id = new RebateIdentifier();
    }


}