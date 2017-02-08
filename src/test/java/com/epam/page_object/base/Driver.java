package com.epam.page_object.base;


import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Driver {

    public static WebDriver Instance = null;

    private Driver() {
    }

    public static WebDriver Initialize(String browser) {
        System.setProperty("webdriver.gecko.driver", "resource\\geckodriver-v0.13.0-win64\\geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "resource\\chromedriver_win32(1)\\chromedriver.exe");

        if (Instance == null) {
            setGrid(browser);
        }
        return Instance;
    }

    public static void setGrid(String browser) {

        if (browser.equalsIgnoreCase("Firefox")) {
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            capabilities.setPlatform(Platform.WINDOWS);
            try {
                Driver.Instance = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        if (browser.equalsIgnoreCase("Chrome")) {
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setPlatform(Platform.WINDOWS);
            try {
                Driver.Instance = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void quit() {
        Driver.Instance.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Quit Browser");
        Instance.quit();
    }

}
