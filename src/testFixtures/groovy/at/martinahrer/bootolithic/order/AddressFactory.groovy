package at.martinahrer.bootolithic.order

import at.martinahrer.bootolithic.test.AbstractObjectFactory
import at.martinahrer.bootolithic.test.EntityFactory

@EntityFactory
class AddressFactory extends AbstractObjectFactory<Address>{
    protected Map newDefaults(Map args) {
        return [
            street: "street",
            zipCode: "zipCode",
            city: "city",
            region: "region",
            country: "country"
        ]
    }

    @Override
    Address newObject(Map args = [:]) {
        Map defaults = newDefaults(args)
        defaults << args
        return new Address(defaults.street, defaults.zipCode,defaults.city, defaults.region, defaults.country)
    }
}
