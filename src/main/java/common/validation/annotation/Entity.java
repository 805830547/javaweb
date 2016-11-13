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
@Constraint(validatedBy = Entity.AccountValidator.class)
@Documented
public @interface Entity {
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /** default message */
    String message() default "illegal";

    /**
     * Validation for this annotation
     */
    class AccountValidator implements ConstraintValidator<Entity, String> {
        /** Pattern object for Number */
        private static final Pattern ENTITY_PATTERN = Pattern.compile("^(PRJ_[0-9]{10}|RA[0-9]{4}_Dummy)$");

        /**
         * validation logic
         */
        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if (value == null) {
                return true;
            }
            return ENTITY_PATTERN.matcher(value).find();
        }

        @Override
        public void initialize(Entity constraintAnnotation) {
        }
    }
}
