package at.martinahrer.bootolithic.service;

import at.martinahrer.bootolithic.persistence.EntityMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * This is currently just a skeleton for a service.
 * We need to implement transactionality here.
 * See <a href="https://github.com/cloudflightio/archunit-cleancode-verifier/blob/master/rules/spring.md#transaction-demarcation-is-part-of-the-service-layer">Transaction demarcation is part of the service layer</a>
 *
 * @param <T> the type of the entity
 * @param <ID> the type of the ID of the entity
 */

public abstract class AbstractCrudService<T, ID> implements CrudService<T, ID>{
    protected final JpaRepository<T, ID> repository;
    protected final EntityMapper<T> entityMapper;

    public AbstractCrudService(JpaRepository<T, ID> repository, EntityMapper<T> entityMapper) {
        this.repository = repository;
        this.entityMapper = entityMapper;
    }

    @Transactional(readOnly = true)
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional
    @Override
    public T create(T entity) {
        return repository.save(entity);
    }

    @Transactional
    @Override
    public T update(ID id, T entity) {
        var found=repository.findById(id)
            .orElseThrow(() -> new EmptyResultDataAccessException(entity.getClass().getSimpleName(), 1));
        return repository.save(map(entity, found));
    }

    @Transactional
    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    protected T map(T source, T target) {
        entityMapper.map(source, target);
        return target;
    }
}
