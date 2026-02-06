package at.martinahrer.bootolithic.catalog;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Size;

import java.lang.annotation.*;

@Size(min = SizeIdentifierConstraint.MIN_LENGTH, max = SizeIdentifierConstraint.MAX_LENGTH)
@Constraint(validatedBy = {})
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface SizeIdentifierConstraint {
    int MIN_LENGTH = 1;
    int MAX_LENGTH = 32;

    String message() default "Invalid ric rate identifier";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
