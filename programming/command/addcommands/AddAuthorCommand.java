package edu.kit.informatik.programming.command.addcommands;

import edu.kit.informatik.programming.command.CommandResultType;
import edu.kit.informatik.programming.model.Author;
import edu.kit.informatik.programming.model.LiteratureSystem;
import edu.kit.informatik.programming.command.Command;
import edu.kit.informatik.programming.command.CommandResult;

public class AddAuthorCommand implements Command {
    private static final String AUTHOR_ALREADY_EXIST_ERROR = "author with same name already added.";
    private static final int NUMBER_OF_ARGUMENTS = 2;

    @Override
    public CommandResult execute(LiteratureSystem model, String[] commandArguments) {
        String name = commandArguments[0] + " " + commandArguments[1];
        if (model.getAuthors().containsKey(name)) {
            return new CommandResult(CommandResultType.FAILURE, AUTHOR_ALREADY_EXIST_ERROR);
        }
        model.addAuthor(name, new Author(commandArguments[0], commandArguments[1]));
        return new CommandResult(CommandResultType.SUCCESS, null);
    }

    @Override
    public int getNumberOfArguments() {
        return NUMBER_OF_ARGUMENTS;
    }
}
