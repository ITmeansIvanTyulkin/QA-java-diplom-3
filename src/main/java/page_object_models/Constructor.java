package page_object_models;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Constructor {

    private final WebDriver driver;

    public Constructor(WebDriver driver) {
        this.driver = driver;
    }

    // Локаторы для элементов конструктора..
    private static final By buns = By.xpath("//div//main/section[1]/div[1]/div[1]/span");
    private static final By sauces = By.xpath("//div//main/section[1]/div[1]/div[2]/span");
    private static final By fillings = By.xpath("//div//main/section[1]/div[1]/div[3]/span");


    // Методы для работы с локаторами.
    @Step("Проверка перехода к разделу соусы.")
    public void goToSauces() {
        driver.findElement(sauces).click();
    }

    @Step("Проверка перехода к раздулу булок.")
    public void goToBuns() {
        driver.findElement(buns).click();
    }

    @Step("Проверка перехода к разделу начинок.")
    public void goToFillings() {
        driver.findElement(fillings).click();
    }

    // Ожидания.
    @Step("Ожидание загрузки страницы: проверили видимость элемента а странице.")
    public void waitForTheSauces() {
        new WebDriverWait(driver, 8)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[text() = 'Соусы']")));
    }

    @Step("Ожидание загрузки страницы: проверили видимость элемента а странице.")
    public void waiteForTheBuns() {
        new WebDriverWait(driver, 8)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[text() = 'Булки']")));
    }

    @Step("Ожидание загрузки страницы: проверили видимость элемента а странице.")
    public void waiteForTheFillings() {
        new WebDriverWait(driver, 8)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[text() = 'Начинки']")));
    }
}