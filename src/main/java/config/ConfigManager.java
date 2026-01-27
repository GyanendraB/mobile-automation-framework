package config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static Properties props = new Properties();

    static {
        try (InputStream input = ConfigManager.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            props.load(input);

        } catch (Exception e) {
            throw new RuntimeException("Unable to load config.properties");
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}
