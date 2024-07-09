package edu.kit.informatik.programming.command.othercommands;

import edu.kit.informatik.programming.command.CommandResultType;
import edu.kit.informatik.programming.model.LiteratureSystem;
import edu.kit.informatik.programming.command.Command;
import edu.kit.informatik.programming.command.CommandResult;
import edu.kit.informatik.programming.command.IllegalCommandException;
import edu.kit.informatik.programming.model.Publication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PublicationsByCommand implements Command {
    private static final String COMMAND_ARGUMENT = "by";
    private static final String AUTHOR_NOT_EXIST = "author '%s' does not exist.";
    private static final String AUTHOR_NAME_SEPARATOR = ";";
    private static final String AUTHOR_NAME_SPACE = " ";

    @Override
    public CommandResult execute(LiteratureSystem model, String[] commandArguments) {
        if (!commandArguments[0].equals(COMMAND_ARGUMENT)) {
            throw new IllegalCommandException();
        }

        StringBuilder authorsArgument = new StringBuilder();
        for (String argument : Arrays.copyOfRange(commandArguments, 2, commandArguments.length)) {
            authorsArgument.append(argument).append(AUTHOR_NAME_SPACE);
        }

        String[] authors = authorsArgument.toString().split(AUTHOR_NAME_SEPARATOR);
        List<String> publications = new ArrayList<>();

        for (String author : authors) {
            if (!model.getAuthors().containsKey(author)) {
                return new CommandResult(CommandResultType.FAILURE, AUTHOR_NOT_EXIST.formatted(author));
            }
            addPublications(publications, model.getAuthors().get(author).getPublications());
        }

        List<String> sortedPublications = publications.stream().sorted().toList();
        StringBuilder result = new StringBuilder();
        for (String publication : sortedPublications) {
            result.append(publication).append(System.lineSeparator());
        }

        result.setLength(result.length() - System.lineSeparator().length());
        return new CommandResult(CommandResultType.SUCCESS, result.toString());
    }

    private void addPublications(List<String> allPublications, List<Publication> publications) {
        for (Publication publication : publications) {
            if (!allPublications.contains(publication.getId())) {
                allPublications.add(publication.getId());
            }
        }
    }

    @Override
    public int getNumberOfArguments() {
        return -1; //list
    }
}
