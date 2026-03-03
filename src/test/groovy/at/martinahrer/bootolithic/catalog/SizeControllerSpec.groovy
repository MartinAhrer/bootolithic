package at.martinahrer.bootolithic.catalog

import at.martinahrer.bootolithic.service.CrudService
import at.martinahrer.bootolithic.test.web.AbstractStandaloneControllerSpec
import at.martinahrer.bootolithic.web.CrudController
import org.apache.commons.lang3.RandomStringUtils
import org.jmolecules.ddd.types.Identifier
import org.springframework.data.domain.PageImpl

import java.util.function.Function
import java.util.function.Supplier

class SizeControllerSpec extends AbstractStandaloneControllerSpec implements ConverterSetupTrait {

    SizeFactory objectFactory = new SizeFactory()
    SizeFactory resourceFactory = objectFactory

    Supplier<Identifier> identifierSupplier = { -> new SizeIdentifier(RandomStringUtils.insecure().nextAlphabetic(SizeIdentifierConstraint.MAX_LENGTH)) }

    String requestUriPrefix = "/public/catalog/sizes"

    @Override
    Function getInjectInvalidResourceProperties() {
        return {
            Size resource ->
                resource.surcharge = new BigDecimal(-1) // this will force a constraint validation error
                resource
        }
    }

    CrudController buildController() {
        CrudService service = Mock(SizeService)

        // should be possible to move that to some generic mock support for CRUD service operations
        def object = objectFactory.newObject(id: identifierValue)
        service.findAll(_) >> new PageImpl([object])
        service.findById(identifierValue) >> Optional.of(object)
        service.findById({ !identifierValue.equals(it)}) >> Optional.empty()
        service.create(_) >> { Size entity -> entity }
        service.update(_, _) >> { Identifier id, Size entity -> entity }

        return new SizeController(service)
    }

}