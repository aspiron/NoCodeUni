package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class Course extends PageBase {
    public Course(WebDriver dr) {
        super(dr);
    }

    @FindBy(id = "course-documents-form-CourseName--1078269106")
    WebElement courseNameAddCourse;

    @FindBy(id = "course-documents-form-Faculty-1389247778")
    WebElement facultyMenuAddCourse;

    @FindBy(id = "course-documents-form-Description-1634506682")
    WebElement descriptionAddCourse;

    @FindBy(id = "course-documents-form-CoverPhoto-1704715303")
    WebElement coverPhotoAddCourse;

    @FindBy(id = "course-documents-form-Startdate-831849774")
    public
    WebElement startDateAddCourse;

    @FindBy(id = "course-documents-form-Enddate-1271684309")
    WebElement endDateAddCourse;

    @FindBy(css = "button.MuiButtonBase-root:nth-child(8)")
    public WebElement addBtnAddCourse;

    @FindBy(css = "a.MuiButton-text:nth-child(2) > span:nth-child(1)")
    public WebElement coursesHeaderLink;

    @FindBy(xpath = "//span[contains(text(),'Course list')]")
    public WebElement courseListLink;

    @FindBy(id = "course-list")
    public WebElement courseListArea;


    public Course enterDataToCreateCourse(String courseName, String facultySelectValue, String courseDesc) {
        type(courseNameAddCourse, courseName);
        new Actions(dr).click(facultyMenuAddCourse)
                .sendKeys(Keys.TAB, Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER)
                .perform();
        type(descriptionAddCourse, courseDesc);
        return this;
    }

    public Course uploadPhoto() {
        new Course(dr).coverPhotoAddCourse.sendKeys("D:\\EDU\\QA\\QA36\\POM_NoCodeUni_Piron\\src\\main\\resources\\qwe.jpg");
//        new Course(dr).coverPhotoAddCourse.sendKeys("https://berlin.vandervalk.de/inc/hotels/76/packages/4826/mobile/carousel_packages_1024x768_canstockphoto8286698.jpg");
        dr.switchTo().activeElement().submit();
        return this;
    }

    public void setStartCourseDate(int pickADay) {
        click(startDateAddCourse);

        Actions a = new Actions(dr);
        int timesOfPressingTab = pickADay;
        for (int i = 0; i < timesOfPressingTab; i++) {
            a.sendKeys(Keys.TAB).perform();
        }
        a.sendKeys(Keys.ENTER).perform();
    }

    public void setEndCourseDate(int pickADay) {
        click(endDateAddCourse);

        Actions a = new Actions(dr);
        int timesOfPressingTab = pickADay;
        for (int i = 0; i < timesOfPressingTab; i++) {
            a.sendKeys(Keys.TAB).perform();
        }
        a.sendKeys(Keys.ENTER).perform();

    }


    public void addCourse(){
        new Profile(dr).addCourseBtn.click();
        enterDataToCreateCourse(
                "Fancy Golf Resort Berlin Pankow",
                "Arts, Design & Architecture",
                "Large golf club: 4 courses, 36 holes, driving range, restaurant and academy where lessons are held.");

        uploadPhoto();

        new Actions(dr).sendKeys(Keys.PAGE_DOWN).perform();
        sleep();
        sleep();

        setStartCourseDate(10);
        sleep();
        setEndCourseDate(20);

        addBtnAddCourse.click();
        sleep();
    }



}
