package constructor;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page_object_models.Constructor;

import static credentials.Constants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ConstructorTests {

    private Constructor constructor;
    private WebDriver driver;


    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    @Test
    @DisplayName("Тест перехода к разделу соусов.")
    @Description("Проверка, что происходит успешный скролл в раздел сайта соусов.")
    public void shouldGoToSauces() {
        constructor = new Constructor(driver);
        constructor.goToSauces();
        constructor.waitForTheSauces();
        assertThat(true, equalTo(driver.findElement(By
                .xpath("//div[contains(span/text(),'Соусы')]")).isDisplayed()));
    }

    @Test
    @DisplayName("Тест перехода к разделу булок.")
    @Description("Проверка, что происходит успешный скролл в раздел сайта булок.")
    public void shouldGoToBuns() {
        constructor = new Constructor(driver);
        if (driver.findElement(By
                .xpath("//div[contains(span/text(),'Булки')]")).isDisplayed()) {
            assertThat(true, equalTo(driver.findElement(By
                    .xpath("//div[contains(span/text(),'Булки') and contains(@class,'current')]")).isDisplayed()));
        } else {
            constructor.goToBuns();
            constructor.waiteForTheBuns();
            assertThat(true, equalTo(driver.findElement(By
                    .xpath("//div[contains(span/text(),'Булки') and contains(@class,'current')]")).isDisplayed()));
        }
    }

    @Test
    @DisplayName("Тест перехода к разделу начинок.")
    @Description("Проверка, что происходит успешный скролл в раздел сайта начинок.")
    public void shouldGoToTheFillings() {
        constructor = new Constructor(driver);
        constructor.goToFillings();
        constructor.waiteForTheFillings();
        assertThat(true, equalTo(driver.findElement(By
                .xpath("//div[contains(span/text(),'Начинки')]")).isDisplayed()));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}