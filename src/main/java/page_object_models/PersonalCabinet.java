package page_object_models;

import createFieldsGenerator.UserCreateFieldsGenerator;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import userCreate.UserCreate;

public class PersonalCabinet {

    private HeaderElements headerElements;
    private final WebDriver driver;
    PersonalCabinet step;

    public PersonalCabinet(WebDriver driver) {
        this.driver = driver;
        this.headerElements = new HeaderElements(driver);
    }

    // Локаторы для элементов страницы Личный Кабинет - ВХОД.
    private static final By constructorButton2 = By.xpath("//div//header/nav/ul/li[1]/a/p");
    private static final By registrationWord = By.xpath("//div/main//div/p[1]/a");
    private static final By passwordRestoreWord = By.xpath("//div/div/main/div//p[2]/a");
    private static final By emailFieldSet = By.xpath("//div/div/main/div/form/fieldset[1]/div/div/input");
    private static final By passwordFieldSet = By.xpath("//div/div/main/div/form/fieldset[2]/div/div/input");
    private static final By enterButton = By.xpath("//div//main/div/form/button");


    // Локаторы для элементов страницы Личный Кабинет - ЗАРЕГИСТРИРОВАТЬСЯ.
    private static final By nameFieldRegistration = By.xpath("//div//main/div/form/fieldset[1]/div/div/input");
    private static final By emailFieldRegistration = By.xpath("//div//main/div/form/fieldset[2]/div/div/input");
    private static final By passwordFieldRegistration = By.xpath("//div//main/div/form/fieldset[3]/div/div/input");
    private static final By registrationButton = By.xpath("//div//main/div/form/button");


    // Общие методы взаимодействия с локаторами.
    @Step("Ожидание загрузки страницы: проверили видимость элемента на странице.")
    public void waitForEnterPage() {
        new WebDriverWait(driver, 8)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[text() = 'Вход']")));
    }

    @Step("Ожидание загрузки страницы: проверили видимость элемента а странице.")
    public void waitForRegistrationPage() {
        new WebDriverWait(driver, 8)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[text() = 'Регистрация']")));
    }

    @Step("Ожидание загрузки страницы: проверили видимость элемента а странице.")
    public void waitForPersonalCabinetPage() {
        new WebDriverWait(driver, 8)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[text() = 'В этом разделе вы можете изменить свои персональные данные']")));
    }

    @Step("Ожидание загрузки главной страницы: проверили видимость элемента а странице.")
    public void waitForHomePage() {
        new WebDriverWait(driver, 8)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[text() = 'Соберите бургер']")));
    }

    // Методы взаимодействия с локаторами при регистрации пользователя.
    @Step("Заполнение поля name для регистрации пользователя.")
    public String nameFieldFillingToRegisterAUser() {
        UserCreate name = UserCreateFieldsGenerator.passingGeneratorNewName();
        driver.findElement(nameFieldRegistration).sendKeys(name.getName());
        String getName = name.getName();
        return getName;
    }

    @Step("Заполнение поля email для регистрации пользователя.")
    public String emailFieldFillingToRegisterAUser() {
        UserCreate email = UserCreateFieldsGenerator.passingGeneratorNewEmail();
        driver.findElement(emailFieldRegistration).sendKeys(email.getEmail());
        String getEmail = email.getEmail();
        return getEmail;
    }

    @Step("Заполнение поля password для регистрации пользователя.")
    public String passwordFieldFillingToRegisterAUser() {
        UserCreate password = UserCreateFieldsGenerator.passingGeneratorNewPassword();
        driver.findElement(passwordFieldRegistration).sendKeys(password.getPassword());
        String getPassword = password.getPassword();
        return getPassword;
    }

    @Step("Метод для передачи данных для параметризированного теста проверки длины пароля.")
    public boolean passwordLengthCheck(String text) {
        //UserCreate password = UserCreateFieldsGenerator.passwordLengthPassing();
        //driver.findElement(passwordFieldRegistration).sendKeys(password.getPassword());
        driver.findElement(passwordFieldRegistration).sendKeys(text);
        return false;
    }

    @Step("Нажать на кнопку Зарегистрироваться.")
    public void registrationButtonClick() {
        driver.findElement(registrationButton).click();
    }

    @Step("Удаление пользователя.")
    public void userDelete() {
    }

    @Step("Переход на форму регистрации нового пользователя.")
    public void goToPersonalCabinetPage() {
        headerElements.personalCabinetButtonClick();
        driver.findElement(registrationWord).click();
    }

    @Step("Единый шаг регистрации нового пользователя.")
    public ValidatableResponse registrationANewUser() {
        // Заполнение поля name для регистрации пользователя.
        nameFieldFillingToRegisterAUser();
        // Заполнение поля email для регистрации пользователя.
        emailFieldFillingToRegisterAUser();
        // Заполнение поля password для регистрации пользователя.
        passwordFieldFillingToRegisterAUser();
        // Нажать на кнопку Зарегистрироваться.
        registrationButtonClick();
        return null;
    }

    @Step("Вход в систему зарегистрированного пользователя.")
    public ValidatableResponse enteringTheSystem() {
        // Иду на страницу регистрации.
        goToPersonalCabinetPage();

        // В переменную userNameData извлекаю данные сгенерированного Name.
        UserCreate name = UserCreateFieldsGenerator.passingGeneratorNewName();
        driver.findElement(nameFieldRegistration).sendKeys(name.getName());

        // В переменную userEmailData извлекаю данные сгенерированного Email.
        UserCreate responseEmail = UserCreateFieldsGenerator.passingGeneratorNewEmail();
        driver.findElement(emailFieldRegistration).sendKeys(responseEmail.getEmail());
        String userEmailData = responseEmail.getEmail();

        // В переменную userPasswordData извлекаю данные сгенерированного Password.
        UserCreate responsePassword = UserCreateFieldsGenerator.passingGeneratorNewPassword();
        driver.findElement(passwordFieldRegistration).sendKeys(responsePassword.getPassword());
        String userPasswordData = responsePassword.getPassword();

        // Нажимаю на кнопку Зарегистрироваться.
        registrationButtonClick();

        // Ожидаю загрузки страницы "Вход".
        waitForEnterPage();

        // Заполняю поля Email и Password и нажимаю кнопку "Войти".
        driver.findElement(emailFieldSet).sendKeys(userEmailData);
        driver.findElement(passwordFieldSet).sendKeys(userPasswordData);
        driver.findElement(enterButton).click();

        // Ожидаю загрузку главной страницы уже будучи зарегистрированным пользователем.
        headerElements.waitForLoadHomePage();

        // Перехожу в Личный Кабинет.
        headerElements.personalCabinetButtonClick();

        return null;
    }

    @Step("Переход из личного кабинета в конструктор.")
    public void movingFromThePersonalCabinetToTheConstructor() {
        enteringTheSystem();
        waitForPersonalCabinetPage();
        driver.findElement(constructorButton2).click();
    }

    @Step("Переход из личного кабинета на главную по клику на логотип Stellar Burgers.")
    public void movingFromThePersonalCabinetToTheMainPageViaLogoClick() {
        enteringTheSystem();
        waitForPersonalCabinetPage();
        headerElements.logoButtonClick();
    }
}