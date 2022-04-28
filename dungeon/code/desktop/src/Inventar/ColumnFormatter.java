package Inventar;

import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

/**
 * A ColumnFormatter that formats outputs on console
 */

public class ColumnFormatter extends SimpleFormatter {

    public String format(LogRecord record){
        return "------------\nLogger: " + record.getLoggerName() + "\nLevel: " + record.getLevel() +
            "\nClass: " + record.getSourceClassName() + "\nMethod: " + record.getSourceMethodName() +
            "\nMessage: " + record.getMessage() + "\n------------\n";
    }

}
