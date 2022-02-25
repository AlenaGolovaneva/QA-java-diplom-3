import com.codeborne.selenide.SelenideElement;
import com.model.User;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;

public class RegistrationPage {

    public  static final String URL_REGISTRATION = "https://stellarburgers.nomoreparties.site/register";

    //Локаторы полей
    @FindBy(how = How.XPATH, using = "//label[text()='Имя']/../input")
    private SelenideElement fieldName;
    @FindBy(how = How.XPATH, using = "//label[text()='Email']/../input")
    private SelenideElement fieldEmail;
    @FindBy(how = How.XPATH, using = "//label[text()='Пароль']/../input")
    private SelenideElement fieldPassword;
    @FindBy(how = How.XPATH, using = "//p[text()='Некорректный пароль']/../p")
    private SelenideElement fieldErrorPassword;

    //Локаторы кнопок
    @FindBy(how = How.XPATH, using = "//button[text()='Зарегистрироваться']")
    private SelenideElement buttonRegistration;
    @FindBy(how = How.XPATH, using = "//a[text()='Войти']/../a")
    private SelenideElement buttonLogin;

    @Step
    public void setUser(User user){
        fieldName.shouldBe(visible);
        fieldName.setValue(user.getName());;
        fieldEmail.setValue(user.getEmail());
        fieldPassword.setValue(user.getPassword());;
    }

    @Step
    public void clickButtonRegistration() {
        buttonRegistration.shouldBe(visible);
        buttonRegistration.click();
    }

    @Step
    public void clickButtonLogin() {
        buttonLogin.shouldBe(visible);
        buttonLogin.click();
    }

    @Step
    public String getTextErrorPassword() {
        fieldErrorPassword.shouldBe(visible);
        return fieldErrorPassword.getText();
    }
}
