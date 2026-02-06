package at.martinahrer.bootolithic.test

abstract class AbstractEntityFactory<T> extends AbstractObjectFactory<T> {
    abstract protected Map newDefaults(Map args = [:])
    abstract protected T newInstance()
    /**
     * Use to add any children (persistent collection elements or references that need special processing)
     *
     * @param args
     * @param object
     */
    protected void addChildren(Map args, T object) {}

    @Override
    T newObject(Map args = [:]) {
        def defaults = newDefaults(args)

        T object = newInstance()
        defaults.each { key, value ->
            object."${key}" = value
        }
        addChildren(args, object)
        return object
    }

}
