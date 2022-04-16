import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

public class FileFormatter extends SimpleFormatter{
    @Override
    public String format(LogRecord record){
        return record.getLoggerName() + "," + record.getLevel() + "," + record.getSourceMethodName() + "," + record.getSourceClassName() + "," + record.getMessage() + "\n";
    }  
}
