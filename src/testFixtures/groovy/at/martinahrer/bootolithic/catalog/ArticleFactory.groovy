package at.martinahrer.bootolithic.catalog

import at.martinahrer.bootolithic.test.AbstractAssociationAdapter
import at.martinahrer.bootolithic.test.AbstractEntityFactory
import at.martinahrer.bootolithic.test.EntityFactory
import at.martinahrer.bootolithic.test.NullAssociationAdapter

import static java.util.Optional.empty

@EntityFactory
class ArticleFactory extends AbstractEntityFactory<Article> {
    RebateFactory rebateFactory = new RebateFactory()
    SizeFactory sizeFactory = new SizeFactory()
    AbstractAssociationAdapter associationAdapter = new NullAssociationAdapter()

    ArticleFactory(Optional<RebateFactory> rebateFactory, Optional<SizeFactory> sizeFactory, Optional<AbstractAssociationAdapter> associationAdapter) {
        rebateFactory.ifPresent {this.rebateFactory = it }
        sizeFactory.ifPresent {this.sizeFactory = it }
        associationAdapter.ifPresent { it -> this.associationAdapter = it }
    }

    static ArticleFactory of () {
        return new ArticleFactory(empty(), empty(), empty())
    }
    @Override
    protected Map newDefaults(Map args) {
        def defaults = [
            id : new ArticleIdentifier(),
            name : "USD",
            numberOfDecimalDigits: BigDecimal.valueOf(3),
            price: 10.euro,
            rebate: args.rebate ? args.rebate : associationAdapter.apply(rebateFactory.newObject()).getId(),
            size: args.size ? args.size : associationAdapter.apply(sizeFactory.newObject()).getId()
        ]
        defaults << args
        return defaults
    }

    @Override
    protected Article newInstance() {
        return new Article()
    }
}
