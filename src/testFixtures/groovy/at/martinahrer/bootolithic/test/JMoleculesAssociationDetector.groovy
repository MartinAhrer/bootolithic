package at.martinahrer.bootolithic.test

import org.jmolecules.ddd.types.Association

import java.lang.reflect.Field

class JMoleculesAssociationDetector implements AssociationDetector {
    @Override
    boolean isAssociation(Class<?> beanClass, String propertyName) {
        Optional<Field> field = findField(beanClass, propertyName)
        if (field.isPresent()) {
            isAssociationType(field.get())
        } else {
            false
        }
    }

    private boolean isAssociationType(Field field) {
        return Association.isAssignableFrom(field.getType())
    }

    private Optional<Field> findField(Class<?> beanClass, String propertyName) {
        Class<?> currentClass = beanClass
        while (currentClass != null) {
            try {
                return Optional.of(currentClass.getDeclaredField(propertyName))
            } catch (NoSuchFieldException e) {
                currentClass = currentClass.superclass
            }
        }
        return Optional.empty()
    }
}
