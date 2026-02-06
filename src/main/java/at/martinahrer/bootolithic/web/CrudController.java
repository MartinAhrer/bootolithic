package at.martinahrer.bootolithic.web;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

public interface CrudController<T, ID> {
    EntityModel<T> findById(ID id);

    PagedModel<T> findAll(Pageable pageable);

    EntityModel<T> create(@Valid T resource);

    EntityModel<T> update(ID id, @Valid T resource);

    void deleteById(ID id);
}
