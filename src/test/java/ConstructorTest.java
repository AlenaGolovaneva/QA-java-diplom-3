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
public class ConstructorTest {
    public UserOperations userOperations;
    public Map<String, String> dataRegistration;

    public ConstructorTest(boolean isYandex) {
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

        LoginPage loginPage = open(LoginPage.URL_LOGIN, LoginPage.class);
        loginPage.setUserData(dataRegistration.get("email"), dataRegistration.get("password"));
        loginPage.clickButtonLogin();
    }

    @After
    public void ternDown(){
        userOperations.delete();
        closeWebDriver();
    }

    @Test
    @DisplayName("constructor rolls")
    @Description("Check constructor rolls on main page")
    public void constructorRolls() {
        MainPage mainPage = page(MainPage.class);
        mainPage.clickButtonToppings();
        mainPage.clickButtonRolls();

        assertEquals("Constructor rolls is display","Булки", mainPage.getTextRolls());
    }

    @Test
    @DisplayName("constructor sauces")
    @Description("Check constructor sauces on main page")
    public void constructorSauces() {
        MainPage mainPage = page(MainPage.class);
        mainPage.clickButtonToppings();
        mainPage.clickButtonSauces();
        assertEquals("Constructor souse is display","Соусы", mainPage.getTextSauces());
    }

    @Test
    @DisplayName("constructor toppings")
    @Description("Check constructor toppings on main page")
    public void constructorTopping() {
        MainPage mainPage = page(MainPage.class);
        mainPage.clickButtonToppings();
        assertEquals("Constructor topping is display","Начинки", mainPage.getTextToppings());
    }
}
