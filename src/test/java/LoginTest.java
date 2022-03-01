import com.UserOperations;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class LoginTest {
    public UserOperations userOperations;
    public Map<String, String> dataRegistration;

    public LoginTest(boolean isYandex) {
        if (isYandex) {
            System.setProperty("webdriver.chrome.driver", "src/resources/yandexdriver");
        }
    }

    @Parameterized.Parameters
    public static Object[] getParams() {
        return new Object[]{false, true};
    }

    @Before
    public void setUp(){
        userOperations = new UserOperations();
        dataRegistration = userOperations.register();
    }

    @After
    public void ternDown(){
        userOperations.delete();
        closeWebDriver();
    }

    @Test
    @DisplayName("login user main page")
    @Description("Check login user from main page")
    public void loginUserUserDataUserDisplayButton() {
        MainPage mainPage = open(MainPage.URL_MAIN, MainPage.class);
        mainPage.clickButtonLoginToAccount();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.setUserData(dataRegistration.get("email"), dataRegistration.get("password"));
        loginPage.clickButtonLogin();

        assertEquals("User is login from main page display button order, button order is displayed","Оформить заказ", mainPage.getTextButtonCreateOrder());
    }

    @Test
    @DisplayName("login user header personal area")
    @Description("Check login user from header personal area")
    public void loginUserPersonalAreDisplayButton(){
        MainPage mainPage = open(MainPage.URL_MAIN, MainPage.class);

        HeaderPage headerPage = page(HeaderPage.class);
        headerPage.clickButtonPersonalAreaHeader();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.setUserData(dataRegistration.get("email"), dataRegistration.get("password"));
        loginPage.clickButtonLogin();

        assertEquals("User is login from personal area page, button order is displayed","Оформить заказ", mainPage.getTextButtonCreateOrder());
    }

    @Test
    @DisplayName("login user from registration")
    @Description("Check login user from registration page")
    public void loginUserFormRegistrationButton(){
        RegistrationPage registrationPage = open(RegistrationPage.URL_REGISTRATION, RegistrationPage.class);
        registrationPage.clickButtonLogin();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.setUserData(dataRegistration.get("email"), dataRegistration.get("password"));
        loginPage.clickButtonLogin();

        MainPage mainPage = page(MainPage.class);
        assertEquals("User is login from registration page, button order is displayed","Оформить заказ", mainPage.getTextButtonCreateOrder());
    }

    @Test
    @DisplayName("login user from restore password page")
    @Description("Check login user from restore password page")
    public void loginUserFormRestorePassword(){
        RestorePasswordPage restorePasswordPage = open(RestorePasswordPage.URL_RESTORE_PASSWORD, RestorePasswordPage.class);
        restorePasswordPage.clickButtonLoginRestorePass();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.setUserData(dataRegistration.get("email"), dataRegistration.get("password"));
        loginPage.clickButtonLogin();

        MainPage mainPage = page(MainPage.class);
        assertEquals("User is login from restore password page, button order is displayed","Оформить заказ", mainPage.getTextButtonCreateOrder());
    }
}
