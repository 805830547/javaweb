package common;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;

/**
 * Wrapper class of Hibernate Validator<br>
 * This class privides validation methods<br>
 * <br>
 * To use these methods, add annotation to targer models<br>
 * <br>
 * If you want to change violation messages, put 'ValidationMessages.properties' on your classpath
 *
 * @author ryo.yoneda
 */
public class AnnotationValidator {
    /** validator instantce => this object's methods are 'thread safe' */
    private static Validator validator;

    /**
     * static constructor
     */
    static {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    /**
     * Validate model
     *
     * @param object
     * @return if violation exists => 'Set' of 'ConstraintViolation<T>'<br>
     *         if violation doesn't exist => empty 'Set'
     */
    public static <T> Set<ConstraintViolation<T>> getViolations(T object) {
        if (object == null) {
            return new HashSet<ConstraintViolation<T>>();
        }
        return validator.validate(object, Default.class);
    }

    /**
     * Validate model and return violation message
     *
     * @param object
     * @return if violation exists => list of validation error message<br>
     *         if violation doesn't exist => empty List
     */
    public static List<String> getViolationMessageList(Object object) {
        List<String> messageList = new ArrayList<String>();
        if (object == null) {
            return messageList;
        }
        Set<ConstraintViolation<Object>> violations = validator.validate(object, Default.class);
        if (violations.isEmpty()) { return messageList; }
        StringBuilder stringBuilder = new StringBuilder();
        for (ConstraintViolation<Object> violation: violations) {
            stringBuilder.append(violation.getPropertyPath());
            stringBuilder.append(" ");
            stringBuilder.append(violation.getMessage());
            messageList.add(stringBuilder.toString());
            stringBuilder.setLength(0);
        }
        return messageList;
    }

    /**
     * Validate model and return violation message<br>
     * all violation messages will be combined as one String
     *
     * @param object
     * @return if violation exists => validation error message<br>
     *         if violation doesn't exist => null
     */
    public static String getViolationMessage(Object... objectArray) {
        if (objectArray == null) {
            return null;
        }
        List<String> violationMessages = new ArrayList<String>();
        for (Object object: objectArray) {
            violationMessages.addAll(getViolationMessageList(object));
        }
        if (violationMessages.isEmpty()) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String message: violationMessages) {
            stringBuilder.append(message).append(" ");
        }
        return stringBuilder.toString();
    }

    /**
     * Create Error String
     *
     * @param validationErrorMessage
     * @return
     */
    public static String getErrorDataString(List<String> lineData, String validationErrorMessage) {
        StringBuffer errorStringString = new StringBuffer();
        for (String string : lineData) {
            errorStringString.append(string).append(",");
        }
        errorStringString.append(validationErrorMessage);
        return errorStringString.toString();
    }

}
