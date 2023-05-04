package loggingTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import logging.Logging;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page_object_models.HeaderElements;
import page_object_models.PersonalCabinet;

import static credentials.Constants.*;
import static org.junit.Assert.assertEquals;

public class LoggingTest {

    private PersonalCabinet personalCabinet;
    private HeaderElements headerElements;
    private Logging logging;
    private WebDriver driver;

    public LoggingTest() {
    }

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    @Test
    @DisplayName("Тест входа по кнопке Войти в аккаунт с главной страницы (UI).")
    @Description("Проверка, что происходит успешный вход в раздел сайта для зарегистрированных пользователей.")
    public void shouldLoginFromTheMainPageViaUI() {
        personalCabinet = new PersonalCabinet(driver);
        headerElements = new HeaderElements(driver);
        logging = new Logging(driver);
        personalCabinet.waitForHomePage();
        logging.registrationDefaultUser();
        headerElements.personalCabinetButtonClick();
        logging.logInTheSystem();
        headerElements.waitForLoadHomePage();
        headerElements.personalCabinetButtonClick();
        personalCabinet.waitForPersonalCabinetPage();
        assertEquals(PROFILE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Тест входа по кнопке Личный кабинет в аккаунт с главной страницы (UI).")
    @Description("Проверка, что происходит успешный вход в раздел сайта для зарегистрированных пользователей.")
    public void loggingFromTheMainPage() {
        personalCabinet = new PersonalCabinet(driver);
        logging = new Logging(driver);
        logging.loggingViaThePersonalCabinetButton();
        personalCabinet.waitForPersonalCabinetPage();
        assertEquals(PROFILE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Тест входа по кнопке Войти в форме регистрации (UI).")
    @Description("Проверка, что происходит успешный вход в раздел сайта для зарегистрированных пользователей.")
    public void loggingTheUserFromTheRegistrationFormButton() {
        logging = new Logging(driver);
        logging.loggingViaTheRegistrationButtonForm();
        assertEquals(PROFILE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Тест входа по кнопке Войти в форме восстановления пароля (UI).")
    @Description("Проверка, что происходит успешный вход в раздел сайта для зарегистрированных пользователей.")
    public void enterTheSystem() {
        logging = new Logging(driver);
        logging.pressThePasswordRestoreButton();
        assertEquals(LOGIN_USER_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Тест выхода по кнопке Выйти в личном кабинете пользователя (UI).")
    @Description("Проверка, что происходит успешный выход из раздела сайта для зарегистрированных пользователей.")
    public void loggingOut() {
        logging = new Logging(driver);
        logging.exitFromThePersonalCabinet();
        assertEquals(PROFILE_URL, driver.getCurrentUrl());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}