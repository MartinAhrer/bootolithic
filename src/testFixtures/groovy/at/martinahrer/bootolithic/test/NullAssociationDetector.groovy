package at.martinahrer.bootolithic.test

class NullAssociationDetector implements AssociationDetector {
    @Override
    boolean isAssociation(Class<?> beanClass, String propertyName) {
        false
    }
}
