package at.martinahrer.bootolithic.catalog;

import lombok.Value;
import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
@Value(staticConstructor = "of")
public class BaseKey {
    @BaseKeyConstraint
    String value;
}
