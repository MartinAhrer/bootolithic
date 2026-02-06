package at.martinahrer.bootolithic.test

interface ObjectFactory<T> {
    // this is the factory method
    T newObject(Map args)
}

