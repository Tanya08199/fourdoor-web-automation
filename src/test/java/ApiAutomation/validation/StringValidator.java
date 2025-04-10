package ApiAutomation.validation;

public class StringValidator implements FieldValidator {
    public boolean validate(Object value) {
        return value != null && value instanceof String && !((String) value).trim().isEmpty();
    }
}