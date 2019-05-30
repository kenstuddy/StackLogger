package com.stack_logger;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

 /**
  * Author: Ken Studdy
  * Date: May 19, 2019
  * Description: This class improves the usefulness of System.out and System.err print methods by logging the run-time stack. It displays the class name of where the print methods are called and also displays the date and time when the print methods are called. In addition, you can also output the logged information to a text file.
  * Instructions: To use this class, simply import it and put these two lines in your main method:
     System.setOut(new StackLogger(System.out, "information.txt", "out"));
     System.setErr(new StackLogger(System.err, "errors.txt", "err"));
  * If you do not wish to log to a file, simply pass null as the second parameter, like so:
     System.setOut(new StackLogger(System.out, null, "out"));
     System.setErr(new StackLogger(System.err, null, "err"));
  * In general for logging in Java, I recommend using a logging facade like SLF4J preferably with a logging backend like Log4j2 so that you can achieve the ability to change the logging backend with SLF4J as well as the ability to have high performance asynchronous logging from Log4j2. However, this StackLogger class works very well if all you need is simple logging.
 */
public final class StackLogger extends PrintStream {


    private final FileOutputStream writer;
    private final String outputType;
    public StackLogger(OutputStream out, String fileName, String type) throws FileNotFoundException {
        super(out);
        outputType = type;
        this.writer = fileName == null ? null : new FileOutputStream(new File(fileName), true);
    }

    @Override
    public void print(String message) {
        Throwable throwable = new Throwable();
        String name = "unknown";
        if (throwable.getStackTrace().length > 2) {
            name = throwable.getStackTrace()[2].getFileName().replace(".java", "");
        }
        log(name, message);
    }

    @Override
    public void print(boolean message) {
        Throwable throwable = new Throwable();
        String name = throwable.getStackTrace()[2].getFileName().replace(".java", "");
        log(name, message);
    }

    @Override
    public void print(int message) {
        Throwable throwable = new Throwable();
        String name = throwable.getStackTrace()[2].getFileName().replace(".java", "");
        log(name, message);
    }

    public void log(String className, Object message) {
        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy, h:mm:ss a");
        Calendar calendar = Calendar.getInstance();
        String time = dateFormat.format(calendar.getTime());
        String line = "";
        //If the class name is Throwable or the outputType is set to "err", that means it's an error.
        if (className.equals("Throwable") || outputType.equals("err")) {
            line = "[" + time + "] " + "[" + className + "] [ERROR] " + message;
        } else {
            line = "[" + time + "] " + "[" + className + "] [INFO] " + message;
        }
        super.print(line);
        if (writer != null) {
            try {
                //Write a byte array of the String of the log line plus the newline character to the log file.
                writer.write((line + '\n').getBytes(), 0, line.length() + 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
