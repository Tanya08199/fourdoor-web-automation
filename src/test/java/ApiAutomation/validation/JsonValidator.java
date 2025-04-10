package ApiAutomation.validation;

import java.util.List;
import java.util.Map;

public class JsonValidator {
    private final Map<String, FieldValidator> validators;

    public JsonValidator(Map<String, FieldValidator> validators) {
        this.validators = validators;
    }

    public boolean validate(Map<String, Object> jsonResponse) {
        boolean allValid = true;

        for (Map.Entry<String, FieldValidator> entry : validators.entrySet()) {
            String keyPath = entry.getKey();
            FieldValidator validator = entry.getValue();

            List<Object> values = JsonPathUtils.extractValues(jsonResponse, keyPath);

            for (Object value : values) {
                if (!validator.validate(value)) {
                    System.err.println("❌ Validation failed for key: " + keyPath + " | Value: " + value);
                    allValid = false;
                }
            }
        }

        return allValid;
    }
}
