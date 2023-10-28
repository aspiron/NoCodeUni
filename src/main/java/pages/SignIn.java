package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignIn extends PageBase {
    public SignIn(WebDriver dr) {
        super(dr);
    }

    @FindBy(id = "sw-form-capture-email-input")
    public
    WebElement emailLogin;

    @FindBy(id = "sw-form-password-input")
    public
    WebElement passwordLogin;

    @FindBy(id = "sw-sign-in-submit-btn")
    public
    WebElement signInBtn;

    @FindBy(id = "sw-go-to-sign-up-btn")
    public
    WebElement signUpBtn;

    @FindBy(xpath = "//span[contains(text(),'Sign in')]")
    public
    WebElement signInHeaderBtn;

    @FindBy(xpath = "//a[contains(text(),'Forgot password')]")
    public
    WebElement forgotPasswordLink;

    @FindBy(id = "sw-form-password-input")
    public
    WebElement showPasswordIcon;

    @FindBy(id = "signin")
    public WebElement signInForm;


    public SignIn openLoginForm() {
        click(signInHeaderBtn);
        return this;
    }

    public Profile login(String email, String password) {
        type(emailLogin, email);
        type(passwordLogin, password);
        click(signInBtn);
        return new Profile(dr);
    }


}
