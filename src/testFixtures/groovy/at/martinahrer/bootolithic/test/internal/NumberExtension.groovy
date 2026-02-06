package at.martinahrer.bootolithic.test.internal

import org.javamoney.moneta.Money

import javax.money.MonetaryAmount

class NumberExtension {
    static MonetaryAmount getEuro(Number self) {
        Money.of(self, "EUR")
    }

    static MonetaryAmount getUsd(Number self) {
        Money.of(self, "USD")
    }

}
