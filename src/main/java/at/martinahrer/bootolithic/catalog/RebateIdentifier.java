package at.martinahrer.bootolithic.catalog;

import io.hypersistence.tsid.TSID;
import org.jmolecules.ddd.types.Identifier;


public record RebateIdentifier(TSID id) implements Identifier {
    public RebateIdentifier() {
        this(TSID.fast());
    }

    public static RebateIdentifier of(String id) {
        return new RebateIdentifier(TSID.from(id));
    }
}