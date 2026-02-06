package at.martinahrer.bootolithic.integrationtest.persistence

import org.jmolecules.ddd.types.AggregateRoot
import org.jmolecules.ddd.types.Identifier
import org.springframework.data.repository.CrudRepository

abstract class AbstractAggregateRootRepositorySpecification<T extends AggregateRoot, ID extends Identifier> extends AbstractRepositorySpecification<T, ID> {
    CrudRepository<T, ID> getRepository() {
        super.getRepository() as CrudRepository<T, ID>
    }

    def "find by id" () {
        given:
        AggregateRoot aggregateRoot = repository.save(entityFactory.newObject())

        when:
        Optional<AggregateRoot> result = repository.findById(aggregateRoot.id)

        then:
        result.isPresent()
    }

    def "find all"() {
        given:
        AggregateRoot aggregateRoot =  repository.save(entityFactory.newObject())

        when:
        def result = repository.findAll()

        then:
        result.contains(aggregateRoot)
    }

    def "save"() {
        given:
        def aggregateRoot = entityFactory.newObject()

        when:
        aggregateRoot = repository.save(aggregateRoot)
        entityManager.flush()
        entityManager.clear() // ensure entities are reloaded from DB
        aggregateRoot.id != null // generated ids are set after persisting

        then:
        def found = repository.findById (aggregateRoot.id)
        found.isPresent()
    }

    def "saved entity has assigned id" () {
        given:
        AggregateRoot aggregateRoot =  repository.save(entityFactory.newObject())

        when:
        AggregateRoot result = repository.save(aggregateRoot)

        then:
        result.id !=null
    }

    def "delete by id" () {
        def object = entityFactory.newObject()
        given:
        AggregateRoot aggregateRoot =  repository.save(object)

        when:
        repository.deleteById(aggregateRoot.id)

        then:
        !repository.findById(aggregateRoot.id).isPresent()
    }
}
