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
public class LogOutTest {
    public UserOperations userOperations;
    public LoginPage loginPage;
    public Map<String, String> dataRegistration;

    public LogOutTest(boolean isYandex) {
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

        loginPage = open(LoginPage.URL_LOGIN,LoginPage.class);
        loginPage.setUserData(dataRegistration.get("email"), dataRegistration.get("password"));
        loginPage.clickButtonLogin();
    }

    @After
    public void ternDown(){
        userOperations.delete();
        closeWebDriver();
    }

    @Test
    @DisplayName("logout")
    @Description("Check logout from personal area")
    public void logoutFomPersonalArea() {
        HeaderPage headerPage = page(HeaderPage.class);
        headerPage.clickButtonPersonalAreaHeader();

        AccountProfilePage accountProfilePage = page(AccountProfilePage.class);
        accountProfilePage.clickButtonLogout();
        assertEquals("check login button display","Вход", loginPage.getTextLogin());
    }
}
