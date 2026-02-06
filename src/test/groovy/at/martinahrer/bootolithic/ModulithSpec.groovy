package at.martinahrer.bootolithic

import org.springframework.modulith.core.ApplicationModules
import org.springframework.modulith.docs.Documenter
import spock.lang.Specification

class ModulithSpec extends Specification {

    ApplicationModules modules = ApplicationModules.of(ServerApplication);

    def verifiesArchitecture() {
        when:
        modules.verify();

        then:
        noExceptionThrown()
    }

    def createDocumentation() {
        setup:
        new Documenter(modules).writeDocumentation();
    }
}
