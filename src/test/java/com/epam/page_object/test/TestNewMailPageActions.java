package com.epam.page_object.test;

import com.epam.page_object.base.Driver;
import com.epam.page_object.pages.NewMailPage;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static com.epam.page_object.base.Driver.*;

public class TestNewMailPageActions {

    private NewMailPage objMailPage;

    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true, description = "Start browser")
    public void setup(String brow) {
        Driver.Initialize(brow);
    }


    @BeforeClass(dependsOnMethods = "setup", description = "Add implicit wait")
    public void addImplicitly() {
        Driver.Instance.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @AfterClass(alwaysRun = true)
    public void closeBrowser() {
        Driver.quit();
    }

    @DataProvider(name = "NewMail_Provider")
    public Object[][] LoginCredentials() {
        Object[][] NewMail = new Object[1][3];
        NewMail[0][0] = "https://mail.yandex.by/";
        NewMail[0][1] = "testtask28";
        NewMail[0][2] = "testtask28testtask28";
        return NewMail;
    }

    @Test(dataProvider = "NewMail_Provider", groups = "Mail Page Test", description = "Tests the actions on New Mail page ")
    public void testMailIsInDraft(String URL, String LOGIN, String PASSW) {
        objMailPage = new NewMailPage(Driver.Instance);
        objMailPage.goToUrl(URL);
        objMailPage.doLogin(LOGIN, PASSW);
       // objMailPage.doJSScroll();
        objMailPage.rightClickOnNewMail();
    }

}
