package edu.kit.informatik.programming.command.othercommands;

import edu.kit.informatik.programming.model.LiteratureSystem;
import edu.kit.informatik.programming.command.Command;
import edu.kit.informatik.programming.command.CommandResult;
import edu.kit.informatik.programming.command.IllegalCommandException;

public class PrintBibliographyCommand implements Command {
    private static final String COMMAND_ARGUMENT = "bibliography";

    @Override
    public CommandResult execute(LiteratureSystem model, String[] commandArguments) {
        if (!commandArguments[0].equals(COMMAND_ARGUMENT)) {
            throw new IllegalCommandException();
        }

        return null;
    }

    @Override
    public int getNumberOfArguments() {
        return 2;
    }
}
