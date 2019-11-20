import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized.Parameter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;


@RunWith(Parameterized.class)
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

    @Parameter(0)
    public int menuId;

    @Parameter(1)
    public int submenuId;

    @Parameter(2)
    public String expectedText;

    @Parameters(name = "{index}: Test with m1={0}, m2 ={1}, result is:{2} ")
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
                {1, 1, "Template"},
                {1, 2, "Logotype"},
                {2, 1, "Catalog"},
                {2, 2, "Attribute Groups"},
                {2, 3, "Option Groups"},
                {2, 4, "Manufacturers"},
                {2, 5, "Suppliers"},
                {2, 6, "Delivery Statuses"},
                {2, 7, "Sold Out Statuses"},
                {2, 8, "Quantity Units"},
                {2, 9, "CSV Import/Export"},
                {3, 1, "Countries"},
                {4, 1, "Currencies"},
                {5, 1, "Customers"},
                {5, 2, "CSV Import/Export"},
                {5, 3, "Newsletter"},
                {6, 1, "Geo Zones"},
                {7, 1, "Languages"},
                {7, 2, "Storage Encoding"},
                {8, 1, "Customer Modules"},
                {8, 2, "Shipping Modules"},
                {8, 3, "Payment Modules"},
                {8, 4, "Order Modules"},
                {8, 5, "Order Total Modules"},
                {8, 6, "Job Modules"},
                {9, 1, "Orders"},
                {9, 2, "Order Statuses"},
                {10, 1, "Pages"},
                {10, 2, "CSV Import/Export"},
                {11, 1, "Monthly Sales"},
                {11, 2, "Most Sold Products"},
                {11, 3, "Most Shopping Customers"},
                {12, 1, "Settings"},
                {13, 1, "Slides"},
                {14, 1, "Tax Rates"},
                {14, 2, "Tax Classes"},
                {15, 1, "Search Translations"},
                {15, 2, "Scan Files For Translations"},
                {15, 3, "CSV Import/Export"},
                {16, 1, "Users"}
        };
        return Arrays.asList(data);
    }

    @Test
    public void test1() throws InterruptedException {
        driver.get("http://demo.litecart.net/admin/login.php?redirect_url=%2Fadmin%2F");
        driver.findElement(By.cssSelector(".btn")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector(".app:nth-child(" + menuId + ")")).click();
        if (submenuId != 1) {
            Thread.sleep(500);
            driver.findElement(By.cssSelector("li.app.selected > ul > li:nth-child("+ submenuId + ")")).click();
        }
        assertThat(driver.findElement(By.cssSelector("div.panel-heading")).getText(), is(expectedText));
    }
}
