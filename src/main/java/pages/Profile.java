package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class Profile extends PageBase{

    public Profile(WebDriver dr) {
        super(dr);
    }


    @FindBy(css = ".MuiAvatar-root")
    public
    WebElement myProfileBtnAsTeacher;

    @FindBy(xpath = "/html/body/div[1]/div[1]/header/div/div/div[2]/button/div")
    public
    WebElement myProfileBtnAsStudent;

    @FindBy(xpath = "//span[contains(text(), 'Add a course')]")
    public
    WebElement add_A_CourseBtn;

    @FindBy(xpath = "//span[contains(text(),'Add course')]")
    public
    WebElement addCourseBtn;

    @FindBy(xpath = "//span[contains(text(),'Sign Out')]")
    WebElement signOutBtn;

    @FindBy(xpath = "//span[contains(text(),'My Profile')]")
    public WebElement myProfileLinkToAccount;

    @FindBy(id = "sw-form-capture-Role")
    WebElement roleTuner;

    @FindBy(id = "sw-form-capture-full_name-input")
    WebElement editFullNameField;

    @FindBy(id = "sw-form-capture-About")
    WebElement editAboutField;

    @FindBy(id = "sw-update-profile-btn")
    WebElement updateProfileBtn;

    @FindBy(id = "sw-form-password-input")
    WebElement oldPasswordField;

    @FindBy(id = "sw-new-password-input")
    WebElement newPasswordField;

    @FindBy(id = "sw-change-password-btn")
    WebElement changePasswordBtn;


    public void logout(){
        click(myProfileBtnAsTeacher);
        click(signOutBtn);
    }

    public void editStudentProfileInfo(){
        Select select = new Select(roleTuner);
        select.selectByIndex(1);
        type(editFullNameField, "NewName");
        type(dr.findElement(By.id("sw-form-capture-email-input")), "wizard@hogwarts.net"); // email field is typeable

        click(dr.findElement(By.xpath("//*[@id=\"user-profile\"]/div/div/div")));
        type(editAboutField, "some info about me");

        new Actions(dr).sendKeys(Keys.PAGE_DOWN).perform();

        sleep();
        sleep();
        click(updateProfileBtn);
    }

    public void setNewPassword(String oldPass, String newPass){
        type(oldPasswordField, oldPass);
        type(newPasswordField, newPass);
        click(changePasswordBtn);
    }



}
