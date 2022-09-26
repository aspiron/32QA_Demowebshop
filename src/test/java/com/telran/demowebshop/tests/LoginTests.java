package com.telran.demowebshop.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (!isLoginLinkPresent()) {
            pressLogoutButton();
        }
    }

    @Test
    public void loginPositiveTest() {
        Assert.assertTrue(isLoginLinkPresent());
        fillLoginForm();
        Assert.assertFalse(isLoginLinkPresent());
        pressLogoutButton();

    }


    public boolean isLoginLinkPresent() {
        return dr.findElements(By.cssSelector(".ico-login")).size() > 0;
    }

    public void fillLoginForm() {
        dr.findElement(By.cssSelector(".ico-login")).click();
        type(By.cssSelector("#Email"), "ap123456@gmail.com");
        type(By.cssSelector("#Password"), "123456");

        dr.findElement(By.cssSelector("#RememberMe")).click();
        dr.findElement(By.cssSelector(".button-1.login-button")).click();
    }


}

