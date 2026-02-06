package at.martinahrer.bootolithic.web

import at.martinahrer.bootolithic.catalog.Rebate
import at.martinahrer.bootolithic.catalog.RebateController
import at.martinahrer.bootolithic.catalog.RebateIdentifier
import at.martinahrer.bootolithic.catalog.RebateService
import spock.lang.Specification

class AbstractCrudControllerUpdateSpec extends Specification {

    def "updating the id is not permitted" () {
        given:
        def service = Mock(RebateService)
        service.update(_, _) >> { id, entity -> entity }
        AbstractCrudController controller = new RebateController(service) {}

        when:
        controller.update(new RebateIdentifier(), new Rebate(new RebateIdentifier()))

        then:
        thrown(IllegalArgumentException)
    }

}