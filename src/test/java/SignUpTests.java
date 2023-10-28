import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Profile;
import pages.SignIn;
import pages.SignUp;

public class SignUpTests extends TestBase {

    @BeforeMethod
    public void preconditions() {
        SignUp reg = new SignUp(dr);
        reg.openRegForm();
        sleep();
    }

    @Test // NC-2
    public void checkRegFormFields() {
        // check if all the fields of the registration form are displayed on the page and avaliable to interact with
        SignUp reg = new SignUp(dr);
        Assert.assertTrue(reg.selectRoleMenu.isDisplayed());
        Assert.assertTrue(reg.fullNameField.isDisplayed());
        Assert.assertTrue(reg.emailField.isDisplayed());
        Assert.assertTrue(reg.passwordField.isDisplayed());
        Assert.assertTrue(reg.agreeCheckbox.isDisplayed());
        Assert.assertTrue(reg.signUpFormBtn.isDisplayed());
        Assert.assertTrue(reg.signInFormBtn.isDisplayed());
    }

    @Test // NC-3
    public void checkTypeOnFullNameField(){
        // check that typing in the field is possible
        SignUp reg = new SignUp(dr);
        reg.type(reg.fullNameField, "JimBim");

        Assert.assertTrue(reg.fullNameField.isDisplayed());

        Actions a = new Actions(dr);
        a.doubleClick(reg.fullNameField).keyDown(Keys.CONTROL).sendKeys("C").keyUp(Keys.CONTROL).perform();
        a.click(reg.emailField).keyDown(Keys.CONTROL).sendKeys("V").keyUp(Keys.CONTROL).perform();
        sleep();
        sleep();
    }

    @Test // NC-4
    public void checkTypeOnPasswordField() {
        // check that typing in the field is possible
        SignUp reg = new SignUp(dr);
        reg.type(reg.passwordField, "1234qweQ!");
        Assert.assertTrue(reg.passwordField.isDisplayed());
        reg.click(reg.showPasswordIcon);
        sleep();
    }

    @Test // NC-5
    public void regPositiveTest() {
        new SignUp(dr).registration("Alex Piron", "qwe@gmail.com", "123456");
        sleep();

        Profile profile = new Profile(dr);
        Assert.assertTrue(profile.myProfileBtnAsTeacher.isDisplayed());
        Assert.assertTrue(profile.add_A_CourseBtn.isDisplayed());
        Assert.assertTrue(profile.addCourseBtn.isDisplayed());
    }

    @Test // NC-6
    public void regNegativeInvalidPasswordTest() {
        SignUp reg = new SignUp(dr);
        reg.registration("Alex Piron", "qwe@gmail.com", "1");
        sleep();

        Assert.assertTrue(dr.getPageSource().contains("Please make sure there are no empty required fields."));
        Assert.assertTrue(dr.getPageSource().contains("Password must contain at least 6 characters"));
    }

    @Test // NC-7
    public void regNegativeInvalidEmailTest() {
        SignUp reg = new SignUp(dr);
        reg.registration("Alex Piron", "qwe", "123456");
        sleep();

        Assert.assertTrue(dr.getPageSource().contains("Please make sure there are no empty required fields."));
    }

    @Test // NC-10
    public void regPositiveSignInAfterSignUpTest() {
        new SignUp(dr).registration("Alex Piron", "qwe@gmail.com", "123456");
        new Profile(dr).logout();

        SignIn login = new SignIn(dr);
        login.type(login.emailLogin, "qwe@gmail.com");
        login.type(login.passwordLogin, "123456");
        login.signInBtn.click();

        Assert.assertEquals(dr.getCurrentUrl().toString(), "https://jere237.softr.app/sign-in");
    }

    @Test // NC-11
    public void checkTypeOnEmailField() {
        SignUp reg = new SignUp(dr);
        reg.type(reg.emailField, "qwe@gmail.com");
        Assert.assertTrue(reg.emailField.isDisplayed());
    }

    @Test // NC-12
    public void regNegativeNoCheckboxTickedTest() {
        new SignUp(dr).registrationWithNoCheckbox("Alex Piron", "qwe@gmail.com", "123456");
        sleep();

        Assert.assertTrue(dr.getPageSource().contains("Please make sure there are no empty required fields."));
    }

}
