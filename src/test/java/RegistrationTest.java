import com.LoginGetToken;
import com.model.User;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverConditions.currentFrameUrlContaining;

@RunWith(Parameterized.class)
public class RegistrationTest {

    public RegistrationTest(boolean isYandex) {
        if (isYandex) {
            System.setProperty("webdriver.chrome.driver", "src/resources/yandexdriver");
        }
    }

    @Parameterized.Parameters
    public static Object[] getParams() {
        return new Object[]{false, true};
    }

    @After
    public void ternDown(){
        closeWebDriver();

    }

    @Test
    @DisplayName("registration user")
    @Description("Check user is registered")
    public void registrationUserUserDataOpenLoginPage() {
        RegistrationPage registrationPage = open(RegistrationPage.URL_REGISTRATION, RegistrationPage.class);
        User user = new User();
        user.setName(RandomStringUtils.randomAlphabetic(10))
                .setEmail(RandomStringUtils.randomAlphabetic(7) + "@yandex.ru")
                .setPassword(RandomStringUtils.randomAlphabetic(10));
        registrationPage.setUser(user);
        registrationPage.clickButtonRegistration();

        currentFrameUrlContaining("https://stellarburgers.nomoreparties.site/login");

        LoginGetToken loginGetToken = new LoginGetToken();
        loginGetToken.login(user.getEmail(), user.getPassword());
        loginGetToken.delete();
    }


    @Test
    @DisplayName("registration user password isn't correct  ")
    @Description("Check registration user password is not correct")
    public void registrationUserPasswordIsNorCorrectMessageError() {
        RegistrationPage registrationPage = open(RegistrationPage.URL_REGISTRATION, RegistrationPage.class);
        User user = new User();
        user.setName(RandomStringUtils.randomAlphabetic(10))
                .setEmail(RandomStringUtils.randomAlphabetic(7)+"@yandex.ru")
                .setPassword(RandomStringUtils.randomAlphabetic(4));
        registrationPage.setUser(user);
        registrationPage.clickButtonRegistration();

        assertEquals("an error message is displayed if the password is not correct","Некорректный пароль", registrationPage.getTextErrorPassword());
    }
}