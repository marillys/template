package utils;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {
    Properties properties = new Properties();
    String arquivoConfiguracao = "config.properties";

    public String getProp(String key) {
        try {
            if (System.getProperty("env") == null) {
                properties.load(getClass().getClassLoader().getResourceAsStream(arquivoConfiguracao));
            } else {
                properties.load(getClass().getClassLoader().getResourceAsStream(System.getProperty("env")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }
}
