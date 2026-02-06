package at.martinahrer.bootolithic.order

import at.martinahrer.bootolithic.catalog.Article
import at.martinahrer.bootolithic.catalog.ArticleFactory
import at.martinahrer.bootolithic.catalog.RebateFactory
import at.martinahrer.bootolithic.catalog.SizeFactory
import at.martinahrer.bootolithic.test.AbstractAssociationAdapter
import at.martinahrer.bootolithic.test.AbstractEntityFactory
import at.martinahrer.bootolithic.test.EntityFactory
import at.martinahrer.bootolithic.test.NullAssociationAdapter

@EntityFactory
class OrderPositionFactory extends AbstractEntityFactory<OrderPosition> {
    RebateFactory rebateFactory = new RebateFactory()
    SizeFactory sizeFactory = new SizeFactory()
    ArticleFactory articleFactory = ArticleFactory.of()
    AbstractAssociationAdapter associationAdapter = new NullAssociationAdapter()


    OrderPositionFactory(Optional<ArticleFactory> articleFactory, Optional<RebateFactory> rebateFactory, Optional<SizeFactory> sizeFactory, Optional<AbstractAssociationAdapter> associationAdapter) {
        articleFactory.ifPresent {this.articleFactory = it }
        rebateFactory.ifPresent {this.rebateFactory = it }
        sizeFactory.ifPresent {this.sizeFactory = it }
        associationAdapter.ifPresent {  this.associationAdapter = it }
    }

    static OrderPositionFactory of() {
        return new OrderPositionFactory(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty())
    }

    @Override
    protected Map newDefaults(Map args) {
        def defaults = [:]

        // if article is passed from args, then also rate and price must be passed
        if (!args.article) {
            def article= buildPersistentArticle()
            defaults.article = article.id
            defaults.quantity = 10
            defaults.price = 10.euro
        } else {
            defaults.article = args.article
            defaults.quantity = 10
            defaults.price = 10.euro
        }
        defaults << args
        return defaults
    }

    private Article buildPersistentArticle() {
        def rebate = associationAdapter.apply(rebateFactory.newObject())
        def size = associationAdapter.apply(sizeFactory.newObject())
        def article = associationAdapter.apply(articleFactory.newObject(rebate: rebate.id, size: size.id))

        return article
    }


    @Override
    protected OrderPosition newInstance() {
        return new OrderPosition()
    }
}
