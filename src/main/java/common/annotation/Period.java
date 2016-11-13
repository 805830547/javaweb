package common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

@Target({ ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = Period.SearchTypeValidator.class)
@Documented
public @interface Period {
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    /** default message */
    String message() default "illegal";

    /**
     * Validation for this annotation
     */
    class SearchTypeValidator implements ConstraintValidator<Period, String> {
        /** enum of SearchType */
        public enum PeriodEnum {
            /** January */
            JANUARY("Jan"),
            FEBRUARY("Feb"),
            MARCH("Mar"),
            APRIL("Apr"),
            MAY("May"),
            JUNE("Jun"),
            JULY("Jul"),
            AUGUST("Aug"),
            SEPTEMBER("Sep"),
            OCTOBER("Oct"),
            NOVEMBER("Nov"),
            DECEMBER("Dec");

            private String month;
            private PeriodEnum(String month) { this.month = month;}
            public String getMonth() { return month;}
        }

        /**
         * validation logic
         */
        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if (value == null) {
                return true;
            }
            for (PeriodEnum period : PeriodEnum.values()) {
                if (value.equals(period.getMonth())) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public void initialize(Period constraintAnnotation) {
        }
    }
}