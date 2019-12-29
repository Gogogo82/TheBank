package bankApp.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AccountValidator implements ConstraintValidator<ValidateAccount, String> {

    private String pattern;

    @Override
    public void initialize(ValidateAccount constraintAnnotation) {
        pattern = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        if (s == null)
            return false;
        else
            return (s.length() == 20) && s.matches(pattern);
    }
}
