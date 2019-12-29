package bankApp.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AmountValidator implements ConstraintValidator<ValidateAmount, Double> {

    private String pattern;

    @Override
    public void initialize(ValidateAmount constraintAnnotation) {
        pattern = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Double d, ConstraintValidatorContext constraintValidatorContext) {

        boolean result = false;
//
//        if((s == null) || !s.matches(pattern))
//            return result;
//        else {
//            try {
//                Double.parseDouble(s);
//                result = true;
//            }
//            catch (Exception e) {
//                // ignored
//            }
//        }
        if (d == null)
            return false;

        result = (d * 100d) % 1 == 0;

        return result;
    }
}