import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;

public class RestorePasswordPage {
    public  static final String URL_RESTORE_PASSWORD = "https://stellarburgers.nomoreparties.site/forgot-password";

    //Локатор кнопки
    @FindBy(how = How.XPATH, using = "//a[text()='Войти']")
    private SelenideElement buttonLoginRestorePass;

    @Step
    public void clickButtonLoginRestorePass() {
        buttonLoginRestorePass.shouldBe(visible);
        buttonLoginRestorePass.click();
    }
}
