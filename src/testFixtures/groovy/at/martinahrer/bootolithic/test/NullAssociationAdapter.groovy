package at.martinahrer.bootolithic.test

class NullAssociationAdapter<T,R> extends AbstractAssociationAdapter<T, R> {
    @Override
    R apply(T t) {
        return t as R
    }
}
