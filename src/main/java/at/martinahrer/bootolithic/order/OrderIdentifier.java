package at.martinahrer.bootolithic.order;

import org.jmolecules.ddd.types.Identifier;

import java.util.UUID;

public record OrderIdentifier(UUID id) implements Identifier {// rename to value
    // On purpose this is using a UUID to have a different identifier type for show-casing json-api compatibility
    public OrderIdentifier() {
        this(UUID.randomUUID());
    }

    public static OrderIdentifier of(String id) {
        return new OrderIdentifier(UUID.fromString(id));
    }
}