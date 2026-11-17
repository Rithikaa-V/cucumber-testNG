package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Reads values from src/test/resources/config.properties.
 *
 * A value given on the command line wins over the file, e.g.
 *   mvn test -Dbrowser=firefox -Dheadless=true
 */
public class ConfigReader {

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IllegalStateException("config.properties was not found in src/test/resources");
            }
            PROPERTIES.load(input);
        } catch (IOException e) {
            throw new IllegalStateException("Could not read config.properties", e);
        }
    }

    private ConfigReader() {
        // Only static methods - nobody needs to create an object of this class
    }

    public static String get(String key) {
        String fromCommandLine = System.getProperty(key);
        if (fromCommandLine != null) {
            return fromCommandLine.trim();
        }
        String fromFile = PROPERTIES.getProperty(key);
        if (fromFile == null) {
            throw new IllegalStateException("Key '" + key + "' is missing in config.properties");
        }
        return fromFile.trim();
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }
}
