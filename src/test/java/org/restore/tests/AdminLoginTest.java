package org.restore.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.openqa.selenium.WebDriver;
import org.restore.datamodels.LoginTestDataModel;
import org.restore.pages.adminpage.AdminPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class AdminLoginTest extends BaseTest {

    private WebDriver myPersonalDriver;
    AdminPage adminPage;

    @BeforeClass
    public void beforeClass() {
        myPersonalDriver = getMyPersonalDriver();
        adminPage = new AdminPage(myPersonalDriver);
        adminPage.open();
    }

    @Test(dataProvider = "getData")
    public void loginTest(LoginTestDataModel testData) {
        String login = testData.getAdminLogin();
        String password = testData.getAdminPassword();
        boolean testPass = testData.getTestPass();
        if (adminPage.isAdminLoginFormOpen())
            adminPage.login(login, password);
        else {
            adminPage.logout();
            adminPage.login(login, password);
        }


        if (!testPass)
            /*Did login fail? It has to be...
              Add more negative test data (later)
             */
            assertTrue(adminPage.isAdminLoginFormOpen());
        else {
            //Did login succeed? It has to be...
            assertTrue(adminPage.isAdminPageOpen());
        }
    }

    @AfterClass
    public void afterClass() {
        myPersonalDriver.quit();
    }

    @DataProvider
    private Object[] getData() {
        return readLoginTestDataJson("LoginTestData.json").toArray();
    }

    private List<LoginTestDataModel> readLoginTestDataJson(String jsonArrayFile) {
        Type dataType = new TypeToken<List<LoginTestDataModel>>() {
        }.getType();
        Gson gson = new Gson();
        JsonReader reader = null;
        try {
            InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(jsonArrayFile);
            reader = new JsonReader(new InputStreamReader(resourceAsStream));
        } catch (NullPointerException ioe) {
            System.err.println("Unable to read json file:" + jsonArrayFile);
        }
        return gson.fromJson(reader, dataType);
    }
}
