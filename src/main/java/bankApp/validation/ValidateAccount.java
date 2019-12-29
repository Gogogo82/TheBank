package bankApp.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AccountValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateAccount {

    // default logic (for example - starts with "LUV")
    public String value() default "\\d{20}";

    // default error message
    public String message() default "must contain 20 digits";

    // default groups
    public Class<?>[] groups() default{};

    // default payload
    public Class<? extends Payload>[] payload() default {};
}
