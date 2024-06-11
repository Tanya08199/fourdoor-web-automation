package jsonUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class jsonUtils {

  private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Map<String,Object> getJsonDataAsMap(String jsonFileName) throws IOException {
        String completeJsonPath = System.getProperty("user.dir") + "/src/test/Resources/" + jsonFileName;

        return objectMapper.readValue(new File(completeJsonPath), new TypeReference<Map<String, Object>>() {
        });
    }

    public static List<String> getSlugs(String jsonFileName) throws IOException {
        Map<String, Object> data = getJsonDataAsMap(jsonFileName);
        return (List<String>) data.get("slugs");
    }
}
