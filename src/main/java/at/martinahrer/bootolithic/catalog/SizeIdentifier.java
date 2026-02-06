package at.martinahrer.bootolithic.catalog;

import org.jmolecules.ddd.types.Identifier;

public record SizeIdentifier(@SizeIdentifierConstraint String id) implements Identifier {
    public static SizeIdentifier of(String id) {
        return new SizeIdentifier(id);
    }
}