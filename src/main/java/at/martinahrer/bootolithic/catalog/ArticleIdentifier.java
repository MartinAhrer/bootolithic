package at.martinahrer.bootolithic.catalog;

import io.hypersistence.tsid.TSID;
import org.jmolecules.ddd.types.Identifier;

public record ArticleIdentifier(TSID id) implements Identifier {
    public ArticleIdentifier() {
        this(TSID.fast());
    }
    public static ArticleIdentifier of(String id) {
        return new ArticleIdentifier(TSID.from(id));
    }
}