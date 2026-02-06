package at.martinahrer.bootolithic.catalog

import at.martinahrer.bootolithic.test.EntityFactory
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.ComponentScan.Filter
import org.springframework.context.annotation.FilterType
/**
 * For now this should only support the repository tests
 */
@ComponentScan(
    includeFilters = @Filter(type = FilterType.ANNOTATION, classes = [ EntityFactory])
)
class CatalogRepositoryIntegrationTestConfiguration {
}
