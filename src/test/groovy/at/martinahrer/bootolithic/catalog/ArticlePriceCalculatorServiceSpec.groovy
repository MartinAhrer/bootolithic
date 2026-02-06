package at.martinahrer.bootolithic.catalog

import at.martinahrer.bootolithic.catalog.internal.ArticleRepository
import at.martinahrer.bootolithic.catalog.internal.RebateRepository
import at.martinahrer.bootolithic.catalog.internal.SizeRepository
import spock.lang.Specification

class ArticlePriceCalculatorServiceSpec extends Specification {

    RebateRepository rebateRepository = Mock()
    ArticleRepository articleRepository = Mock()
    SizeRepository sizeRepository = Mock()

    SizeFactory sizeFactory = new SizeFactory()
    RebateFactory rebateFactory = new RebateFactory()
    ArticleFactory articleFactory = ArticleFactory.of()

    private ArticlePriceCalculatorService articlePriceCalculator = new ArticlePriceCalculatorService(sizeRepository, rebateRepository, articleRepository)

    def "calculate article price"() {
        given:
        def size = sizeFactory.newObject(surcharge: 0.01)
        def rebate = rebateFactory.newObject(rebate: 0.1)
        def article = articleFactory.newObject(rebate: rebate.id, size:size.id)

        articleRepository.findById(article.id) >> Optional.of(article)
        rebateRepository.findById(article.rebate) >> Optional.of(rebate)
        sizeRepository.findById(size.id) >> Optional.of(size)

        when:
        def result = articlePriceCalculator.calculate(article.id).get()

        then:
        result == 9.1.euro
    }

    def "article does not exist"() {
        given:
        def article = articleFactory.newObject()

        articleRepository.findById(article.id) >> Optional.empty()

        when:
        Optional<BigDecimal> price = articlePriceCalculator.calculate(article.id)

        then:
        price.isEmpty()
    }

    def "rebate does not exist"() {
        given:
        def article = articleFactory.newObject()
        articleRepository.findById(article.id) >> Optional.of(article)
        rebateRepository.findById(article.rebate) >> Optional.empty()

        when:
        Optional<BigDecimal> price = articlePriceCalculator.calculate(article.id)

        then:
        price.isEmpty()
    }

    def "size does not exist"() {
        given:
        def article = articleFactory.newObject()
        def rebate = rebateFactory.newObject()

        articleRepository.findById(article.id) >> Optional.of(article)
        rebateRepository.findById(article.rebate) >> Optional.of(rebate)
        sizeRepository.findById(article.size) >> Optional.empty()

        when:
        Optional<BigDecimal> price = articlePriceCalculator.calculate(article.id)

        then:
        price.isEmpty()
    }
}