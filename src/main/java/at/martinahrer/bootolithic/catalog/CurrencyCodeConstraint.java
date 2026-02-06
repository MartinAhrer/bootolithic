package at.martinahrer.bootolithic.catalog;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Size;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Size(min = CurrencyCodeConstraint.MIN_LENGTH, max = CurrencyCodeConstraint.MAX_LENGTH)
@Constraint(validatedBy = {})
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrencyCodeConstraint {
    int MIN_LENGTH = 1;
    int MAX_LENGTH = 32;

    String message() default "Invalid currency code ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
