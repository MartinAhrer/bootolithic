package at.martinahrer.bootolithic.order;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
// Until we have a specification for field length, we use a single constraint
public record Address(@AddressFieldConstraint String street,
                      @AddressFieldConstraint String zipCode,
                      @AddressFieldConstraint String city,
                      @AddressFieldConstraint String region,
                      @AddressFieldConstraint String country) {
}
