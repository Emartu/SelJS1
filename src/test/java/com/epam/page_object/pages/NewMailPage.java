package com.epam.page_object.pages;

import com.epam.page_object.base.Driver;
import com.epam.page_object.base.WaitTool;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;


public class NewMailPage {

    private WaitTool waitTool = new WaitTool();
    private final WebDriver driver;

    @FindBy(xpath = "//input[@name='login']")
    private WebElement login;

    @FindBy(xpath = "//input[@name='passwd']")
    private WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submit;

    @FindBy(xpath = "//a[@data-key='view=toolbar-button-compose-go&id=compose-go']")
    private WebElement createMail;

    @FindBy(xpath = "//*[@class='js-compose-field mail-Bubbles']")
    private WebElement sendTo;

    @FindBy(xpath = "//*[@class='mail-Compose-Field-Input-Controller js-compose-field js-editor-tabfocus-prev']")
    private WebElement subject;

    @FindBy(xpath = "//*[@id='cke_1_contents']")
    private WebElement body;

    @FindBy(xpath = "//a[@data-key='view=folder&fid=6']")
    private WebElement draftLink;

    @FindBy(xpath = "//span[@title='sent via WebDriver']")
    private WebElement sentSubj;

    @FindBy(xpath = "//div[@data-id='0']")
    private WebElement contextNewPage;


    public NewMailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void goToUrl(String URL) {
        Driver.Instance.get(URL);
    }

    public void doLogin(String userName, String passw) {
        login.sendKeys(userName);
        password.sendKeys((passw));
        submit.click();
    }

    public void rightClickOnNewMail() {
        Actions actDraft = new Actions(driver);
        //actDraft.contextClick(draftLink).click(contextNewPage).build().perform();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        actDraft.contextClick(createMail).sendKeys(Keys.ENTER).build().perform();
    }

    public void doJSScroll() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //Vertical scroll - down by 150  pixels
        js.executeScript("window.scrollBy(0,150)");
        String sText = js.executeScript("return document.title;").toString();
        System.out.println(sText);
    }

}
