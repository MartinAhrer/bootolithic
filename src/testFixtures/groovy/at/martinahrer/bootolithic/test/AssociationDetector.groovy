package at.martinahrer.bootolithic.test

interface AssociationDetector {
    boolean isAssociation(Class<?> beanClass, String propertyName)
}
