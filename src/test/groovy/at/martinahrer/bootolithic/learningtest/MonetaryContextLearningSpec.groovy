package at.martinahrer.bootolithic.learningtest

import org.javamoney.moneta.spi.MoneyUtils
import spock.lang.Specification

import javax.money.MonetaryContextBuilder
import java.math.RoundingMode

class MonetaryContextLearningSpec extends Specification {
    def value = "123.456"

    def "rounding"() {
        given:
        def mc = MonetaryContextBuilder
            .of()
            .setFixedScale(true)
            .setMaxScale(scale)
            .set(RoundingMode.class, RoundingMode.HALF_UP)
            .build()

        when:
        def result = MoneyUtils.getBigDecimal(new BigDecimal(value), mc).toString()

        then:
        expected == MoneyUtils.getBigDecimal(new BigDecimal(value), mc).toString()

        where:
        expected  | scale
        "123.456" | 3
        "123.46"  | 2
        "123.5"   | 1
    }
}