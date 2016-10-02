import java.util.logging.*;

class Stepic_4_3_8 {

    static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
    }

    /*
     * Implement a method of tuning the parameters of logging
     * 1. Logger named "org.stepic.java.logging.ClassA" receives messages of all levels
     * 2. Logger named "org.stepic.java.logging.Class B" receives messages WARNING level and higher
     * 3. Messages that come from the lower to the level of loggers "org.stepic.java",
     * regardless of the severity of the messages are printed to the console in XML format (*)
     * and are not passed on to higher levels handlers "org.stepic", "org" and "."
     */

    static void configureLogging() {
        Logger loggerA = Logger.getLogger("org.stepic.java.logging.ClassA");
        Logger loggerB = Logger.getLogger("org.stepic.java.logging.ClassB");
        Logger logger = Logger.getLogger("org.stepic.java");

        loggerA.setLevel(Level.ALL);
        loggerB.setLevel(Level.WARNING);

        Handler consoleHandler = new ConsoleHandler();
        Formatter xmlFormatter = new XMLFormatter();
        consoleHandler.setLevel(Level.ALL);
        consoleHandler.setFormatter(xmlFormatter);
        logger.addHandler(consoleHandler);
        logger.setUseParentHandlers(false);
    }
}