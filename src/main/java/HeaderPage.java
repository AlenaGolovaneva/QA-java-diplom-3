import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;

public class HeaderPage {

    //Локаторы кнопок
    @FindBy(how = How.XPATH, using = "//p[text()='Личный Кабинет']")
    private SelenideElement buttonPersonalArea;
    @FindBy(how = How.CLASS_NAME, using = "AppHeader_header__logo__2D0X2")
    private SelenideElement buttonLogoType;
    @FindBy(how = How.XPATH, using = "//p[text()='Конструктор']")
    private SelenideElement buttonConstructor;

    @Step("Click button personal area header")
    public void clickButtonPersonalAreaHeader() {
        buttonPersonalArea.shouldBe(visible);
        buttonPersonalArea.click();
    }

    @Step("Click button logo type in header")
    public void clickButtonLogoType() {
        buttonLogoType.shouldBe(visible);
        buttonLogoType.click();
    }

    @Step("Click button constructor in header")
    public void clickButtonConstructor() {
        buttonConstructor.shouldBe(visible);
        buttonConstructor.click();
    }
}
