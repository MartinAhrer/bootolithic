package at.martinahrer.bootolithic.web;

public class AbstractResourceNotFoundException extends RuntimeException {

    public AbstractResourceNotFoundException(Class<?> resourceClass, Object reference) {
        this(resourceNotFoundExceptionMessage(reference, resourceClass));
    }
    public AbstractResourceNotFoundException(Class<?> resourceClass, Object reference, Throwable cause) {
        this(resourceNotFoundExceptionMessage(reference, resourceClass), cause);
    }

    protected AbstractResourceNotFoundException() {
        super();
    }

    protected AbstractResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    protected AbstractResourceNotFoundException(String message) {
        super(message);
    }

    protected AbstractResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    protected static String resourceNotFoundExceptionMessage(Object reference, Class<?> clazz) {
        return String.format("%s referenced by %s not found", clazz.getSimpleName(), reference);
    }
}
