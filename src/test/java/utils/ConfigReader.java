package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties = new Properties();

    static {
        try {
            properties.load(new FileInputStream("src/test/Resources/Fourdoor/QA/config.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Cannot load config file: " + e.getMessage());
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getBaseURI_Catalog() {
        return getProperty("baseURI_Catalog");
    }

    public static String getBaseURI_MasterAdmin() {
        return getProperty("baseURI_MasterAdmin");
    }
}
