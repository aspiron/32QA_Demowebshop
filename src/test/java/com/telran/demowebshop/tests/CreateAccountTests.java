package com.telran.demowebshop.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateAccountTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (!isRegisterLinkPresent()) {
            pressLogoutButton();
        }
    }

    int i = (int) (System.currentTimeMillis() / 1000) % 3600;

    @Test
    public void createAccountPositiveTest() {
        Assert.assertTrue(isRegisterLinkPresent());
        fillRegisterFormWithTimeMillis();
        Assert.assertFalse(isRegisterLinkPresent());
    }

    @Test
    public void createAccountNegativeTestEmailExists() {
        Assert.assertTrue(isRegisterLinkPresent());
        click(By.cssSelector(".ico-register"));
        click(By.cssSelector("#gender-male"));
        type(By.cssSelector("#FirstName"), "Alex" + i);
        type(By.cssSelector("#LastName"), "Piron" + i);
        type(By.cssSelector("#Email"), "ap@gmail.com");
        type(By.cssSelector("#Password"), "123456");
        type(By.cssSelector("#ConfirmPassword"), "123456");

        click(By.cssSelector("#register-button"));
        Assert.assertFalse(!isRegisterLinkPresent());
        Assert.assertTrue(isElementPresent(By.xpath("//li[text()='The specified email already exists']")));
    }

    @Test
    public void createAccountNegativeTestShortPassword() {
        Assert.assertTrue(isRegisterLinkPresent());
        click(By.cssSelector(".ico-register"));
        click(By.cssSelector("#gender-male"));
        type(By.cssSelector("#FirstName"), "Alex");
        type(By.cssSelector("#LastName"), "Piron");

        type(By.cssSelector("#Email"), "ap" + i + "@gmail.com");
        type(By.cssSelector("#Password"), "12345");
        type(By.cssSelector("#ConfirmPassword"), "12345");

        click(By.cssSelector("#register-button"));
        Assert.assertFalse(!isRegisterLinkPresent());
        Assert.assertTrue(isElementPresent(By.xpath("//*[text()='The password should have at least 6 characters.']")));
    }

    @Test
    public void createAccountNegativeTestWithoutLastName() {
        Assert.assertTrue(isRegisterLinkPresent());
        click(By.cssSelector(".ico-register"));
        click(By.cssSelector("#gender-male"));
        type(By.cssSelector("#FirstName"), "Alex");
        type(By.cssSelector("#LastName"), "");

        type(By.cssSelector("#Email"), "ap" + i + "@gmail.com");
        type(By.cssSelector("#Password"), "123456");
        type(By.cssSelector("#ConfirmPassword"), "123456");

        click(By.cssSelector("#register-button"));
        Assert.assertFalse(!isRegisterLinkPresent());
        Assert.assertTrue(isElementPresent(By.xpath("//*[text()='Last name is required.']")));
    }

    public boolean isRegisterLinkPresent() {
        return dr.findElements(By.cssSelector(".ico-register")).size() > 0;
    }

    public void fillRegisterFormWithTimeMillis() {
        click(By.cssSelector(".ico-register"));
        click(By.cssSelector("#gender-male"));
        type(By.cssSelector("#FirstName"), "Alex");
        type(By.cssSelector("#LastName"), "Piron");
        type(By.cssSelector("#Email"), "ap123" + i + "@gmail.com");
        type(By.cssSelector("#Password"), "123456");
        type(By.cssSelector("#ConfirmPassword"), "123456");

        click(By.cssSelector("#register-button"));

    }


}
