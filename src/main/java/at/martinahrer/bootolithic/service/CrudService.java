package at.martinahrer.bootolithic.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CrudService<T, ID> {

    Optional<T> findById(ID id);
    Page<T> findAll(Pageable pageable);
    T create(T entity);
    T update(ID id, T entity);
    void deleteById(ID id);
}
