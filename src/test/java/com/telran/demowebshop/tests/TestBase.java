package com.telran.demowebshop.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestBase {
    WebDriver dr;

    @BeforeMethod
    public void setUp() {
        dr = new ChromeDriver();
        dr.get("https://demowebshop.tricentis.com/");
        dr.manage().window().maximize();
        dr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDown() {
        dr.close();
    }


    public void pressLogoutButton() {
        dr.findElement(By.cssSelector(".ico-logout")).click();
    }

    public void pause() throws InterruptedException {
        Thread.sleep(2000);
    }

    public boolean isElementPresent(By locator) {
        try {
            dr.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void type(By locator, String text) {
        click(locator);
        dr.findElement(locator).clear();
        dr.findElement(locator).sendKeys(text);
    }

    public void click(By locator) {
        dr.findElement(locator).click();
    }


}
