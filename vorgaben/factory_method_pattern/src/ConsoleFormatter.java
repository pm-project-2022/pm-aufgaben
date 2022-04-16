import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

public class ConsoleFormatter extends SimpleFormatter {
    
    @Override
    public String format(LogRecord record) {
        return record.getMessage();
    }
}
