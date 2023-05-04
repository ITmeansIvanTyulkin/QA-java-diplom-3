package registrationANewUser;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page_object_models.HeaderElements;
import page_object_models.PersonalCabinet;

import static credentials.Constants.*;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class NewUserPasswordCheckTheLengthTest {
    private PersonalCabinet personalCabinet;
    private WebDriver driver;
    private final String attempt;
    private final String expected = LOGIN_USER_URL;

    public NewUserPasswordCheckTheLengthTest(String attempt) {
        this.attempt = attempt;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {"12345"},      // Должен падать, так как задана некорректная длина пароля.
                {"!@#$%^"},     // Здесь баг, ТЕСТ ДОЛЖЕН ПАДАТЬ, так как задано 6 спецсимволов, но успешен.
                {"      "},     // Здесь баг, ТЕСТ ДОЛЖЕН ПАДАТЬ, но пробелы принимаются в качестве пароля, успешен.
                {"1J89k"},      // Должен падать, так как задана некорректная длина пароля из цифр и букв..
                {"1234Kl"},     // Успешный: цифры и буквы (заглавные и строчные).
        };
    }

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    @Test
    @DisplayName("Тест на проверку длины пароля.")
    @Description("Проверка, что пароль НЕ менее 6 символов. В тестовых данных присутствют варианты с паролем менее 6 символов - эти тесты - падающие.")
    public void shouldCheckThePasswordLength() {
        personalCabinet = new PersonalCabinet(driver);
        personalCabinet.goToPersonalCabinetPage();
        personalCabinet.waitForRegistrationPage();
        personalCabinet.nameFieldFillingToRegisterAUser();
        personalCabinet.emailFieldFillingToRegisterAUser();
        personalCabinet.passwordLengthCheck(attempt);
        personalCabinet.registrationButtonClick();
        personalCabinet.waitForEnterPage();
        assertEquals(expected, driver.getCurrentUrl());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}