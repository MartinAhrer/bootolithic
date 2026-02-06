package at.martinahrer.bootolithic.order

import at.martinahrer.bootolithic.test.AbstractObjectFactory
import at.martinahrer.bootolithic.test.EntityFactory

@EntityFactory
class CustomerFactory extends AbstractObjectFactory<Customer> {
    protected Map newDefaults(Map args) {
        return [
            name: "name",
            email: EmailAddress.of("email"),
            iban: Iban.of("iban")
        ]
    }

    @Override
    Customer newObject(Map args = [:]) {
        Map defaults = newDefaults(args)
        defaults << args
        return new Customer(defaults.name, defaults.email, defaults.iban)
    }
}
