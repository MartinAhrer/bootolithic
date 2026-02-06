package at.martinahrer.bootolithic.integrationtest.persistence

import at.martinahrer.bootolithic.test.AbstractAssociationAdapter
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext

class JpaAssociationAdapter<T, R> extends AbstractAssociationAdapter<T, R> {
    JpaAssociationAdapter(EntityManager entityManager) {
        this.entityManager = entityManager
    }

    @PersistenceContext
    EntityManager entityManager

    @Override
    R apply(T t) {
        t == null ? null : entityManager.merge(t) as R
    }
}
