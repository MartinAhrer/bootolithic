package at.martinahrer.bootolithic.persistence;

public interface EntityMapper<T> {
     T map(T source, T target);
}
