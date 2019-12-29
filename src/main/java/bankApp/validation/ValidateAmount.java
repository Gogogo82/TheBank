package bankApp.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AmountValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateAmount {

    // default logic (for example - starts with "LUV")
    public String value() default "\\d+\\.\\d\\d$";

    // default error message
    public String message() default "Must be greater than zero, with precision after the decimal point: 2, delimiter: \".\"";

    // default groups
    public Class<?>[] groups() default{};

    // default payload
    public Class<? extends Payload>[] payload() default {};
}
