package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CardFormTest {
    private WebDriver driver;

    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999/");
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
        driver = null;
    }

    @Test
    public void shouldSubmitForm() throws InterruptedException {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Пушкин Александр");
//        try {
//            Thread.sleep(2000);
//        } catch(InterruptedException ex) {}
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79270000000");
//        try {
//            Thread.sleep(2000);
//        } catch(InterruptedException ex) {}
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
//        try {
//            Thread.sleep(2000);
//        } catch(InterruptedException ex) {}
        driver.findElement(By.cssSelector("button.button")).click();
//        try {
//            Thread.sleep(2000);
//        } catch(InterruptedException ex) {}
        String text = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText();

        Assertions.assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());

//        Thread.sleep(2000);

    }
}
