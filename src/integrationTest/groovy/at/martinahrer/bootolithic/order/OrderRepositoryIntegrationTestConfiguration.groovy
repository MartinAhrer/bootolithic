package at.martinahrer.bootolithic.order

import at.martinahrer.bootolithic.catalog.CatalogRepositoryIntegrationTestConfiguration
import at.martinahrer.bootolithic.test.EntityFactory
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType
import org.springframework.context.annotation.Import

@ComponentScan(
    includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = [ EntityFactory])
)
@Import(CatalogRepositoryIntegrationTestConfiguration)
class OrderRepositoryIntegrationTestConfiguration {
}
