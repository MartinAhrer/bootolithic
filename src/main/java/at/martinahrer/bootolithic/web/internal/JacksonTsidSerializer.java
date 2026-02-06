package at.martinahrer.bootolithic.web.internal;

import io.hypersistence.tsid.TSID;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ser.std.StdSerializer;

class JacksonTsidSerializer extends StdSerializer<TSID> {

    protected JacksonTsidSerializer() {
        super(TSID.class);
    }

    @Override
    public void serialize(TSID value, JsonGenerator gen, SerializationContext provider) throws JacksonException {
        provider.findValueSerializer(String.class).serialize(value.toString(), gen, provider);
    }
}
