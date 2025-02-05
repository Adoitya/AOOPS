import java.util.*;
import java.util.List;

// LogLevel enum
enum LogLevel {
    INFO, DEBUG, ERROR
}

// Command interface
interface Command {
    void execute(String message);
}

// LogCommand class
class LogCommand implements Command {
    private LogLevel level;
    private String message;

    public LogCommand(LogLevel level, String message) {
        this.level = level;
        this.message = message;
    }

    @Override
    public void execute(String message) {
        // Handle the logging request
        // ... (implementation using handlers)
    }
}

// Abstract LogHandler class
abstract class LogHandler {
    private LogHandler nextHandler;

    public void setNextHandler(LogHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    protected abstract void handle(LogLevel level, String message);

    public void process(LogLevel level, String message) {
        if (canHandle(level)) {
            handle(level, message);
        } else if (nextHandler != null) {
            nextHandler.process(level, message);
        }
    }

    protected abstract boolean canHandle(LogLevel level);
}

// InfoHandler class
class InfoHandler extends LogHandler {
    @Override
    protected void handle(LogLevel level, String message) {
        if (level == LogLevel.INFO) {
            System.out.println("INFO: " + message);
        }
    }

    @Override
    protected boolean canHandle(LogLevel level) {
        return level == LogLevel.INFO;
    }
}

// DebugHandler class
class DebugHandler extends LogHandler {
    @Override
    protected void handle(LogLevel level, String message) {
        if (level == LogLevel.DEBUG) {
            System.out.println("DEBUG: " + message);
        }
    }

    @Override
    protected boolean canHandle(LogLevel level) {
        return level == LogLevel.DEBUG;
    }
}

// ErrorHandler class
class ErrorHandler extends LogHandler {
    @Override
    protected void handle(LogLevel level, String message) {
        if (level == LogLevel.ERROR) {
            System.out.println("ERROR: " + message);
        }
    }

    @Override
    protected boolean canHandle(LogLevel level) {
        return level == LogLevel.ERROR;
    }
}

// Logger class
class Logger {
    private List<Command> commands;

    public Logger() {
        commands = new ArrayList<>();
    }

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void processLogs() {
        for (Command command : commands) {
            command.execute();
        }
    }
}

// Client class
class Client {
    public static void main(String[] args) {
        // Create handlers
        InfoHandler infoHandler = new InfoHandler();
        DebugHandler debugHandler = new DebugHandler();
        ErrorHandler errorHandler = new ErrorHandler();

        // Chain handlers
        infoHandler.setNextHandler(debugHandler);
        debugHandler.setNextHandler(errorHandler);

        // Create commands
        Command infoCommand = new LogCommand(LogLevel.INFO, "This is an info message.");
        Command debugCommand = new LogCommand(LogLevel.DEBUG, "This is a debug message.");
        Command errorCommand = new LogCommand(LogLevel.ERROR, "This is an error message.");

        // Create logger and add commands
        Logger logger = new Logger();
        logger.addCommand(infoCommand);
        logger.addCommand(debugCommand);
        logger.addCommand(errorCommand);

        // Process logs
        logger.processLogs();
    }
}