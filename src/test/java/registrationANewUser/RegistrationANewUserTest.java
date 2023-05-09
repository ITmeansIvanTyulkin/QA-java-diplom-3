package registrationANewUser;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import logging.Logging;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page_object_models.PersonalCabinet;

import static credentials.Constants.*;
import static org.junit.Assert.assertEquals;

public class RegistrationANewUserTest {

    private WebDriver driver;
    private Logging logging;
    private PersonalCabinet personalCabinet;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
        //driver = WebDriverFactory.getDriver(BASE_URL);
    }

    @Test
    @DisplayName("Тест на успешную регистрацию.")
    @Description("Проверка, что происходит успешная регистрация нового пользователя с переходом на страницу входа в кабинет.")
    public void successfulRegistration() {
        personalCabinet = new PersonalCabinet(driver);
        logging = new Logging(driver);
        personalCabinet.goToPersonalCabinetPage();
        personalCabinet.waitForRegistrationPage();
        personalCabinet.registrationANewUser();
        assertEquals(REGISTER_PAGE_URL, driver.getCurrentUrl()); // Первая проверка - осуществляется по URL.
        logging.checkEnterPageTextIsDisplayedTrue(); // Вторая проверка - осуществояется по названию формы.
    }

    @Test
    @DisplayName("Тест на вход в систему зарегистрированного пользователя.")
    @Description("Проверка, что происходит успешный вход в раздел сайта для зарегистрированных пользователей.")
    public void successfulLogging() {
        personalCabinet = new PersonalCabinet(driver);
        logging = new Logging(driver);
        personalCabinet.enteringTheSystem();
        personalCabinet.waitForPersonalCabinetPage();
        assertEquals(PROFILE_URL, driver.getCurrentUrl()); // Первая проверка - осуществляется по URL.
        logging.checkAccountTextIsDisplayed(); // Вторая проверка - осуществляется по названию формы.
    }

    @Test
    @DisplayName("Тест на переход из личного кабинета в конструктор.")
    @Description("Проверка, что происходит успешный переход в раздел сайта - нажатием на Конструктор.")
    public void constructorPress() {
        personalCabinet = new PersonalCabinet(driver);
        logging = new Logging(driver);
        personalCabinet.movingFromThePersonalCabinetToTheConstructor();
        personalCabinet.waitForHomePage();
        assertEquals(BASE_URL, driver.getCurrentUrl()); // Первая проверка - осуществляется по URL.
        logging.checkConstructorPageTextIsDisplayed(); // Вторая проверка - осуществляется по названию формы.
    }

    @Test
    @DisplayName("Тест на переход из личного кабинета на главную страницу по клику на лого.")
    @Description("Проверка, что происходит успешный переход в раздел сайта - нажатием на логотип Stellar Burgers.")
    public void logoPress() {
        personalCabinet = new PersonalCabinet(driver);
        logging = new Logging(driver);
        personalCabinet.movingFromThePersonalCabinetToTheMainPageViaLogoClick();
        personalCabinet.waitForHomePage();
        assertEquals(BASE_URL, driver.getCurrentUrl()); // Первая проверка - осуществляется по URL.
        logging.checkConstructorPageTextIsDisplayed(); // Вторая проверка - осуществляется по названию формы.
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}