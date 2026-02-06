package at.martinahrer.bootolithic.test

abstract class AbstractObjectFactory<T> implements ObjectFactory<T> {
    T call(Map args=[:]) {
        newObject(args)
    }
}
