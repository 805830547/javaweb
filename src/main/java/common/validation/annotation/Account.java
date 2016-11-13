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
@Constraint(validatedBy = Account.AccountValidator.class)
@Documented
public @interface Account {
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /** default message */
    String message() default "illegal";

    /**
     * Validation for this annotation
     */
    class AccountValidator implements ConstraintValidator<Account, String> {
        /** Pattern object for Number */
        private static final Pattern ACCOUNT_PATTERN = Pattern.compile("^(ACC_[0-9]{6}|Org_UC|Org_CAP_Total)$");

        /**
         * validation logic
         */
        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if (value == null) {
                return true;
            }
            return ACCOUNT_PATTERN.matcher(value).find();
        }

        @Override
        public void initialize(Account constraintAnnotation) {
        }
    }
}
