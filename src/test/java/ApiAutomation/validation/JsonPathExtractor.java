package ApiAutomation.validation;

import java.util.*;

public class JsonPathExtractor {

    @SuppressWarnings("unchecked")
    public static Object extract(Map<String, Object> json, String path) {
        String[] parts = path.split("\\.");

        Object current = json;
        for (String part : parts) {
            if (current instanceof Map) {
                current = ((Map<String, Object>) current).get(part);
            } else if (current instanceof List) {
                List<Object> list = (List<Object>) current;
                List<Object> results = new ArrayList<>();
                for (Object item : list) {
                    Object result = extract((Map<String, Object>) item, String.join(".", Arrays.copyOfRange(parts, Arrays.asList(parts).indexOf(part), parts.length)));
                    results.add(result);
                }
                return results;
            } else {
                return null;
            }
        }

        return current;
    }
}
