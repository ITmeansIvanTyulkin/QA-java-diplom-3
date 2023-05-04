package headerTests;

import driverpackage.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page_object_models.HeaderElements;

import static credentials.Constants.*;
import static org.junit.Assert.assertEquals;

public class HeaderTests {
    private WebDriver driver;
    HeaderElements step;
    private HeaderElements headerElements;

    @Before
    public void setUp() {
        // Чтобы переключить браузер с хрома на яндекс, достаточно закомментировать нижеследующие строки и раскомментировать последнюю
        // плюс в специальном классе WebDriverFactory прописать путь к бинарному файлу на своём ноутбуке, так как сейчас прописан мой.
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
        headerElements = new HeaderElements(driver);
        headerElements.waitForLoadHomePage();
        //driver = WebDriverFactory.getDriver(BASE_URL);
    }

    @Test
    @DisplayName("Тест кнопки Конструктор.")
    @Description("Проверка, что по клику на кнопку Конструктор осуществляется переход в раздел конструктора бургера.")
    public void constButtonClick() {
        headerElements.constructorButtonClick();
        assertEquals(BASE_URL, driver.getCurrentUrl());
        String actualTitle = driver.getTitle();
        String expectedTitle = "Stellar Burgers";
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    @DisplayName("Тест кнопки Лента Заказов.")
    @Description("Проверка, что по клику на кнопку Лента Заказов осуществляется переход в раздел Ленты Заказов.")
    public void lentButtonClick() {
        headerElements.lentOfOrdersClick();
        assertEquals(LENT_OF_ORDERS, driver.getCurrentUrl());
        String actualTitle = driver.getTitle();
        String expectedTitle = "Stellar Burgers";
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    @DisplayName("Тест клика на Логотип.")
    @Description("Проверка, что по клику на Логотип осуществляется переход в главный раздел.")
    public void logoButtonClick() {
        headerElements.logoButtonClick();
        assertEquals(BASE_URL, driver.getCurrentUrl());
        String actualTitle = driver.getTitle();
        String expectedTitle = "Stellar Burgers";
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    @DisplayName("Тест клика на кнопку Личный Кабинет.")
    @Description("Проверка, что по клику на кнопку Личный Кабинет осуществляется переход на страницу регистрации/входа в личный кабинет.")
    public void personalCabButtonClick() {
        headerElements.personalCabinetButtonClick();
        assertEquals(LOGIN_USER_URL, driver.getCurrentUrl());
        String actualTitle = driver.getTitle();
        String expectedTitle = "Stellar Burgers";
        assertEquals(expectedTitle, actualTitle);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}