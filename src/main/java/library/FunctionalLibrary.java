package library;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;

public class FunctionalLibrary {

    private WebDriver driver;
    private WebDriverWait wait;

    public FunctionalLibrary(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // =========================================================
    // 1. GENERIC SELENIUM WRAPPER METHODS
    // =========================================================

    public WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForClickability(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void enterText(By locator, String text) {
        WebElement element = waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    public void clickElement(By locator) {
        waitForClickability(locator).click();
    }

    public String getElementText(By locator) {
        return waitForVisibility(locator).getText().trim();
    }

    public boolean isElementDisplayed(By locator) {
        try {
            return waitForVisibility(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // =========================================================
    // 2. FUNCTIONAL METHODS FOR LOGIN
    // =========================================================

    public boolean isLoginPageLoaded() {
        return isElementDisplayed(LoginPage.USERNAME_FIELD);
    }

    public void enterCredentials(String username, String password) {
        enterText(LoginPage.USERNAME_FIELD, username);
        enterText(LoginPage.PASSWORD_FIELD, password);
    }

    public void clickLoginButton() {
        clickElement(LoginPage.LOGIN_BUTTON);
    }

    public void performLogin(String username, String password) {
        enterCredentials(username, password);
        clickLoginButton();
    }

    public String getDashboardHeader() {
        return getElementText(LoginPage.DASHBOARD_HEADER);
    }

    public String getInvalidLoginMessage() {
        return getElementText(LoginPage.INVALID_ALERT_MSG);
    }
}
