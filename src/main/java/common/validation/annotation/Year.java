package common.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.regex.Pattern;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

@Target({ ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = Year.AccountValidator.class)
@Documented
public @interface Year {
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /** default message */
    String message() default "illegal";

    /**
     * Validation for this annotation
     */
    class AccountValidator implements ConstraintValidator<Year, String> {
        /** Pattern object for Number */
        private static final Pattern YEAR_PATTERN = Pattern.compile("^FY[0-9]{2}$");

        /**
         * validation logic
         */
        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if (value == null) {
                return true;
            }
            return YEAR_PATTERN.matcher(value).find();
        }

        @Override
        public void initialize(Year constraintAnnotation) {
        }
    }
}
