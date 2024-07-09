package edu.kit.informatik.programming.command.othercommands;

import edu.kit.informatik.programming.command.CommandResultType;
import edu.kit.informatik.programming.model.LiteratureSystem;
import edu.kit.informatik.programming.command.Command;
import edu.kit.informatik.programming.command.CommandResult;
import edu.kit.informatik.programming.command.IllegalCommandException;

import java.util.ArrayList;
import java.util.List;

public class AllPublicationsCommand implements Command {
    private static final String COMMAND_ARGUMENT = "publications";

    @Override
    public CommandResult execute(LiteratureSystem model, String[] commandArguments) {
        if (!commandArguments[0].equals(COMMAND_ARGUMENT)) {
            throw new IllegalCommandException();
        }

        StringBuilder result = new StringBuilder();
        List<String> ids = new ArrayList<>(model.getPublications().keySet());
        List<String> sortedIds = ids.stream().sorted().toList();
        for (String id : sortedIds) {
            result.append(id).append(System.lineSeparator());
        }
        result.setLength(result.length() - System.lineSeparator().length());
        return new CommandResult(CommandResultType.SUCCESS, result.toString());
    }

    @Override
    public int getNumberOfArguments() {
        return 1;
    }
}
