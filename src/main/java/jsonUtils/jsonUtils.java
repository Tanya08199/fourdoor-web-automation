package jsonUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class jsonUtils {

  private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Map<String,String> getJsonDataAsMap(String jsonFileName) throws IOException {
        String completeJsonPath = System.getProperty("user.dir") + "/src/test/Resources/" + jsonFileName;

        return objectMapper.readValue(new File(completeJsonPath), new TypeReference<Map<String, String>>() {
        });

    }
}
