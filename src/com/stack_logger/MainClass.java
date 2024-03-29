package com.stack_logger;

 /*
  * Author: Ken Studdy
  * Date: May 19, 2019
  * Description: This class is used as an example of the functionality of the StackLogger class. The StackLogger class can be added to any Java project using Java 8 or newer.
  */
public class MainClass {
    public static void main(String[] args) {
        try {
            StackLogger infoLogger = new StackLogger(System.out, "information.txt", "out");
            System.setOut(infoLogger);
            StackLogger errorLogger = new StackLogger(System.err, "errors.txt", "err");
            System.setErr(errorLogger);
            System.err.println("This is an example of an error when using the Stack Logger.");
            System.err.println("This is an example of another error when using the Stack Logger.");
            System.out.println("This is an example of information when using the Stack Logger.");
            System.out.println("This is an example of more information when using the Stack Logger.");
            infoLogger.close();
            errorLogger.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
