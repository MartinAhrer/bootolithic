package at.martinahrer.bootolithic.web.internal;

import com.toedter.spring.hateoas.jsonapi.JsonApiConfiguration;
import org.jmolecules.jackson3.JMoleculesModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonApiJMoleculesJacksonConfiguration {
    @Bean
    public JsonApiConfiguration jsonApiConfiguration() {
        return new JsonApiConfiguration()
            .withMapperCustomizer(builder ->
                builder
                    .addModule(new JMoleculesModule())
                    .addModule(new JacksonTsidModule())
            );
    }
}
