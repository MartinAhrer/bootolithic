package at.martinahrer.bootolithic.web.internal;

import io.hypersistence.tsid.TSID;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.deser.std.StdDeserializer;

class JacksonTsidDeserializer extends StdDeserializer<TSID> {

    protected JacksonTsidDeserializer() {
        super(TSID.class);
    }

    @Override
    public TSID deserialize(JsonParser p, DeserializationContext ctxt) throws tools.jackson.core.JacksonException {
        return TSID.from(p.getValueAsString());
    }
}
