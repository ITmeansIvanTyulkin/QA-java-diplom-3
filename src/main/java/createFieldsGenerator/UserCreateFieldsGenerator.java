package createFieldsGenerator;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import userCreate.UserCreate;

import java.security.SecureRandom;
import java.util.Random;

public class UserCreateFieldsGenerator {

    // Инициализирую массив данных emails с 10 различными вариантами существующих электронных почт.
    static final String[] emails = {
            "@yandex.ru",       // 1.
            "@gmail.com",       // 2.
            "@yahoo.com",       // 3.
            "@mail.ru",         // 4.
            "@ya.ru",           // 5.
            "@hotbox.com",      // 6.
            "@rambler.ru",      // 7.
            "@list.ru",         // 8.
            "@bk.ru",           // 9.
            "@postbox.com"      // 10.
    };

    // Создаю генератор имени для email. К имени будут подставляться рандомно варианты email из массива String[] emails.
    public static String email() {
        Random randomLength = new Random();
        int length = randomLength.nextInt(10) + 1;

        Random randomEmails = new Random();
        int index = randomEmails.nextInt(emails.length);

        boolean useLetters = true;
        boolean useNumbers = false;

        String generatedEmail = RandomStringUtils.random(length, useLetters, useNumbers);

        return generatedEmail.toLowerCase() + emails[index];
    }

    // Создаю генератор пароля и привожу его к строковому типу в соответствии с полем JSON из документации.
    public static String password() {
        int max = 12;
        int min = 6;

        int randomInt = new SecureRandom().nextInt(max - min) + min;

        boolean useLetters = false;
        boolean useNumbers = true;

        String generatedPassword = RandomStringUtils.random(randomInt, useLetters, useNumbers);

        return generatedPassword.toString();
    }

    public static String passwordInvalid() {
        int max = 5;
        int min = 1;

        int randomInt = new SecureRandom().nextInt(max - min) + min;

        boolean useLetters = false;
        boolean useNumbers = true;

        String generatedPassword = RandomStringUtils.random(randomInt, useLetters, useNumbers);

        return generatedPassword.toString();
    }

    // Создаю генератор имён пользователей.
    public static String name() {
        Random name = new Random();
        int length = name.nextInt(10) + 1;

        boolean useLetters = true;
        boolean useNumbers = false;

        String generatedName = RandomStringUtils.random(length, useLetters, useNumbers);

        return generatedName;
    }

    // Создаю уникальных пользователей из конструкторов рандомных значений с помощью методов email(), password(), name(), описанных выше.
    @Step("Создание дефолтного пользователя через API.")
    public static UserCreate passingGeneratorDataViaApi() {
        return new UserCreate().setEmail("masha29@yahoo.com").setPassword("123456Hgd").setName("Маша");
    }

    // Метод для создания email существующего пользователя.
    public static UserCreate passingGeneratorNewEmail() {
        return new UserCreate().setEmail(email());
    }

    // Метод для создания password существующего пользователя.
    public static UserCreate passingGeneratorNewPassword() {
        return new UserCreate().setPassword(password());
    }

    // Метод для создания name существующего пользователя.
    public static UserCreate passingGeneratorNewName() {
        return new UserCreate().setName(name());
    }

    // Методы для создания полей для дефолтного пользователя.
    public static UserCreate defaultUserName() {
        return new UserCreate().setName("Маша");
    }

    public static UserCreate defaultUserEmail() {
        return new UserCreate().setEmail("masha29@yahoo.com");
    }

    public static UserCreate defaultUserPassword() {
        return new UserCreate().setPassword("123456Hgd");
    }
}