import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;

public class AccountProfilePage {

    //Локатор пооля
    @FindBy(how = How.XPATH, using = "//a[text()='Профиль']")
    private SelenideElement fieldProfile;

    //Локатор кнопки
    @FindBy(how = How.XPATH, using = "//button[text()='Выход']")
    private SelenideElement logoutButton;

    @Step("Get text profile account profile page")
    public String getTextProfile() {
        fieldProfile.shouldBe(visible);
        return fieldProfile.getText();
    }

    @Step("Click button logout account profile page")
    public void clickButtonLogout() {
        logoutButton.shouldBe(visible);
        logoutButton.click();
    }
}
