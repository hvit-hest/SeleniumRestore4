package org.restore.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.restore.datamodels.MenuItemModel;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Properties;

public class Utils {

    public static final String testConfigPropertiesPath = "testconfig.properties";

    private static Properties loadPropertiesFile(String filePath) {
        Properties prop = new Properties();
        try (InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath)) {
            prop.load(resourceAsStream);
        } catch (IOException ioe) {
            System.err.println("Unable to load properties file:" + filePath);
        }
        return prop;
    }

    public static Properties getTestProperties() {
        return loadPropertiesFile(testConfigPropertiesPath);
    }

    public List<MenuItemModel> readMenuFromJson(String jsonArrayFile) {
        Type MenuType = new TypeToken<List<MenuItemModel>>() {
        }.getType();
        Gson gson = new Gson();
        JsonReader reader = null;
        try {
            InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(jsonArrayFile);
            reader = new JsonReader(new InputStreamReader(resourceAsStream));
        } catch (NullPointerException ioe) {
            System.err.println("Unable to read json file:" + jsonArrayFile);
        }
        return gson.fromJson(reader, MenuType);
    }
}

    /*public void printTestingProperties() {
        Properties prop = loadPropertiesFile(testConfigPropertiesPath);
        prop.forEach((k, v) -> System.out.println(k + " " + v));
    }

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
    }*/

