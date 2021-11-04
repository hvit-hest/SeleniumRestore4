package org.restore.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.restore.datamodels.LoginTestDataModel;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Properties;

public class Utils {

    public static final String testConfigPropertiesPath = "testconfig.properties";

    private Properties loadPropertiesFile(String filePath) {
        Properties prop = new Properties();
        try (InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(filePath)) {
            prop.load(resourceAsStream);
        } catch (IOException ioe) {
            System.err.println("Unable to load properties file:" + filePath);
        }
        return prop;
    }

    public Properties getTestProperties() {
        return loadPropertiesFile(testConfigPropertiesPath);
    }

    public void printTestingProperties() {
        Properties prop = loadPropertiesFile(testConfigPropertiesPath);

    }

    public void printTestingProperties1() {
        Properties prop = loadPropertiesFile("testconfig.properties");
        prop.forEach((k, v) -> System.out.println(k + " " + v));
    }
}
