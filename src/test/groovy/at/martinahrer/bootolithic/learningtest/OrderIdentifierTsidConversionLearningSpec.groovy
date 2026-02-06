package at.martinahrer.bootolithic.learningtest

import at.martinahrer.bootolithic.order.OrderIdentifier
import spock.lang.Specification


class OrderIdentifierTsidConversionLearningSpec extends Specification {

    def "create identifier from TSID string"() {
        expect:
        String id = new OrderIdentifier().id.toString()

        def converted = OrderIdentifier.of(id)

        converted.id.toString() == id
    }

}