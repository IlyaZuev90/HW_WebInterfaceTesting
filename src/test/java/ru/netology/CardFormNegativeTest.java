package ru.netology;

        import io.github.bonigarcia.wdm.WebDriverManager;
        import org.junit.jupiter.api.*;
        import org.openqa.selenium.By;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.openqa.selenium.chrome.ChromeOptions;

public class CardFormNegativeTest {
    private WebDriver driver;

    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999/");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldTestFormInvalidName() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Pushkin Alexander");
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
        String text = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText();

        Assertions.assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());

//        Thread.sleep(2000);

    }

    @Test
    void shouldTestFormNoName() {
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
        String text = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText();

        Assertions.assertEquals("Поле обязательно для заполнения", text.trim());

//        Thread.sleep(2000);

    }

    @Test
    void shouldTestFormInvalidNumber() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Пушкин Александр");
//        try {
//            Thread.sleep(2000);
//        } catch(InterruptedException ex) {}
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("none");
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
        String text = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText();

        Assertions.assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());

//        Thread.sleep(2000);

    }

    @Test
    void shouldTestFormNoNumber() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Пушкин Александр");
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
        String text = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText();

        Assertions.assertEquals("Поле обязательно для заполнения", text.trim());

//        Thread.sleep(2000);
    }

    @Test
    void shouldTestFormNoAgreement() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Пушкин Александр");
//        try {
//            Thread.sleep(2000);
//        } catch(InterruptedException ex) {}
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79270000000");
//        try {
//            Thread.sleep(2000);
//        } catch(InterruptedException ex) {}
        driver.findElement(By.cssSelector("button.button")).click();
//        try {
//            Thread.sleep(2000);
//        } catch(InterruptedException ex) {}
        String text = driver.findElement(By.className("checkbox__text")).getText();

        Assertions.assertTrue(driver.findElement(By.cssSelector("[data-test-id='agreement'].input_invalid")).isDisplayed());

//        Thread.sleep(2000);
    }
}