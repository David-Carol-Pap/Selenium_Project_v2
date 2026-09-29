package Pages;

import HelperMethods.ElementsMethods;
import HelperMethods.JavaScriptMethods;
import ObjectData.PracticeFormObject;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class PracticeFormePage extends CommonPage {

    @FindBy(id = "firstName")
    private WebElement firstNameElement;

    @FindBy(id = "lastName")
    private WebElement lastNameElement;

    @FindBy(id = "userEmail")
    private WebElement emailElement;

    @FindBy(css = "input[placeholder='Mobile Number']")
    private WebElement mobilePhoneElement;

    @FindBy(xpath = "//label[@for='gender-radio-1']")
    private WebElement maleGenderElement;

    @FindBy(xpath = "//label[@for='gender-radio-2']")
    private WebElement femaleGenderElement;

    @FindBy(xpath = "//label[@for='gender-radio-3']")
    private WebElement otherGenderElement;

    @FindBy(id = "currentAddress")
    private WebElement adressElement;

    @FindBy(id = "react-select-3-input")
    private WebElement stateElement;

    @FindBy(id = "react-select-4-input")
    private WebElement cityElement;

    @FindBy(xpath = "//label[@for='hobbies-checkbox-3']")
    private WebElement musicHobbyElement;

    @FindBy(xpath = "//label[@for='hobbies-checkbox-1']")
    private WebElement sportsHobbyElement;

    @FindBy(xpath = "//label[@for='hobbies-checkbox-2']")
    private WebElement readingHobbyElement;

//    @FindBy(xpath = "//div[@id='subjectsContainer']")
//    WebElement subjectElement;

    @FindBy(id = "subjectsInput")
    private WebElement subjectElement;

    @FindBy(id = "dateOfBirthInput")
    private WebElement dateOfBirthElement;

    @FindBy(id = "uploadPicture")
    private WebElement uploadPictures;

    @FindBy(xpath = "//select[@class='react-datepicker__month-select']")
    private WebElement monthSelectorElement;

    @FindBy(xpath = "//select[@class='react-datepicker__month-select']//option")
    private List<WebElement> monthsElementsList;

    @FindBy(xpath = "//select[@class='react-datepicker__year-select']//option")
    private List<WebElement> yearsElementsList;

    @FindBy(xpath = "//div[@class='react-datepicker__month']//div")
    private List<WebElement> daysElementsList;

    public PracticeFormePage(WebDriver driver) {
        super(driver);
    }

    public void completeFirstRegion(PracticeFormObject practiceFormObject) {
        elementsMethods.clearAndFillElement(firstNameElement, practiceFormObject.getFirstName());
        elementsMethods.clearAndFillElement(lastNameElement, practiceFormObject.getLastName());
        elementsMethods.clearAndFillElement(emailElement, practiceFormObject.getEmail());
        elementsMethods.clearAndFillElement(adressElement, practiceFormObject.getAddress());
        elementsMethods.clearAndFillElement(mobilePhoneElement, practiceFormObject.getMobile());
    }

    public void commpleteGender(PracticeFormObject practiceFormObject) {
        switch (practiceFormObject.getGender()) {
            case "Male":
                elementsMethods.clickOnElements(maleGenderElement);
                break;
            case "Female":
                elementsMethods.clickOnElements(femaleGenderElement);
                break;
            case "Other":
                elementsMethods.clickOnElements(otherGenderElement);
                break;
        }
        javaScriptMethods.javaScriptScrollPage(0, 400);
    }

    public void completeSubject(String subject) {
        elementsMethods.clickOnElements(subjectElement);
        elementsMethods.fillWithActions(subjectElement, subject);
    }

    public void completeSubjectWithList(PracticeFormObject practiceFormObject) {
        elementsMethods.clickOnElements(subjectElement);
        elementsMethods.fillMultipleValues(subjectElement, practiceFormObject.getSubjects());

    }

    public void completeHobbys(PracticeFormObject practiceFormObject) {
        List<WebElement> hobiesElement = new ArrayList<>();
        hobiesElement.add(sportsHobbyElement);
        hobiesElement.add(musicHobbyElement);
        hobiesElement.add(readingHobbyElement);
        elementsMethods.clickMultipleValues(hobiesElement, practiceFormObject.getHobbies());
    }

    public void setDateOfBirth(String month, String year, String day) {
        elementsMethods.clickOnElements(dateOfBirthElement);
        elementsMethods.clickOnElements(monthSelectorElement);
        elementsMethods.selectElementFromListByText(monthsElementsList, month);
        elementsMethods.selectElementFromListByText(yearsElementsList, year);
        elementsMethods.selectElementFromListByText(daysElementsList, day);
    }

    public void takeTheValueSetForBirthDay() {
        elementsMethods.selectTextFromAnElement(dateOfBirthElement);
    }

    public void uploadPictures() {
        elementsMethods.uploadPictures(uploadPictures);
    }

    public void selectStateAndCity(PracticeFormObject practiceFormObject) {
         javaScriptMethods.forceClick(stateElement);
   //     elementsMethods.waitVisibilityElement(stateElement);
    //    elementsMethods.clickOnElements(stateElement);
        elementsMethods.clearAndFillElement(stateElement, practiceFormObject.getState());
        stateElement.sendKeys(Keys.ENTER);
        javaScriptMethods.forceClick(cityElement);
     //   elementsMethods.clickOnElements(cityElement);
        elementsMethods.clearAndFillElement(cityElement, practiceFormObject.getCity());
        cityElement.sendKeys(Keys.ENTER);
    }
}







