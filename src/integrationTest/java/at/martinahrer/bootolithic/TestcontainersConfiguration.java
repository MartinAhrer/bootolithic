package at.martinahrer.bootolithic;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;

@TestConfiguration(proxyBeanMethods = false)
@Import(DatabaseTestcontainersConfiguration.class)
public class TestcontainersConfiguration {
}
