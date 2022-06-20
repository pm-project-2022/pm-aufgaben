package multithreading;

import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

public class LoggerFormatter extends SimpleFormatter {

    @Override
    public String format(LogRecord record) {
        return "------------\n" + "Logger: " + record.getLoggerName() + "\n" + "Level: " + record.getLevel() + "\n"
                + "Method: " + record.getSourceMethodName() + "\n" + "Message: " + record.getMessage() + "\n"
                + "------------\n\n";
    }
}
