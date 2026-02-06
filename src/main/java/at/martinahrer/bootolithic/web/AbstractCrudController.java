package at.martinahrer.bootolithic.web;

import at.martinahrer.bootolithic.service.AbstractCrudService;
import jakarta.validation.Valid;
import org.jmolecules.ddd.types.Identifiable;
import org.springframework.core.GenericTypeResolver;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.function.BiConsumer;

import static at.martinahrer.bootolithic.web.PageMetadataBuilder.pageMetadata;

public class AbstractCrudController<T extends Identifiable<ID>, ID> implements CrudController<T, ID> {
    private final AbstractCrudService<T, ID> service;

    private TypeParameterInfo typeParameterInfo;

    private BiConsumer<T, ID> updateValidator = (entity, id) -> {
        if (!entity.getId().equals(id)) {
            throw new IllegalArgumentException(String.format("Resource ID %s does not match ID %s", entity.getId(), id));
        }
    };

    public AbstractCrudController(AbstractCrudService<T, ID> service) {
        this.service = service;
        this.typeParameterInfo = buildTypeParameterInfo();
    }

    record TypeParameterInfo(Class<?> domainType, Class<?> domainIdType) {
        public TypeParameterInfo(Class<?>[] typeParameterClasses) {
            this(typeParameterClasses[0], typeParameterClasses[1]);
        }
    }

    private TypeParameterInfo buildTypeParameterInfo() {
        Class<?>[] typeParameterClasses = GenericTypeResolver.resolveTypeArguments(this.getClass(), CrudController.class);
        return new TypeParameterInfo(typeParameterClasses);
    }

    @GetMapping("/{id}")
    public EntityModel<T> findById(@PathVariable ID id) {
        T content = service
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(typeParameterInfo.domainType(), id));
        return EntityModel.of(content);
    }

    @GetMapping("")
    public PagedModel<T> findAll(final Pageable pageable) {
        Page<T> page = service.findAll(pageable);
        return PagedModel.of(page.getContent(), pageMetadata().page(page).build());
    }

    @PostMapping(value = "", consumes = {"application/json"})
    public EntityModel<T> create(@RequestBody @Valid T resource) {
        return EntityModel.of(service.create(resource));
    }

    @PostMapping(value = "", consumes = {"application/vnd.api+json"})
    public EntityModel<T> create(@RequestBody @Valid EntityModel<T> resource) {
        return EntityModel.of(service.create(resource.getContent()));
    }

    @PutMapping("/{id}")
    public EntityModel<T> update(@PathVariable ID id, @RequestBody @Valid T resource) {
        try {
            updateValidator.accept(resource, id);
            return EntityModel.of(service.update(id, resource));
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(resource.getClass(), id);
        }
    }

    @PutMapping(value = "/{id}", consumes = {"application/vnd.api+json"})
    public EntityModel<T> update(@PathVariable ID id, @RequestBody @Valid EntityModel<T> resource) {
        try {
            updateValidator.accept(resource.getContent(), id);
            return EntityModel.of(service.update(id, resource.getContent()));
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(resource.getClass(), id);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable ID id) {
        service.deleteById(id);
    }
}
