package at.martinahrer.bootolithic.catalog;

import at.martinahrer.bootolithic.catalog.internal.ArticleRepository;
import at.martinahrer.bootolithic.catalog.internal.RebateRepository;
import at.martinahrer.bootolithic.catalog.internal.SizeRepository;
import org.jmolecules.ddd.annotation.Service;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Optional;

@Service
public class ArticlePriceCalculatorService {
    private final SizeRepository sizeRepository;
    private final RebateRepository rebateRepository;
    private final ArticleRepository articleRepository;

    public ArticlePriceCalculatorService(SizeRepository sizeRepository, RebateRepository rebateRepository, ArticleRepository articleRepository) {
        this.sizeRepository = sizeRepository;
        this.rebateRepository = rebateRepository;
        this.articleRepository = articleRepository;
    }

    public Optional<MonetaryAmount> calculate(ArticleIdentifier id) {
        return articleRepository.findById(id)
            .flatMap(this::calculate);
    }

    public Optional<MonetaryAmount> calculate(Article article) {
        return rebateRepository.findById(article.getRebate())
            .flatMap(marketPrice -> calculate(article, marketPrice));
    }

    public Optional<MonetaryAmount> calculate(Article article, Rebate rebate) {
        return sizeRepository.findById(article.getSize())
            .flatMap(rate -> Optional.of(calculate(article, rebate, rate)));
    }

    private MonetaryAmount calculate(Article article, Rebate rebate, Size size) {
        MathContext priceCalculationMathContext = article.getPriceCalculationMathContext();
        var surcharge = article.getPrice().multiply(size.getSurcharge().add(BigDecimal.valueOf(1), priceCalculationMathContext));
        var rebateAmount = article.getPrice().multiply(rebate.getRebate().add(BigDecimal.valueOf(1), priceCalculationMathContext));
        return article.getPrice().add(surcharge).subtract(rebateAmount);
    }

}
