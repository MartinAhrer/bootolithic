package at.martinahrer.bootolithic.test.web

import at.martinahrer.bootolithic.test.ObjectFactory
import org.jmolecules.ddd.types.Identifier
import org.springframework.data.web.PageableHandlerMethodArgumentResolver
import org.springframework.format.support.DefaultFormattingConversionService
import org.springframework.format.support.FormattingConversionService
import org.springframework.http.MediaType
import org.springframework.http.converter.json.JacksonJsonHttpMessageConverter
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.AbstractMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder
import spock.lang.Specification
import tools.jackson.databind.json.JsonMapper

import java.util.function.Function
import java.util.function.Supplier

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
/**
 * CRUD controller tests exercising GET, POST, PUT and DELETE methods.
 * POST and PUT also tests if validation is in place.
 */
abstract class AbstractStandaloneControllerSpec extends Specification {
    MockMvc mockMvc

    JsonMapper jsonMapper = JsonMapper.builder()
        .findAndAddModules()
        .build()

    /**
     * This creates and wires the controller
     * @return Controller instance
     */
    abstract def buildController()

    abstract def getRequestUriPrefix()

    // id supplier must not respond with a random value
    abstract Supplier<Identifier> getIdentifierSupplier()

    abstract ObjectFactory getResourceFactory()

    /**
     * Inject properties that break resource validation
     * @param resource
     * @return
     */
    Function getInjectInvalidResourceProperties() {
        return  { resource -> resource }
    }

    Identifier identifierValue

    protected setupCustomArgumentResolvers(StandaloneMockMvcBuilder builder) {
        builder.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
    }

    protected setupMessageConverters(StandaloneMockMvcBuilder builder) {
        builder.setMessageConverters(new JacksonJsonHttpMessageConverter())
    }

    protected setupConversionService(FormattingConversionService formattingConversionService) {
    }

    AbstractMockMvcBuilder setupMockMvcBuilder(StandaloneMockMvcBuilder builder) {
        setupMessageConverters(builder)
        setupCustomArgumentResolvers(builder)
        setupConversionService(builder)
        return builder
    }

    protected void setupConversionService(StandaloneMockMvcBuilder builder) {
        def formattingConversionService = new DefaultFormattingConversionService()
        setupConversionService(formattingConversionService)
        builder.setConversionService(formattingConversionService)
    }

    def setup() {
        identifierValue = identifierSupplier.get()

        def controller = buildController()
        def builder = MockMvcBuilders
            .standaloneSetup(controller)
        setupMockMvcBuilder(builder)
        mockMvc = builder.build()
    }

    def "GET /"() {
        when:
        def resultActions = mockMvc
            .perform(get("${requestUriPrefix}")
                .accept(MediaType.APPLICATION_JSON))

        then:
        resultActions
            .andExpect(status().is2xxSuccessful())
            .andExpect(jsonPath('$.content[0].id').value(identifierValue.id.toString()))
            .andDo(log())
    }


    def "GET /{id}"() {
        when:
        def resultActions = mockMvc
            .perform(get("${requestUriPrefix}/{id}", identifierValue.id.toString())
                .accept(MediaType.APPLICATION_JSON))

        then:
        resultActions
            .andExpect(status().is2xxSuccessful())
            .andExpect(jsonPath('$.id').value(identifierValue.id.toString()))
            .andDo(log())
    }


    def "GET /{id} 404"() {
        when:
        def resultActions = mockMvc
            .perform(get("${requestUriPrefix}/{id}", identifierSupplier.get().id.toString())
                .accept(MediaType.APPLICATION_JSON))

        then:
        resultActions
            .andExpect(status().isNotFound())
            .andDo(log())
    }

    def "POST /"() {
        when:
        def resultActions = mockMvc
            .perform(post("${requestUriPrefix}")
                .characterEncoding('UTF-8')
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(serializeResource(resourceFactory.newObject(id: identifierValue))))

        then:
        resultActions
            .andExpect(status().is2xxSuccessful())
            .andExpect(jsonPath('$.id').value(identifierValue.id.toString()))
            .andDo(log())
    }

    def "POST / 400"() {
        when:
        def resultActions = mockMvc
            .perform(post("${requestUriPrefix}")
                .characterEncoding('UTF-8')
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(serializeResource(injectInvalidResourceProperties.apply(resourceFactory.newObject(id: identifierValue)))))

        then:
        resultActions
            .andExpect(status().isBadRequest())
            .andDo(log())
    }

    def "PUT /{id}"() {
        when:
        def resultActions = mockMvc
            .perform(put("${requestUriPrefix}/{id}", identifierValue.id.toString())
                .characterEncoding('UTF-8')
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(serializeResource(resourceFactory.newObject(id: identifierValue))))

        then:
        resultActions
            .andExpect(status().is2xxSuccessful())
            .andExpect(jsonPath('$.id').value(identifierValue.id.toString()))
            .andDo(log())
    }

    def "PUT /{id} 400"() {
        when:
        def resultActions = mockMvc
            .perform(put("${requestUriPrefix}/{id}", identifierValue.id.toString())
                .characterEncoding('UTF-8')
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(serializeResource(injectInvalidResourceProperties.apply(resourceFactory.newObject(id: identifierValue)))))

        then:
        resultActions
            .andExpect(status().isBadRequest())
            .andDo(log())
    }

    String serializeResource(def resource) {
        jsonMapper.writeValueAsString(resource)
    }
}