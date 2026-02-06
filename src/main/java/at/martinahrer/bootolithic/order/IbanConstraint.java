package at.martinahrer.bootolithic.order;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Size;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Size(min = IbanConstraint.MIN_LENGTH, max = IbanConstraint.MAX_LENGTH)
@Constraint(validatedBy = {})
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface IbanConstraint {
    int MIN_LENGTH = 1;
    int MAX_LENGTH = 34;

    String message() default "Invalid currency code ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    // TODO implement custom validator: https://en.wikipedia.org/wiki/International_Bank_Account_Number
}
