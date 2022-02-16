package org.restore.tests;

import org.openqa.selenium.WebDriver;
import org.restore.utils.WebDriverSelection;

import java.util.concurrent.TimeUnit;

import static org.restore.utils.Utils.getTestProperties;

public class BaseTest {

    private static final String userPassword = getTestProperties().getProperty("userPassword");
    private final static String userEmail = getTestProperties().getProperty("userEmail");
    private static final WebDriver myPersonalDriver = new WebDriverSelection().getDriverFromProperties();
    private static final String adminName = getTestProperties().getProperty("adminName");
    private static final String adminPassword = getTestProperties().getProperty("adminPassword");
    private static final int implicitWait = Integer.parseInt(getTestProperties().getProperty("implicitWait"));

    public BaseTest() {
        myPersonalDriver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
    }

    public static String getUserPassword() {
        return userPassword;
    }

    public static String getUserEmail() {
        return userEmail;
    }

    public static WebDriver getMyPersonalDriver() {
        return myPersonalDriver;
    }

    public static String getAdminName() {
        return adminName;
    }

    public static String getAdminPassword() {
        return adminPassword;
    }

    public static int getImplicitWait() {
        return implicitWait;
    }
}
