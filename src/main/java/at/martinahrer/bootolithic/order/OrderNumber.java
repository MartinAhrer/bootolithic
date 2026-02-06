package at.martinahrer.bootolithic.order;

import lombok.Value;
import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
@Value(staticConstructor = "of")
public class OrderNumber {
    @OrderNumberConstraint
    String value;
}
