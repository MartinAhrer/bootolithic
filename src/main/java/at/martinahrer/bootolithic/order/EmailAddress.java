package at.martinahrer.bootolithic.order;


import jakarta.validation.constraints.Email;
import lombok.Value;
import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
@Value(staticConstructor = "of")
public class EmailAddress {
    @Email
    String value;
}
