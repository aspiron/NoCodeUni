package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUp extends PageBase {

    public SignUp(WebDriver dr) {
        super(dr);
    }

    @FindBy(xpath = "//span[contains(text(),'Sign up')]")
    public
    WebElement signUpHeaderBtn;

    @FindBy(xpath = "//div[@class='filter-option-inner-inner']v")
    public
    WebElement selectRoleMenu;

    @FindBy(id = "bs-select-1-0")
    WebElement roleTeacher;

    @FindBy(id = "bs-select-1-1")
    WebElement roleStudent;

    @FindBy(id = "sw-form-capture-full_name-input")
    public
    WebElement fullNameField;

    @FindBy(id = "sw-form-capture-email-input")
    public
    WebElement emailField;

    @FindBy(id = "sw-form-password-input")
    public
    WebElement passwordField;

    @FindBy(xpath = "//i[contains(@class,'show-password')]")
    public
    WebElement showPasswordIcon;

    @FindBy(css = ".checkmark")
    public
    WebElement agreeCheckbox;

    @FindBy(id = "sw-sign-up-submit-btn")
    public
    WebElement signUpFormBtn;

    @FindBy(id = "sw-go-to-sign-in-btn")
    public
    WebElement signInFormBtn;

    public SignUp openRegForm() {
        click(signUpHeaderBtn);
        return this;
    }

    public Profile registration(String fullName, String email, String password) {
        click(selectRoleMenu);
        click(roleTeacher);
        type(fullNameField, fullName);
        type(emailField, email);
        type(passwordField, password);
        click(showPasswordIcon);
        click(agreeCheckbox);
        click(signUpFormBtn);
        return new Profile(dr);
    }

    public Profile registrationAsStudent(String fullName, String email, String password) {
        click(selectRoleMenu);
        click(roleStudent);
        type(fullNameField, fullName);
        type(emailField, email);
        type(passwordField, password);
        click(showPasswordIcon);
        click(agreeCheckbox);
        click(signUpFormBtn);
        return new Profile(dr);
    }

    public void registrationWithNoCheckbox(String fullName, String email, String password) {
        click(selectRoleMenu);
        click(roleTeacher);
        type(fullNameField, fullName);
        type(emailField, email);
        type(passwordField, password);
        click(showPasswordIcon);
//        click(agreeCheckbox);
        click(signUpFormBtn);
    }
}
