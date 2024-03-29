# StackLogger
This Java project improves the usefulness of System.out and System.err print methods by logging the run-time stack.

# Requirements
* Java 8 or newer
* IntelliJ IDEA 2019.1 or newer (optional)

# How to Install
To use this class, simply import it (if necessary) and put these two lines in your main method:

```java
StackLogger infoLogger = new StackLogger(System.out, "information.txt", "out");
System.setOut(infoLogger);
StackLogger errorLogger = new StackLogger(System.err, "errors.txt", "err");
System.setErr(errorLogger);
```

If you do not wish to log to a file, simply pass null as the second parameter, like so:

```java
StackLogger infoLogger = new StackLogger(System.out, null, "out");
System.setOut(infoLogger);
StackLogger errorLogger = new StackLogger(System.err, null, "err");
System.setErr(errorLogger);
```

When done using the logger (like when exiting your application), you can simply call the close method:
```java
infoLogger.close();
errorLogger.close();
```

You can also run the included Main class for an example of the Stack Logger.

# Example
This is an example of what the output looks like from the included Main class:
![](example/main.png)

