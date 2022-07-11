import dns.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;

public class Runner {

    private WebDriver webDriver;
    private WebDriverWait webDriverWait;
    private Actions actions;
    private Page page;

    @BeforeClass(description = "Setup the browser")
    public void browserSetup() {
        webDriver = new ChromeDriver();
        webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        actions = new Actions(webDriver);
        webDriver.manage().window().maximize();
    }

    @BeforeClass(description = "Algorithm of methods", dependsOnMethods = "browserSetup")
    public void algorithmOfMethods() {
        page = new Page(webDriver, webDriverWait, actions)
                .openPage("https://www.dns-shop.ru/")
                .selectItemInMainMenu();
    }

    @Test(priority = 1, description = "Check of total number of goods")
    public void checkTotalSum() {
        int totalNumberOfGoods = Integer.parseInt(page.getTotalNumberOfGoods());
        Assert.assertEquals(totalNumberOfGoods, page.getSumNumberOfGoods(), "Number of goods doesn't equal");
    }

    @Test(priority = 2, description = "Check searchArea")
    public void checkSearchArea() {
        String text = page.getNameOfGood();
        page.selectNameOfGoodIntoSearch();
        Assert.assertEquals(text, page.getTextFromSearchArea(),
                "Text in search area and chosen are not equal");
    }

    @AfterClass(description = "Close the browser")
    public void browserTearDown() {
        webDriver.quit();
        webDriver = null;
    }
}
