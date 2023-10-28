import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Course;
import pages.Profile;
import pages.SignIn;
import pages.SignUp;

public class SmokeTests extends TestBase {

    @Test // NC-30
    public void E2ENewTeacherCreatesCoursePositiveTest() {
        // registration process:
        new SignUp(dr).signUpHeaderBtn.click();
        new SignUp(dr).registration("Albus Dumbledore", "wizard@hogwarts.net", "123456");
        sleep();
        new Profile(dr).logout();

        // check if registration was successful and user can log in after registration and logout:
        new SignIn(dr).openLoginForm().login("wizard@hogwarts.net", "123456");

        // adding a new course (filling form):
        Course course = new Course(dr);
        course.addCourse();

        // check that added course may be found in the common list by entering its name into the search bar
        dr.get("https://jere237.softr.app/");
        sleep();
        course.coursesHeaderLink.click();
        sleep();
        course.courseListLink.click();
        dr.findElement(By.id(":r0:")).sendKeys("Fancy Golf Resort Berlin Pankow");

        // assert by professor's name in the course card that must be displayed if the course is found:
        Assert.assertTrue(dr.getPageSource().contains("Albus Dumbledore"));

        // assert by description in the course card that must be displayed if the course is found:
        Assert.assertTrue(dr.getPageSource().contains("Large golf club"));

        // assert by amount of div-s of the course cards found - it has to be more than 1:
        Assert.assertTrue(dr.findElements
                (By.id("course-list")).size() > 0);
    }

    @Test // NC-31
    public void E2ESignedUpStudentEditsProfilePositiveTest() {
        new SignUp(dr).signUpHeaderBtn.click();
        new SignUp(dr).registrationAsStudent("Harry Potter", "potter@hg.com", "123456");
        sleep();

        Profile profile = new Profile(dr);

        // go to edit profile form:
        profile.myProfileBtnAsStudent.click();
        sleep();
        profile.myProfileLinkToAccount.click();

        // enter data into update profile form and save changes:
        profile.editStudentProfileInfo();

        // changing password:
        profile.setNewPassword("123456", "12345678");

        // check if changes saved and are valid to login:
        new Actions(dr).sendKeys(Keys.PAGE_UP).perform();
        sleep();
        profile.logout();
        new SignIn(dr).openLoginForm().login("wizard@hogwarts.net", "12345678");

        // check that role has been changed
        Assert.assertTrue(profile.addCourseBtn.isDisplayed());

    }


}
