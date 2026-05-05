package at.martinahrer.bootolithic.integrationtest

import at.martinahrer.bootolithic.DatabaseTestcontainersConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import([DatabaseTestcontainersConfiguration])
class RepositoryIntegrationTestConfiguration {
}
