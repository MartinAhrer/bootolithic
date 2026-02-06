package at.martinahrer.bootolithic.catalog;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.jmolecules.ddd.annotation.Association;
import org.jmolecules.ddd.types.AggregateRoot;

import javax.money.MonetaryAmount;
import java.math.MathContext;
import java.math.RoundingMode;

@Getter
@Setter // for mapstruct -> ugly investigation needed
public class Article implements AggregateRoot<Article, ArticleIdentifier> {
    public static final String DEFAULT_CURRENCY_CODE = "EUR";
    private ArticleIdentifier id;

    @NameConstraint
    @NotNull
    private String name;

    @Positive
    @NotNull
    private Integer numberOfDecimalDigits;

    @NotNull
    @AttributeOverrides({
        @AttributeOverride(name = "amount", column = @Column(name = "price_amount")),
        @AttributeOverride(name = "currency", column = @Column(name = "price_currency")),
    })
    private MonetaryAmount price;

    @Association(aggregateType = Rebate.class)
    @AttributeOverride(name = "id", column = @Column(name = "rebate_id"))
    @NotNull
    private RebateIdentifier rebate;

    @AttributeOverride(name = "id", column = @Column(name = "size_id"))
    @Association(aggregateType = Size.class)
    @NotNull
    private SizeIdentifier size;

    @JsonCreator
    public Article() {
        this(new ArticleIdentifier());
    }

    public Article(ArticleIdentifier id) {
        this.id = id;
    }

    @Transient
    @JsonIgnore
    public MathContext getPriceCalculationMathContext() {
        return new MathContext(getNumberOfDecimalDigits(), RoundingMode.HALF_UP);
    }
}
