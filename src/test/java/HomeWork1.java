import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;


public class HomeWork1 {
    private WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void test1() throws InterruptedException {
        driver.get("http://demo.litecart.net/admin/login.php?redirect_url=%2Fadmin%2F");
        driver.findElement(By.cssSelector(".btn")).click();
        Thread.sleep(3000);
        WebElement boxAppMenu =
                driver.findElement(By.id("box-apps-menu"));
        List<WebElement> itemAppMenu = boxAppMenu.findElements(By.tagName("li"));
        for (int i = 1; i < (itemAppMenu.size() + 1); i++)
        {
            driver.findElement(By.cssSelector(".app:nth-child(" + i + ")")).click();
            Thread.sleep(500);
            if (driver.findElements(By.cssSelector("li.app.selected > ul > li")).size() == 0) {
                String header = driver.findElement(By.cssSelector("div.panel-heading")).getText();
                assertThat(header, not(""));
                System.out.println(header);
            }else {
                WebElement boxSubMenu =
                        driver.findElement(By.cssSelector("li.app.selected > ul"));
                List<WebElement> itemSubMenu = boxSubMenu.findElements(By.tagName("li"));
                for (int x = 2; x < (itemSubMenu.size() + 1); x++)
                {
                    driver.findElement(By.cssSelector("li.app.selected > ul > li:nth-child("+ x + ")")).click();
                    Thread.sleep(500);
                    String header = driver.findElement(By.cssSelector("div.panel-heading")).getText();
                    assertThat(header, not(""));
                    System.out.println(header);
                }
            }
        }
    }
}
