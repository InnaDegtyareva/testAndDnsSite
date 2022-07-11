package dns;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage {

    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait;
    protected Actions actions;

    public AbstractPage(WebDriver webDriver, WebDriverWait webDriverWait, Actions actions) {
        this.webDriver = webDriver;
        this.webDriverWait = webDriverWait;
        this.actions = actions;
        PageFactory.initElements(webDriver, this);
    }
}
