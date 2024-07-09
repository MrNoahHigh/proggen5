package edu.kit.informatik.programming.command.othercommands;

import edu.kit.informatik.programming.command.CommandResultType;
import edu.kit.informatik.programming.model.LiteratureSystem;
import edu.kit.informatik.programming.command.Command;
import edu.kit.informatik.programming.command.CommandResult;
import edu.kit.informatik.programming.command.IllegalCommandException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WrittenByCommand implements Command {
    private static final String COMMAND_ARGUMENT = "by";
    private static final String ID_NOT_EXIST = "id '%s' does not exist.";
    private static final String AUTHOR_NOT_EXIST = "author '%s' does not exist.";
    private static final String AUTHOR_ALREADY_WRITTEN = "author '%s' can only be written once in publication '%s'.";
    private static final String AUTHOR_NAME_SPACE = " ";
    private static final String ID_AUTHOR_SEPARATOR = ",";
    private static final String AUTHOR_NAME_SEPARATOR = ";";

    @Override
    public CommandResult execute(LiteratureSystem model, String[] commandArguments) {
        if (!commandArguments[0].equals(COMMAND_ARGUMENT)) {
            throw new IllegalCommandException();
        }

        String[] idAuthor = commandArguments[1].split(ID_AUTHOR_SEPARATOR);

        if (!model.getPublications().containsKey(idAuthor[0])) {
            return new CommandResult(CommandResultType.FAILURE, ID_NOT_EXIST.formatted(commandArguments[1]));
        }

        StringBuilder authorsArgument = new StringBuilder();
        authorsArgument.append(idAuthor[1]);
        for (String argument : Arrays.copyOfRange(commandArguments, 2, commandArguments.length)) {
            authorsArgument.append(AUTHOR_NAME_SPACE).append(argument);
        }

        String[] authors = authorsArgument.toString().split(AUTHOR_NAME_SEPARATOR);
        List<String> authorsList = new ArrayList<>();

        for (String author : authors) {
            if (!model.getAuthors().containsKey(author)) {
                return new CommandResult(CommandResultType.FAILURE, AUTHOR_NOT_EXIST.formatted(author));
            }

            if (model.getPublications().get(idAuthor[0]).getAuthors().containsKey(author)
                    || authorsList.contains(author)) {
                return new CommandResult(CommandResultType.FAILURE, AUTHOR_ALREADY_WRITTEN.formatted(author, idAuthor[0]));
            }

            authorsList.add(author);
        }

        model.getPublications().get(idAuthor[0]).addAuthors(authorsList, model.getAuthors());
        if (model.getInvalidPublications().containsKey(idAuthor[0])) {
            model.removeInvalidPublication(idAuthor[0]);
        }
        registerCoauthors(model.getPublications().get(idAuthor[0]).getAuthors().keySet().stream().toList(), model);

        return new CommandResult(CommandResultType.SUCCESS, null);
    }

    private void registerCoauthors(List<String> authors, LiteratureSystem model) {
        for (String author : authors) {
            for (String coauthor : authors) {
                if (!author.equals(coauthor)) {
                    model.getAuthors().get(author).addCoauthor(coauthor);
                }
            }
        }
    }

    @Override
    public int getNumberOfArguments() {
        return -1; //differs
    }
}
