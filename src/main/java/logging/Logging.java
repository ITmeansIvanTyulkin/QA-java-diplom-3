package logging;

import createFieldsGenerator.UserCreateFieldsGenerator;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_object_models.HeaderElements;
import page_object_models.PersonalCabinet;
import userCreate.UserCreate;

public class Logging {

    UserCreateFieldsGenerator userCreateFieldsGenerator;
    private PersonalCabinet personalCabinet;
    private HeaderElements headerElements;
    private final WebDriver driver;

    public Logging(WebDriver driver) {
        this.driver = driver;
        this.headerElements = new HeaderElements(driver);
        this.personalCabinet = new PersonalCabinet(driver);
        this.userCreateFieldsGenerator = new UserCreateFieldsGenerator();
    }

    // Локаторы для элементов главной страницы.
    private static final By logInButton = By.xpath("//div//main/section[2]/div/button");

    // Локаторы для элементов страницы Личный Кабинет - ЗАРЕГИСТРИРОВАТЬСЯ.
    private static final By nameFieldRegistration = By.xpath("//div//main/div/form/fieldset[1]/div/div/input");
    private static final By emailFieldRegistration = By.xpath("//div//main/div/form/fieldset[2]/div/div/input");
    private static final By passwordFieldRegistration = By.xpath("//div//main/div/form/fieldset[3]/div/div/input");
    private static final By registrationButton = By.xpath("//div//main/div/form/button");
    private static final By registrationWord = By.xpath("//div/main//div/p[1]/a");

    // Локатор для кнопки войти зарегистрированному в системе пользователю.
    private static final By enterButton = By.xpath("//div//main/div/form/button");

    // Локатор для поля email для ввода почты в форме входа УЖЕ зарегистрированного в системе пользователя.
    private static final By emailFieldForARegisteredUser = By.xpath("//div//main/div/form/fieldset[1]/div//input");

    // Локатор для поля password для ввода пароля в форме входа УЖЕ зарегистрированного в системе пользователя.
    private static final By passwordFieldForARegisteredUser = By.xpath("//div//main/div/form/fieldset[2]/div//input");

    // Локатор для восстановления пароля.
    private static final By passwordRestore = By.xpath("//div//main/div//p[2]/a");
    private static final By emailField = By.xpath("//div//main/div//ul/li[2]/div//input");
    private static final By passwordField = By.xpath("//div//main/div//ul/li[3]/div//input");
    private static final By exit = By.xpath("//div//main/div/nav/ul/li[3]/button");
    private static final By emailToBeRestored = By.xpath("//div//main/div/form/fieldset/div//input");
    private static final By enterButtonFromTheRestorePage = By.xpath("//div//main/div//p/a");


    // Методы для восстановления пароля.
    @Step("Ожидание загрузки страницы: проверили видимость элемента в хедере.")
    public void waitForLoadPasswordRestorePage() {
        new WebDriverWait(driver, 8)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[text() = 'Восстановление пароля']")));
    }

    @Step("Нажать на кнопку Войти со страницы восстановления пароля.")
    public void pressThePasswordRestoreButton() {
        pressTheButtonToEnter();
        personalCabinet.waitForEnterPage();
        driver.findElement(passwordRestore).click();
        waitForLoadPasswordRestorePage();
        driver.findElement(enterButtonFromTheRestorePage).click();
        personalCabinet.waitForEnterPage();
    }

    // Методы взаимодействия с локаторами при регистрации пользователя.
    @Step("Заполнение поля name для регистрации дефолтного пользователя.")
    public void nameFieldFillingToRegisterADefaultUser() {
        UserCreate name = UserCreateFieldsGenerator.defaultUserName();
        driver.findElement(nameFieldRegistration).sendKeys(name.getName());
    }

    @Step("Заполнение поля email для регистрации дефолтного пользователя.")
    public void emailFieldFillingToRegisterADefaultUser() {
        UserCreate email = UserCreateFieldsGenerator.defaultUserEmail();
        driver.findElement(emailFieldRegistration).sendKeys(email.getEmail());
    }

    @Step("Заполнение поля email для для ввода почты УЖЕ зарегистрированного в системе пользователя.")
    public void emailFieldForARegisteredUser() {
        UserCreate email = UserCreateFieldsGenerator.defaultUserEmail();
        driver.findElement(emailFieldForARegisteredUser).sendKeys(email.getEmail());
    }

    @Step("Заполнение поля password для регистрации дефолтного пользователя.")
    public void passwordFieldFillingToRegisterADefaultUser() {
        UserCreate password = UserCreateFieldsGenerator.defaultUserPassword();
        driver.findElement(passwordFieldRegistration).sendKeys(password.getPassword());
    }

    @Step("Заполнение поля password для регистрации дефолтного пользователя.")
    public void passwordFieldForARegisteredUser() {
        UserCreate password = UserCreateFieldsGenerator.defaultUserPassword();
        driver.findElement(passwordFieldForARegisteredUser).sendKeys(password.getPassword());
    }

    @Step("Метод нажатия на кнопку Войти в аккаунт на главной странице.")
    public void pressTheButtonToEnter() {
        driver.findElement(logInButton).click();
    }

    @Step("Регистрация дефолтного пользователя через UI.")
    public void registrationDefaultUser() {
        personalCabinet = new PersonalCabinet(driver);
        driver.findElement(logInButton).click();
        // Ожидание загрузки страницы со словом ВХОД.
        personalCabinet.waitForEnterPage();
        // Клик по слову Регистрация внизу формы.
        personalCabinet.goToPersonalCabinetPage();
        // Ожидание загрузки страницы регистрации.
        personalCabinet.waitForRegistrationPage();
        // Передача данных в поля.
        nameFieldFillingToRegisterADefaultUser();
        emailFieldFillingToRegisterADefaultUser();
        passwordFieldFillingToRegisterADefaultUser();
        // Нажать кнопку ЗАРЕГИСТРИРОВАТЬСЯ.
        driver.findElement(registrationButton).click();
        // Ожидание загрузки страницы со словом ВХОД.
        personalCabinet.waitForEnterPage();
    }

    @Step("Вход по кнопке Личный кабинет в аккаунт на главной странице.")
    public void logInTheSystem() {
        emailFieldForARegisteredUser();
        passwordFieldForARegisteredUser();
        driver.findElement(enterButton).click();
    }

    @Step("Вход через кнопку Личный Кабинет.")
    public void loggingViaThePersonalCabinetButton() {
        personalCabinet.enteringTheSystem();
    }

    @Step("Вход через кнопку в форме регистрации.")
    public void loggingViaTheRegistrationButtonForm() {
        personalCabinet.enteringTheSystem();
        personalCabinet.waitForPersonalCabinetPage();
    }

    @Step("Выход по кнопке Выйти в личном кабинете.")
    public void exitFromThePersonalCabinet() {
        personalCabinet.enteringTheSystem();
        personalCabinet.waitForPersonalCabinetPage();
        driver.findElement(exit).click();
    }
}