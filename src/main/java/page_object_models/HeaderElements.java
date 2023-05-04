package page_object_models;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HeaderElements {

    private final WebDriver driver;
    public HeaderElements(WebDriver driver) {
        this.driver = driver;
    }

    // Локаторы для элементов хедера.
    private static final By constructorButton = By.xpath("//div/header/nav/ul/li/a[@class='AppHeader_header__link__3D_hX AppHeader_header__link_active__1IkJo']");
    private static final By constructorButton2 = By.xpath("//div//header/nav/ul/li[1]/a/p");
    private static final By lentOfOrdersButton = By.xpath("//div/header/nav/ul/li/a[@class='AppHeader_header__link__3D_hX']");
    private static final By logo = By.xpath("//div/header//div[@class='AppHeader_header__logo__2D0X2']");
    private static final By personalCabinetButton = By.xpath("//div/header/nav/a");


    // Методы взаимодействия с локаторами.
    @Step("Ожидание загрузки страницы: проверили видимость элемента в хедере.")
    public void waitForLoadHomePage() {
        new WebDriverWait(driver, 8)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[text() = 'Конструктор']")));
    }

    @Step("Клик на кнопку Конструктор.")
    public void constructorButtonClick() {
        driver.findElement(constructorButton).click();
    }

    @Step("Клик на кнопку Лента Заказов.")
    public void lentOfOrdersClick() {
        driver.findElement(lentOfOrdersButton).click();
    }

    @Step("Клик на кнопку Лого")
    public void logoButtonClick() {
        driver.findElement(logo).click();
    }

    @Step("Клик на кнопку Личный Кабинет")
    public void personalCabinetButtonClick() {
        driver.findElement(personalCabinetButton).click();
    }
}