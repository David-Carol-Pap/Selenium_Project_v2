package Tests;

import ObjectData.PracticeFormObject;
import Pages.CommonPage;
import Pages.HomePage;
import Pages.PracticeFormePage;
import PropertyUtility.PropertyUtility;
import ShareData.Hooks;
import org.testng.annotations.Test;

public class PracticeFormTest extends Hooks {
    public HomePage homePage;
    public CommonPage commonPage;
    public PracticeFormePage practiceFormePage;


    @Test
    public void Forms() {
        PropertyUtility propertyUtility= new PropertyUtility("Form_PracticeForm");
        PracticeFormObject practiceFormObject=new PracticeFormObject(propertyUtility.getData());
        homePage = new HomePage(getDriver());
        commonPage = new CommonPage(getDriver());
        practiceFormePage = new PracticeFormePage(getDriver());
        homePage.GoToDesiredMeniu("Forms");
        commonPage.GoToDesiredSubMeniu("Practice Form");

        practiceFormePage.completeFirstRegion(practiceFormObject);
        practiceFormePage.commpleteGender(practiceFormObject);

        practiceFormePage.completeSubjectWithList(practiceFormObject);

        practiceFormePage.completeHobbys(practiceFormObject);

        practiceFormePage.setDateOfBirth("July", "2023", "22");

     //   practiceFormePage.selectStateAndCity("NCR", "Delhi");// !!!! Nu e ok, nu e lista cu toate valorile e indentificat doar elementul
        practiceFormePage.selectStateAndCity(practiceFormObject);

    }
}

