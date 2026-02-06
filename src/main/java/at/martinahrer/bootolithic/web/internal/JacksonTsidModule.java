package at.martinahrer.bootolithic.web.internal;

import io.hypersistence.tsid.TSID;
import tools.jackson.databind.module.SimpleModule;

public class JacksonTsidModule extends SimpleModule {

    public JacksonTsidModule() {
        super("jackson-module");

        addSerializer(new JacksonTsidSerializer());
        addDeserializer(TSID.class, new JacksonTsidDeserializer());
    }
}
