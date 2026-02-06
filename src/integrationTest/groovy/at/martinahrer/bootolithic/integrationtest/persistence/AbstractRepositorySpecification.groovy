package at.martinahrer.bootolithic.integrationtest.persistence

import at.martinahrer.bootolithic.test.AbstractEntityFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.transaction.Transactional
import org.jmolecules.ddd.types.AggregateRoot
import org.jmolecules.ddd.types.Identifier
import org.springframework.data.repository.Repository
import org.springframework.test.annotation.Rollback
import spock.lang.Specification

@Rollback
@Transactional
abstract class AbstractRepositorySpecification<T extends AggregateRoot, ID extends Identifier> extends Specification {
    @PersistenceContext
    EntityManager entityManager

    abstract Repository<T, ID> getRepository()

    abstract AbstractEntityFactory<T> getEntityFactory()

    protected T newEntity() {
        entityFactory.newObject()
    }

    Closure flushStrategy = { entityManager.flush() }
}
