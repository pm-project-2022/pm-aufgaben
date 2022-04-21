package Logger;

import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

/**
 * An extended SimpleFormatter that formats the movement information of the hero
 */

public class RunAnimationConsoleFormatter extends SimpleFormatter {
    
    @Override
    public String format(LogRecord record) {
        return "--------------\n" + record.getMessage() + "\n";
    }
}
