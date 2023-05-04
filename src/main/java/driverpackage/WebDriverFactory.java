package driverpackage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

// Специальный класс для переключения браузеров.
public class WebDriverFactory {

    public static WebDriver getDriver(String url) {
        String browserName = System.getenv().get("browser");

        WebDriver driver;
        switch(browserName) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(new ChromeOptions()
                        .addArguments("--remote-allow-origins=*")
                );
                break;
            case "yandex":
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\Honor MagicBook Pro\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\chromedriver_win32\\chromedriver");
                driver = new ChromeDriver(new ChromeOptions()
                        .setBinary("C:\\Users\\Honor MagicBook Pro\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe")
                );
                break;
            default: throw new RuntimeException("Browser " + browserName + " not exist");
        }

        driver.manage().window().maximize();
        driver.get(url);

        return driver;
    }
}