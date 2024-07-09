package edu.kit.informatik.programming.command.othercommands;

import edu.kit.informatik.programming.command.CommandResultType;
import edu.kit.informatik.programming.model.LiteratureSystem;
import edu.kit.informatik.programming.command.Command;
import edu.kit.informatik.programming.command.CommandResult;
import edu.kit.informatik.programming.command.IllegalCommandException;

import java.util.List;

public class CoauthorsOfCommand implements Command {
    private static final String COMMAND_ARGUMENT = "of";
    private static final String NAME_SPACE = " ";
    private static final String AUTHOR_NOT_EXIST = "author does not exist.";

    @Override
    public CommandResult execute(LiteratureSystem model, String[] commandArguments) {
        if (!commandArguments[0].equals(COMMAND_ARGUMENT)) {
            throw new IllegalCommandException();
        }

        String author = commandArguments[1] + NAME_SPACE + commandArguments[2];

        if (!model.getAuthors().containsKey(author)) {
            return new CommandResult(CommandResultType.FAILURE, AUTHOR_NOT_EXIST);
        }

        List<String> coauthors = model.getAuthors().get(author).getCoauthors();
        List<String> sortedCoauthors = coauthors.stream().sorted().toList();

        StringBuilder result = new StringBuilder();
        for (String coauthor : sortedCoauthors) {
            result.append(coauthor).append(System.lineSeparator());
        }
        result.setLength(result.length() - System.lineSeparator().length());

        return new CommandResult(CommandResultType.SUCCESS, result.toString());
    }

    @Override
    public int getNumberOfArguments() {
        return 3;
    }
}
