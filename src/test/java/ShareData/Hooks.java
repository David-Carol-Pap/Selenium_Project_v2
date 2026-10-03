package ShareData;

import logger.LoggerUtility;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

public class Hooks extends ShareData {

    public String testName;

    @BeforeMethod(alwaysRun = true)
    public void prepareEnvironment() {
        testName =
                this.getClass().getSimpleName();

        LoggerUtility.startTestcase(testName);

        prepareBrowser();
    }

    @AfterMethod(alwaysRun = true)
    public void clearEnvironment(
            ITestResult result
    ) {
        try {
            if (result.getStatus()
                    == ITestResult.FAILURE
                    && result.getThrowable() != null) {

                LoggerUtility.errorLog(
                        result.getThrowable()
                                .getMessage()
                );
            }
        } finally {
            clearBrowser();
            LoggerUtility.endTestCase(testName);
        }
    }

    @AfterSuite(alwaysRun = true)
    public void finalizeLogFile() {
        LoggerUtility.mergeLogFileIntoOne();
    }
}
