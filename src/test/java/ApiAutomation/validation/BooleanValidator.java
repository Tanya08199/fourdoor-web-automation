package ApiAutomation.validation;


public class BooleanValidator implements FieldValidator {
    @Override
    public boolean validate(Object value) {
        return value != null && value instanceof Boolean;
    }
}
