package at.martinahrer.bootolithic.learningtest

import org.springframework.core.GenericTypeResolver
import spock.lang.Specification


class GenericTypeResolverLearningSpec extends Specification {
    def "extract type parameter" () {
        expect:
        def types=GenericTypeResolver.resolveTypeArguments(SomeInterfaceImplementation, SomeInterface)

        types.length > 0
        types[0].isAssignableFrom(Integer)
    }
}

interface SomeInterface<T> {}

class SomeInterfaceImplementation implements SomeInterface<Integer> {
}