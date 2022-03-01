import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;

public class MainPage {
    public  static final String URL_MAIN = "https://stellarburgers.nomoreparties.site/";

    //Локаторы текстов
    @FindBy(how = How.XPATH, using = "//h2[text()='Начинки']")
    private SelenideElement textToppings;
    @FindBy(how = How.XPATH, using = "//h2[text()='Булки']")
    private SelenideElement textRolls;
    @FindBy(how = How.XPATH, using = "//h2[text()='Соусы']")
    private SelenideElement textSauces;

    //Локаторы кнопок
    @FindBy(how = How.XPATH, using = "//button[text()='Войти в аккаунт']")
    private SelenideElement buttonLoginToAccount;
    @FindBy(how = How.XPATH, using = "//button[text()='Оформить заказ']")
    private SelenideElement buttonCreateOrder;
    @FindBy(how = How.XPATH, using = "//span[text()='Начинки']")
    private SelenideElement buttonToppings;
    @FindBy(how = How.XPATH, using = "//span[text()='Булки']")
    private SelenideElement buttonRolls;
    @FindBy(how = How.XPATH, using = "//span[text()='Соусы']")
    private SelenideElement buttonSauces;

    @Step("Click button login to account in main page")
    public void clickButtonLoginToAccount() {
        buttonLoginToAccount.shouldBe(visible);
        buttonLoginToAccount.click();
    }

    @Step("Click button toppings in main page")
    public void clickButtonToppings() {
        buttonToppings.shouldBe(visible);
        buttonToppings.click();
    }

    @Step("Click button rolls in main page")
    public void clickButtonRolls() {
        buttonRolls.shouldBe(visible);
        buttonRolls.click();
    }

    @Step("Click button sauces in main page")
    public void clickButtonSauces() {
        buttonSauces.shouldBe(visible);
        buttonSauces.click();
    }

    @Step("Get text button create order in main page")
    public String getTextButtonCreateOrder() {
        buttonCreateOrder.shouldBe(visible);
        return buttonCreateOrder.getText();
    }

    @Step("Get text toppings in main page")
    public String getTextToppings() {
        textToppings.shouldBe(visible);
        return textToppings.getText();
    }

    @Step("Get text rolls in main page")
    public String getTextRolls() {
        textRolls.shouldBe(visible);
        return textRolls.getText();
    }

    @Step("Get text sauces in main page")
    public String getTextSauces() {
        textSauces.shouldBe(visible);
        return textSauces.getText();
    }
}
