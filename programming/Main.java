package edu.kit.informatik.programming;

import edu.kit.informatik.programming.command.CommandHandler;
import edu.kit.informatik.programming.model.LiteratureSystem;

/**
 * This class is the entry point of the program.
 *
 * @author Programmieren-Team
 * @author uymkb
 */
public final class Main {
    private static final String UTILITY_CLASS_CONSTRUCTOR_MESSAGE = "Utility classes cannot be instantiated";

    private Main() {
        throw new UnsupportedOperationException(UTILITY_CLASS_CONSTRUCTOR_MESSAGE);
    }

    /**
     * Starts the program.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LiteratureSystem literatureSystem = new LiteratureSystem();
        CommandHandler commandHandler = new CommandHandler(literatureSystem);
        commandHandler.handleUserInput();
    }
}