package at.martinahrer.bootolithic.catalog

import at.martinahrer.bootolithic.test.AbstractEntityFactory
import at.martinahrer.bootolithic.test.EntityFactory

@EntityFactory
class SizeFactory extends AbstractEntityFactory<Size> {
    @Override
    protected Map newDefaults(Map args) {
        def defaults = [
            id : new SizeIdentifier('XXXL'),
            name: 'XXX Large',
            surcharge: BigDecimal.valueOf(0.02),
        ]
        defaults << args
        return defaults
    }

    @Override
    protected Size newInstance() {
        return new Size()
    }
}