package at.martinahrer.bootolithic.integrationtest

import at.martinahrer.bootolithic.DatabaseTestcontainersConfiguration
import at.martinahrer.bootolithic.integrationtest.persistence.JpaAssociationAdapter
import at.martinahrer.bootolithic.test.AbstractAssociationAdapter
import at.martinahrer.bootolithic.test.AssociationDetector
import at.martinahrer.bootolithic.test.JMoleculesAssociationDetector
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import([DatabaseTestcontainersConfiguration])
class IntegrationTestConfiguration {

    @Bean
    AssociationDetector associationDetector() {
        new JMoleculesAssociationDetector()
    }

    @Bean
    AbstractAssociationAdapter associationAdapter() {
        new JpaAssociationAdapter()
    }
}
