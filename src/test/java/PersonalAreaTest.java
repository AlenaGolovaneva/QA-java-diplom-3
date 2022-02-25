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
public class PersonalAreaTest {
    public UserOperations userOperations;
    public LoginPage loginPage;
    public Map<String, String> dataRegistration;

    public PersonalAreaTest(boolean isYandex) {
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

        loginPage = open(LoginPage.URL_LOGIN, LoginPage.class);
        loginPage.setUserData(dataRegistration.get("email"), dataRegistration.get("password"));
        loginPage.clickButtonLogin();
    }

    @After
    public void ternDown(){
        userOperations.delete();
        closeWebDriver();
    }

    @Test
    @DisplayName("open personal area")
    @Description("Check open personal area from main page")
    public void openPersonalArea() {
        HeaderPage headerPage = page(HeaderPage.class);
        headerPage.clickButtonPersonalAreaHeader();

        AccountProfilePage accountProfilePage = page(AccountProfilePage.class);
        assertEquals("Профиль", accountProfilePage.getTextProfile());
    }

    @Test
    @DisplayName("open personal area back to main from logotype")
    @Description("Check open personal area click logotype header back to main page")
    public void openPersonalAreaClickLogoTypeFromHeader() {
        HeaderPage headerPage = page(HeaderPage.class);
        headerPage.clickButtonPersonalAreaHeader();
        headerPage.clickButtonLogoType();

        MainPage mainPage = page(MainPage.class);
        assertEquals("Оформить заказ", mainPage.getTextButtonCreateOrder());
    }

    @Test
    @DisplayName("open personal area back to main from constructor")
    @Description("Check open personal area click constructor header back to main page")
    public void openPersonalAreaClickConstructorFromHeader() {
        HeaderPage headerPage = page(HeaderPage.class);
        headerPage.clickButtonPersonalAreaHeader();
        headerPage.clickButtonConstructor();

        MainPage mainPage = page(MainPage.class);
        assertEquals("Оформить заказ", mainPage.getTextButtonCreateOrder());
    }
}
