package at.martinahrer.bootolithic.catalog

import io.hypersistence.tsid.TSID
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.JsonTest
import org.springframework.boot.test.json.JacksonTester
import spock.lang.Specification

@JsonTest
class JMoleculesIdentifierIntegrationSpec extends Specification {

    @Autowired
    JacksonTester<ArticleIdentifier> identifierTester
    @Autowired
    JacksonTester<Article> articleTester

    def id = TSID.fast()
    def marketPriceId = TSID.fast()

    void deserializesIdentifier() throws Exception {
        expect:

        String content = """
            "${id}"
            """
        ArticleIdentifier identifier = identifierTester.parseObject(content)
        identifier == new ArticleIdentifier(id)
    }


    void deserializesArticleIdentifier() throws Exception {
        expect:
        String content = """
        {
          "id": "${id}",
          "name": "USD",
          "factor": 1,
          "numberOfDecimalDigits": 3,
          "shiftMiddle": 1,
          "spread": 2,
          "surcharge": 1,
          "currencyCode": "EUR",
          "marketPrice": "${marketPriceId}"
        }
    """
        Article article = articleTester.parseObject(content)
        article.getId() == new ArticleIdentifier(id)
    }

}
