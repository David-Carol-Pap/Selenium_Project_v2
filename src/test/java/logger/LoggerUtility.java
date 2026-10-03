package logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class LoggerUtility {
    //pathul unde voi salva fisierele de loguri
    private static final String suiteLogPath = "target/logs/suite/";

    // patul unde voi genre fisierul mare cu toate logurile
    private static final String regressionLogsPath = "target/logs/";


    private static final Logger logger = LogManager.getLogger();

    // vreau sa fac o metoda care ma asigura ca porneste un test

    public static synchronized void startTestcase(String testName){
        ThreadContext.put("threadName", testName);
        logger.info("==============Execution started: " + testName + "====================");
    }

    // metodat care se asigura ca a terminat un test

    public static synchronized  void endTestCase(String testName){
        logger.info("==============Execution finished : " + testName + "====================");
    }

    // metoda care se asigura ca adauga un entry ca si log

    public static synchronized void infoLog(String message){
        logger.info(Thread.currentThread().getName() + ":" + getCallInfo() + message);
    }
    // ca logheaza o erroare
    public static synchronized void errorLog(String message){
        logger.error(Thread.currentThread().getName() + ":" + getCallInfo() + message);
    }
    // metoda care sa scoate informatii despre executia curenta
    private static synchronized  String getCallInfo(){
       String className=Thread.currentThread().getStackTrace()[2].getClassName();
       String methodName=Thread.currentThread().getStackTrace()[2].getMethodName();
       return className + ", " + methodName + "==>";
    }
    // metoda care ia toate logurile si le pune intr-un singur fisier
public static void mergeLogFileIntoOne(){

    // create instance of directory
    File dir = new File(suiteLogPath);

    // Get list of all the files in form of String Array
    String[] fileNames = dir.list();

    try{
        // create object of PrintWriter for output file
        PrintWriter pw = new PrintWriter(regressionLogsPath + "RegressionLogs.log");


        // loop for reading the contents of all the files
        // in the directory GeeksForGeeks
        for (String fileName : fileNames) {
            System.out.println("Reading from " + fileName);
            // create instance of file from Name of
            // the file stored in string Array
            File f = new File(dir, fileName);
            // create object of BufferedReader
            BufferedReader br = new BufferedReader(new FileReader(f));
            pw.println("Contents of file " + fileName);
            // Read from current file
            String line = br.readLine();
            while (line != null) {
                // write to the output file
                pw.println(line);
                line = br.readLine();
            }
            pw.flush();
        }

    } catch (Exception e) {
        System.out.println(e.getMessage());
    }

}

}
;