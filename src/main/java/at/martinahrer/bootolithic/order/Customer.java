package at.martinahrer.bootolithic.order;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public record Customer(@NameConstraint String name, EmailAddress email, Iban iban) {
}
