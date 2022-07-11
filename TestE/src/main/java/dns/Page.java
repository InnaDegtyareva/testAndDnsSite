package dns;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Page extends AbstractPage {

    @FindBy(xpath = "//div[contains(@class,'root-subtitles')]")
    private List<WebElement> mainMenu;
    @FindBy(xpath = "(//input[@placeholder='Поиск по сайту'])[2]")
    private WebElement titleOfMainMenu;
    @FindBy(xpath = "//a[contains(@class,'first-level')]")
    private WebElement titleOfSubMenu;
    @FindBy(xpath = "//a[contains(@class,'second-level')]//span")
    private List<WebElement> subMenu;
    @FindBy(xpath = "//span[@class='menu-desktop__popup']")
    private WebElement subMenuPopUp;
    @FindBy(xpath = "//a[contains(@class,'popup-link')]//span")
    private List<WebElement> numberOfGoods;
    @FindBy(xpath = "(//a[contains(@class,'second-level')])[1]")
    private WebElement nameOfGood;
    @FindBy(xpath="(//input[@placeholder='Поиск по сайту'])[2]")
    private WebElement searchArea;
    @FindBy(xpath="(//span[contains(@class,'ui-input-search')])[4]")
    private WebElement searchIcon;
    @FindBy(xpath="//h1[text()='Результаты поиска']")
    private WebElement titleOfResult;

    Random random = new Random();


    public Page(WebDriver webDriver, WebDriverWait webDriverWait, Actions actions) {
        super(webDriver, webDriverWait, actions);
    }

    public Page openPage(String url) {
        webDriver.get(url);
        return this;
    }

    public Page selectItemInMainMenu() {
        webDriverWait.until(ExpectedConditions.visibilityOf(titleOfMainMenu));
        int selectItemInMainMenu = random.nextInt(mainMenu.size());
        actions.moveToElement(mainMenu.get(selectItemInMainMenu)).perform();
        return this;
    }

    public String getTotalNumberOfGoods() {
        webDriverWait.until(ExpectedConditions.visibilityOf(titleOfSubMenu));
        int selectItemInSubMenu = random.nextInt(subMenu.size());
        actions.moveToElement(subMenu.get(selectItemInSubMenu)).perform();
        return subMenu.get(selectItemInSubMenu).getText();
    }


    public int getSumNumberOfGoods() {
        webDriverWait.until(ExpectedConditions.visibilityOf(subMenuPopUp));
        List<String> sumNumberOfGoods = numberOfGoods.stream().map(e -> e.getText()).collect(Collectors.toList());
        int sum = 0;
        for (String i : sumNumberOfGoods) {
            sum += Integer.parseInt(i);
        }
        System.out.println(sum);
        return sum;
    }

    public String getNameOfGood() {
        return nameOfGood.getText();
    }

    public Page selectNameOfGoodIntoSearch() {
        System.out.println(getNameOfGood());
        searchArea.sendKeys(getNameOfGood());
        searchIcon.click();
        return new Page(webDriver, webDriverWait, actions);
    }

    public String getTextFromSearchArea() {
        webDriverWait.until(ExpectedConditions.visibilityOf(titleOfResult));
        return searchArea.getAttribute("value");
    }
}

