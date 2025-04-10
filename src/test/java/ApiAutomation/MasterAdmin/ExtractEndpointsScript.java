import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class
ExtractEndpointsScript {
    public static void main(String[] args) {
        String filePath = "/Users/a35549/Downloads/fourdoor-catalog-service.postman_collection.json"; // Update with your actual file path
        List<String> endpoints = extractEndpoints(filePath);

        // Print all extracted endpoints
        for (String endpoint : endpoints) {
            System.out.println(endpoint);
        }
    }

    public static List<String> extractEndpoints(String filePath) {
        List<String> endpoints = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(filePath)) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONArray items = (JSONArray) jsonObject.get("item");

            for (Object obj : items) {
                JSONObject item = (JSONObject) obj;
                if (item.containsKey("request")) {
                    JSONObject request = (JSONObject) item.get("request");
                    JSONObject url = (JSONObject) request.get("url");
                    String fullUrl = (url != null && url.containsKey("raw")) ? (String) url.get("raw") : "Unknown URL";

                    // Remove host ({{host}} or full URL)
                    String cleanedUrl = removeHost(fullUrl);

                    // Add only the cleaned URL wrapped in double quotes
                    endpoints.add("\"" + cleanedUrl + "\"");
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return endpoints;
    }

    private static String removeHost(String url) {
        // Remove {{host}} placeholder
        url = url.replace("{{host}}", "");

        // Find the start of the path in a full URL
        int schemeIndex = url.indexOf("://");
        if (schemeIndex != -1) {
            int pathStartIndex = url.indexOf("/", schemeIndex + 3);
            if (pathStartIndex != -1) {
                return url.substring(pathStartIndex); // Extract only the path and query parameters
            }
        }
        return url.startsWith("/") ? url : "/" + url; // Ensure it starts with '/'
    }
}
