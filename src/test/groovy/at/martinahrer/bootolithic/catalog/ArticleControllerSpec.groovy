package at.martinahrer.bootolithic.catalog

import at.martinahrer.bootolithic.service.CrudService
import at.martinahrer.bootolithic.test.web.AbstractStandaloneControllerSpec
import at.martinahrer.bootolithic.web.CrudController
import org.jmolecules.ddd.types.Identifier
import org.springframework.data.domain.PageImpl

import java.util.function.Function
import java.util.function.Supplier

class ArticleControllerSpec extends AbstractStandaloneControllerSpec implements ConverterSetupTrait {

    ArticleFactory objectFactory = ArticleFactory.of()
    ArticleFactory resourceFactory = objectFactory

    Supplier<Identifier> identifierSupplier = { -> new ArticleIdentifier() }

    String requestUriPrefix = "/public/catalog/articles"

    @Override
    Function getInjectInvalidResourceProperties() {
        return {
            Article resource ->
                resource.numberOfDecimalDigits = new BigDecimal(-1) // this will force a constraint validation error
                resource
        }
    }

    CrudController buildController() {
        CrudService service = Mock(ArticleService)

        // should be possible to move that to some generic mock support for CRUD service operations
        def object = objectFactory.newObject(id: identifierValue)
        service.findAll(_) >> new PageImpl([object])
        service.findById(identifierValue) >> Optional.of(object)
        service.findById({ !identifierValue.equals(it)}) >> Optional.empty()
        service.create(_) >> { Article entity -> entity }
        service.update(_, _) >> { Identifier id, Article entity -> entity }

        return new ArticleController(service)
    }

}