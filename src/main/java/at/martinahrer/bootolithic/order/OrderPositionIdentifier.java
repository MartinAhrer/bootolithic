package at.martinahrer.bootolithic.order;

import io.hypersistence.tsid.TSID;
import org.jmolecules.ddd.types.Identifier;


// On purpose this is using a long to have a different identifier type for show-casing json-api compatibility
public record OrderPositionIdentifier (long id) implements Identifier {
    public OrderPositionIdentifier() {
        this(TSID.fast().toLong());
    }

    public static OrderPositionIdentifier of(String value) {
        return new OrderPositionIdentifier(TSID.from(Long.parseLong(value)).toLong());
    }
}