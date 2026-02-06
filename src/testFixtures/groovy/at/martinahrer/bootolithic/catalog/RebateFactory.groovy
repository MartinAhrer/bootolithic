package at.martinahrer.bootolithic.catalog


import at.martinahrer.bootolithic.test.AbstractEntityFactory
import at.martinahrer.bootolithic.test.EntityFactory

@EntityFactory
class RebateFactory extends AbstractEntityFactory<Rebate> {

    @Override
    Map newDefaults(Map args = [:]) {
        def defaults = [
            id: new RebateIdentifier(),
            name: 'Mass discount',
            rebate: BigDecimal.valueOf(0.10),
        ]
        defaults << args
        return defaults
    }

    protected Rebate newInstance() {
        return new Rebate()
    }
}