package ApiAutomation.validation;

import java.util.*;

public class JsonPathUtils {

    // This method will split the keyPath and navigate into nested maps/lists
    public static List<Object> extractValues(Map<String, Object> json, String keyPath) {
        String[] keys = keyPath.split("\\.");
        List<Object> currentValues = new ArrayList<>();
        currentValues.add(json);

        for (String key : keys) {
            List<Object> nextValues = new ArrayList<>();

            for (Object current : currentValues) {
                if (current instanceof Map) {
                    Object next = ((Map<?, ?>) current).get(key);
                    if (next instanceof List) {
                        nextValues.addAll((List<?>) next);
                    } else if (next != null) {
                        nextValues.add(next);
                    }
                }
            }

            currentValues = nextValues;
        }

        return currentValues;
    }
}
