package at.martinahrer.bootolithic.catalog


import spock.lang.Specification

class ArticleFactorySpec extends Specification {

    private ArticleFactory articleFactory = ArticleFactory.of ()

    def 'default values are set'() {
        expect:
        def article = articleFactory.newObject()
        article.name == 'USD'
    }

    def 'id value is set'() {
        expect:
        def article = articleFactory.newObject(id: new ArticleIdentifier())
        article.id != null
    }
}
