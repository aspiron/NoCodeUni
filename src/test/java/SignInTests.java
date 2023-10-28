import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Profile;
import pages.SignIn;
import pages.SignUp;

public class SignInTests extends TestBase {
    @BeforeMethod
    public void preconditions() {
        SignIn login = new SignIn(dr);
        login.openLoginForm();
        sleep();
    }

    @Test //NC-16
    public void checkLoginFormFields() {
        SignIn login = new SignIn(dr);
        Assert.assertTrue(login.emailLogin.isDisplayed());
        Assert.assertTrue(login.passwordLogin.isDisplayed());
        Assert.assertTrue(login.signUpBtn.isDisplayed());
        Assert.assertTrue(login.signInBtn.isDisplayed());
        Assert.assertTrue(login.forgotPasswordLink.isDisplayed());
    }

    @Test // NC-17
    public void checkTypeOnLoginEmailField() {
        // check that typing in the field is possible
        SignIn login = new SignIn(dr);
        login.type(login.emailLogin, "qwe@gmail.com");

        Assert.assertTrue(login.emailLogin.isDisplayed());

        Actions a = new Actions(dr);
        a.click(login.emailLogin).keyDown(Keys.CONTROL).sendKeys("A").keyUp(Keys.CONTROL).perform();
        a.keyDown(Keys.CONTROL).sendKeys("C").keyUp(Keys.CONTROL).perform();
        a.click(login.passwordLogin).keyDown(Keys.CONTROL).sendKeys("V").keyUp(Keys.CONTROL).perform();
        sleep();
        sleep();
    }

    @Test // NC-18
    public void checkTypeOnLoginPasswordField() {
        // check that typing in the field is possible
        SignIn login = new SignIn(dr);
        login.type(login.passwordLogin, "123456");

        Assert.assertTrue(login.passwordLogin.isDisplayed());
        Assert.assertTrue(login.passwordLogin.isEnabled());
        System.out.println(login.passwordLogin.getAttribute("type"));
    }

    @Test //NC-19
    public void positiveLoginTest() {
        SignIn login = new SignIn(dr);
        login.login("malik@example.com", "123456");

        Profile profile = new Profile(dr);
        Assert.assertTrue(profile.myProfileBtnAsTeacher.isDisplayed());
        Assert.assertTrue(profile.addCourseBtn.isDisplayed());
    }

    @Test //NC-20
    public void negativeLoginTestInvalidPassword() {
        SignIn login = new SignIn(dr);
        login.login("malik@example.com", "1");
        sleep();
        Assert.assertTrue(login.signInForm.getText().contains("Invalid email or password"));
    }

    @Test //NC-21
    public void negativeLoginTestInvalidEmail() {
        SignIn login = new SignIn(dr);
        login.login("malik", "123456");
        Assert.assertTrue(login.signInForm.getText().contains("Invalid email or password"));
    }

    @Test //NC-24
    public void negativeLoginTestEmptyPassword() {
        SignIn login = new SignIn(dr);
        login.type(login.emailLogin, "malik@example.com");
        login.click(login.signInBtn);
        sleep();

        Assert.assertTrue(login.signInForm.getText().contains("Invalid email or password"));
        Assert.assertTrue(dr.getPageSource().contains("sw-input-invalid"));
    }

    @Test //NC-22
    public void negativeLoginTestEmptyEmail() {
        SignIn login = new SignIn(dr);
        login.type(login.passwordLogin, "123456");
        login.click(login.signInBtn);
        sleep();

        Assert.assertTrue(login.signInForm.getText().contains("Invalid email or password"));
        Assert.assertTrue(dr.getPageSource().contains("sw-input-invalid"));
    }

    @Test //NC-23
    public void negativeLoginTestNotSignetUp() {
        SignIn login = new SignIn(dr);
        login.login("123456qweqweqwe@inbox.net", "123456");
        sleep();

        Assert.assertTrue(login.signInForm.getText().contains("Invalid email or password"));
    }

    @Test //NC-25
    public void positiveLoginAndLogoutTest() {
        SignIn login = new SignIn(dr);
        login.login("malik@example.com", "123456");

        Profile profile = new Profile(dr);
        Assert.assertTrue(profile.myProfileBtnAsTeacher.isDisplayed());
        Assert.assertTrue(profile.addCourseBtn.isDisplayed());

        profile.logout();
        sleep();

        Assert.assertTrue(dr.getCurrentUrl().contains("https://jere237.softr.app/sign-in"));
    }

    @Test //NC-26
    public void checkEmailPlaceholderIsDisplayed() {
        String emailPlaceholder = dr.findElement(By.id("sw-form-capture-email-input")).getAttribute("placeholder");

        Assert.assertEquals
                (emailPlaceholder, "Email");

        System.out.println("Placeholder in this field is: " + emailPlaceholder);
    }

    @Test //NC-27
    public void checkPasswordPlaceholderIsDisplayed() {
        String passwordPlaceholder = dr.findElement(By.id("sw-form-password-input")).getAttribute("placeholder");

        Assert.assertEquals
                (passwordPlaceholder, "Password");

        System.out.println("Placeholder in this field is: " + passwordPlaceholder);
    }

    @Test //NC-28
    public void checkForgotPasswordLink() {
        SignIn login = new SignIn(dr);
        login.forgotPasswordLink.click();
        Assert.assertTrue(dr.getPageSource().contains("2023 NoCode University. All rights reserved. Illustrations"));

        Assert.assertTrue(dr.getPageSource().contains("If you forgot your password\n" +
                "Here's how to reset your password and regain access to your account"));
    }

    @Test //NC-28
    public void checkSignUpBtn() {
        SignIn login = new SignIn(dr);
        Actions actions = new Actions(dr);
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        sleep();
        login.signUpBtn.click();

        Assert.assertTrue(dr.getCurrentUrl().contains("https://jere237.softr.app/sign-up"));

        SignUp reg = new SignUp(dr);
        Assert.assertTrue(reg.agreeCheckbox.isDisplayed());
    }


}
