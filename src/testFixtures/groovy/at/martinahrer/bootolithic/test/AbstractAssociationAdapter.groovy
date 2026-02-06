package at.martinahrer.bootolithic.test

import java.util.function.Function

abstract class AbstractAssociationAdapter<T,R> implements Function<T, R> {
    /**
     * <code>call</code> allows the adapter to be called like a closure
     *
     * @param aggregateRoot
     * @return
     */
    R call(T aggregateRoot) {
        apply(aggregateRoot)
    }
}
