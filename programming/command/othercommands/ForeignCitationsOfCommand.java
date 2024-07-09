package edu.kit.informatik.programming.command.othercommands;

import edu.kit.informatik.programming.model.LiteratureSystem;
import edu.kit.informatik.programming.command.Command;
import edu.kit.informatik.programming.command.CommandResult;
import edu.kit.informatik.programming.command.IllegalCommandException;

public class ForeignCitationsOfCommand implements Command {
    private static final String FIRST_COMMAND_ARGUMENT = "citations";
    private static final String SECOND_COMMAND_ARGUMENT = "of";

    @Override
    public CommandResult execute(LiteratureSystem model, String[] commandArguments) {
        if (!commandArguments[0].equals(FIRST_COMMAND_ARGUMENT) || !commandArguments[1].equals(SECOND_COMMAND_ARGUMENT)) {
            throw new IllegalCommandException();
        }

        return null;
    }

    @Override
    public int getNumberOfArguments() {
        return 4;
    }
}
