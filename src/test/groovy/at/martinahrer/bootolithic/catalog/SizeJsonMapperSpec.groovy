package at.martinahrer.bootolithic.catalog


import spock.lang.Specification
import tools.jackson.databind.json.JsonMapper

class SizeJsonMapperSpec extends Specification {
    def mapper = JsonMapper.builder()
        .findAndAddModules()
        .build()

    def "serialize jmolecules identifier"() {
        when:
        def json = mapper.writeValueAsString(new SizeIdentifier("USD"))

        then:
        json.equals('"USD"')

        when:
        json = mapper.writeValueAsString(new Size(id: new SizeIdentifier("USD")))

        then:
        json.contains('"id":"USD"')
    }

    def "deserialize jmolecules identifier"() {
        given:
        def content = """{
            "name": "10%",
            "size": "size"
        }"""

        when:
        def rebate = mapper.readValue(content, Article)

        then:
        rebate.id != null
        rebate.size == new SizeIdentifier('size')
    }

}