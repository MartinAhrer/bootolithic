package at.martinahrer.bootolithic.catalog;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jmolecules.ddd.types.AggregateRoot;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter // for mapstruct -> ugly investigation needed
public class Size implements AggregateRoot<Size, SizeIdentifier> {
    @Setter(AccessLevel.PROTECTED)
    private SizeIdentifier id;

    @NameConstraint
    @NotNull
    private String name;

    @PositiveOrZero
    private BigDecimal surcharge;
}
