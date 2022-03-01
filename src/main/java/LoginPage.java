import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;

public class LoginPage {

    public  static final String URL_LOGIN = "https://stellarburgers.nomoreparties.site/login";

    //Локаторы полей
    @FindBy(how = How.XPATH, using = "//label[text()='Email']/../input")
    private SelenideElement fieldEmailLogin;
    @FindBy(how = How.XPATH, using = "//label[text()='Пароль']/../input")
    private SelenideElement fieldPasswordLogin;

    //Локатор текста
    @FindBy(how = How.XPATH, using = "//h2[text()='Вход']")
    private SelenideElement textLogin;

    //Локатор кнопки
    @FindBy(how = How.XPATH, using = "//button[text()='Войти']")
    private SelenideElement buttonLogin;

    @Step("Set user data in login page")
    public void setUserData(String email, String password){
        fieldEmailLogin.shouldBe(visible);
        fieldEmailLogin.setValue(email);
        fieldPasswordLogin.setValue(password);;
    }

    @Step("Click button login in login page")
    public void clickButtonLogin() {
        buttonLogin.shouldBe(visible);
        buttonLogin.click();
    }

    @Step("Get text login in login page")
    public String getTextLogin() {
        textLogin.shouldBe(visible);
        return textLogin.getText();
    }
}

