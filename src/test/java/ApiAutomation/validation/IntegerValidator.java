package ApiAutomation.validation;

public class IntegerValidator implements FieldValidator {
    @Override
    public boolean validate(Object value) {
        return value != null && value instanceof Integer;
    }
}
