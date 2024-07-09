package edu.kit.informatik.programming.command.othercommands;

import edu.kit.informatik.programming.command.CommandResultType;
import edu.kit.informatik.programming.model.LiteratureSystem;
import edu.kit.informatik.programming.command.Command;
import edu.kit.informatik.programming.command.CommandResult;
import edu.kit.informatik.programming.command.IllegalCommandException;

import java.util.ArrayList;
import java.util.List;

public class ListInvalidPublicationsCommand implements Command {
    private static final String FIRST_COMMAND_ARGUMENT = "invalid";
    private static final String SECOND_COMMAND_ARGUMENT = "publications";

    @Override
    public CommandResult execute(LiteratureSystem model, String[] commandArguments) {
        if (!commandArguments[0].equals(FIRST_COMMAND_ARGUMENT) || !commandArguments[1].equals(SECOND_COMMAND_ARGUMENT)) {
            throw new IllegalCommandException();
        }

        StringBuilder result = new StringBuilder();
        List<String> ids = new ArrayList<>(model.getInvalidPublications().keySet());
        for (String id : ids) {
            result.append(id).append(System.lineSeparator());
        }

        result.setLength(result.length() - System.lineSeparator().length());
        return new CommandResult(CommandResultType.SUCCESS, result.toString());
    }

    @Override
    public int getNumberOfArguments() {
        return 2;
    }
}
