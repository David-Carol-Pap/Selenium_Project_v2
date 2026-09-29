package Pages;

import ObjectData.WebTableEntity;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;

public class WebTablePage extends CommonPage {
    private static final Duration WAIT_TIMEOUT =
            Duration.ofSeconds(10);

    public static final int ROWS_PER_PAGE = 10;

    private static final Pattern PAGE_PATTERN =
            Pattern.compile("Page\\s+(\\d+)\\s+of\\s+(\\d+)");

    private final By tableRowsLocator =
            By.xpath(
                    "//div[contains(@class,'web-tables-wrapper')]"
                            + "//table//tbody//tr"
            );

    @FindBy(id = "addNewRecordButton")
    private WebElement addTableButton;

    @FindBy(id = "firstName")
    private WebElement firstNameField;

    @FindBy(id = "lastName")
    private WebElement lastNameField;

    @FindBy(id = "userEmail")
    private WebElement emailField;

    @FindBy(id = "age")
    private WebElement ageField;

    @FindBy(id = "salary")
    private WebElement salaryField;

    @FindBy(id = "department")
    private WebElement departmentField;

    @FindBy(id = "submit")
    private WebElement submitButton;

    @FindBy(xpath =
            "//div[contains(@class,'pagination')]"
                    + "//div[normalize-space(.)"
                    + "='Page 1 of 1']")
    private WebElement pageInformation;

    @FindBy(xpath =
            "//div[contains(@class,'pagination')]"
                    + "//div[2]")
    private WebElement pageInformationContainer;

    @FindBy(xpath =
            "//div[contains(@class,'pagination')]"
                    + "//button[normalize-space(.)='Previous']")
    private WebElement previousButton;

    @FindBy(xpath =
            "//div[contains(@class,'pagination')]"
                    + "//button[normalize-space(.)='Next']")
    private WebElement nextButton;

    public WebTablePage(WebDriver driver) {
        super(driver);
    }

    /*
     * Form section
     */

    public void fillRegisterForm(WebTableEntity entity) {
        javaScriptMethods.forceClick(addTableButton);

        elementsMethods.clearAndFillElement(
                firstNameField,
                entity.getFirstName()
        );

        elementsMethods.clearAndFillElement(
                lastNameField,
                entity.getLastName()
        );

        elementsMethods.clearAndFillElement(
                emailField,
                entity.getEmail()
        );

        elementsMethods.clearAndFillElement(
                ageField,
                entity.getAge()
        );

        elementsMethods.clearAndFillElement(
                salaryField,
                entity.getSalary()
        );

        elementsMethods.clearAndFillElement(
                departmentField,
                entity.getDepartment()
        );

        elementsMethods.clickOnElements(submitButton);

        waitUntilFormIsClosed();
    }

    private void waitUntilFormIsClosed() {
        WebDriverWait wait =
                new WebDriverWait(driver, WAIT_TIMEOUT);

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        addTableButton
                )
        );
    }

    /*
     * Entity section
     */

    public void addEntity(WebTableEntity entity) {
        int pageBeforeInsert =
                getCurrentPageNumber();

        fillRegisterForm(entity);

        /*
         * DemoQA poate reseta pagina după submit.
         * Nu presupunem aici un număr concret de pagină.
         */
        waitUntilPaginationIsVisible();

        restorePage(pageBeforeInsert);
    }

    public void addEntities(List<WebTableEntity> entities) {
        int entityIndex = 0;

        while (entityIndex < entities.size()) {
            addEntity(entities.get(entityIndex));
            entityIndex++;

            int rowsOnCurrentPage =
                    getNumberOfRowsOnCurrentPage();

            boolean moreEntitiesExist =
                    entityIndex < entities.size();

            boolean currentPageIsFull =
                    rowsOnCurrentPage == ROWS_PER_PAGE;

            if (currentPageIsFull && moreEntitiesExist) {

                /*
                 * Dacă nu există încă o pagină, introducem
                 * un element pentru a o crea.
                 */
                if (!isNextButtonEnabled()) {
                    addEntity(entities.get(entityIndex));
                    entityIndex++;

                    waitUntilNextButtonIsEnabled();
                }

                goToNextPage();
            }
        }
    }

    /*
     * Pagination section
     */

    public String getPageInformation() {
        WebDriverWait wait =
                new WebDriverWait(driver, WAIT_TIMEOUT);

        wait.until(
                ExpectedConditions.visibilityOf(
                        pageInformationContainer
                )
        );

        return pageInformationContainer.getText()
                .replaceAll("\\s+", " ")
                .trim();
    }

    public int getCurrentPageNumber() {
        Matcher matcher = getPageMatcher();

        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }

        throw new IllegalStateException(
                "Current page could not be identified from: "
                        + getPageInformation()
        );
    }

    public int getTotalPages() {
        Matcher matcher = getPageMatcher();

        if (matcher.find()) {
            return Integer.parseInt(matcher.group(2));
        }

        throw new IllegalStateException(
                "Total pages could not be identified from: "
                        + getPageInformation()
        );
    }

    public int getExpectedNumberOfPages(int totalEntities) {
        return (int) Math.ceil(
                (double) totalEntities / ROWS_PER_PAGE
        );
    }

    public boolean isNextButtonEnabled() {
        return nextButton.isDisplayed()
                && nextButton.isEnabled();
    }

    private boolean isPreviousButtonEnabled() {
        return previousButton.isDisplayed()
                && previousButton.isEnabled();
    }

    public void waitUntilNextButtonIsEnabled() {
        WebDriverWait wait =
                new WebDriverWait(driver, WAIT_TIMEOUT);

        wait.until(driver ->
                isNextButtonEnabled()
        );
    }

    public void goToNextPage() {
        int currentPage =
                getCurrentPageNumber();

        WebDriverWait wait =
                new WebDriverWait(driver, WAIT_TIMEOUT);

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        nextButton
                )
        );

        javaScriptMethods.forceClick(nextButton);

        wait.until(driver ->
                getCurrentPageNumber() > currentPage
        );
    }

    private void goToPreviousPage() {
        int currentPage =
                getCurrentPageNumber();

        WebDriverWait wait =
                new WebDriverWait(driver, WAIT_TIMEOUT);

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        previousButton
                )
        );

        javaScriptMethods.forceClick(previousButton);

        wait.until(driver ->
                getCurrentPageNumber() < currentPage
        );
    }

    private void goToFirstPage() {
        while (isPreviousButtonEnabled()) {
            goToPreviousPage();
        }
    }

    private void restorePage(int targetPage) {
        goToFirstPage();

        while (getCurrentPageNumber() < targetPage) {
            goToNextPage();
        }

        if (getCurrentPageNumber() != targetPage) {
            throw new IllegalStateException(
                    "Could not restore page "
                            + targetPage
            );
        }
    }

    private void waitUntilPaginationIsVisible() {
        WebDriverWait wait =
                new WebDriverWait(driver, WAIT_TIMEOUT);

        wait.until(
                ExpectedConditions.visibilityOf(
                        pageInformationContainer
                )
        );
    }

    /*
     * Table section
     */

    public int getNumberOfRowsOnCurrentPage() {
        WebDriverWait wait =
                new WebDriverWait(driver, WAIT_TIMEOUT);

        wait.until(
                ExpectedConditions
                        .visibilityOfAllElementsLocatedBy(
                                tableRowsLocator
                        )
        );

        return driver.findElements(tableRowsLocator).size();
    }

    public WebElement getLastRowFromCurrentPage() {
        List<WebElement> rows =
                driver.findElements(tableRowsLocator);

        if (rows.isEmpty()) {
            throw new IllegalStateException(
                    "The current page contains no table rows."
            );
        }

        return rows.get(rows.size() - 1);
    }

    public boolean isEntityDisplayed(
            WebTableEntity entity
    ) {
        String rowText =
                getLastRowFromCurrentPage().getText();

        return rowText.contains(entity.getFirstName())
                && rowText.contains(entity.getLastName())
                && rowText.contains(entity.getEmail())
                && rowText.contains(entity.getAge())
                && rowText.contains(entity.getSalary())
                && rowText.contains(entity.getDepartment());
    }

    /*
     * Utility section
     */

    private Matcher getPageMatcher() {
        return PAGE_PATTERN.matcher(
                getPageInformation()
        );
    }
}
