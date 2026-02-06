package at.martinahrer.bootolithic.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public final class ResourceNotFoundException extends AbstractResourceNotFoundException {
    public ResourceNotFoundException(Class<?> resourceClass, Object reference) {
        super(resourceClass, reference);
    }

    public ResourceNotFoundException(Class<?> resourceClass, Object reference, Throwable cause) {
        super(resourceClass, reference, cause);
    }
}