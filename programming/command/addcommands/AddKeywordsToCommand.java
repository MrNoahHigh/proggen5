package edu.kit.informatik.programming.command.addcommands;

import edu.kit.informatik.programming.command.CommandResultType;
import edu.kit.informatik.programming.model.LiteratureSystem;
import edu.kit.informatik.programming.command.Command;
import edu.kit.informatik.programming.command.CommandResult;
import edu.kit.informatik.programming.command.IllegalCommandException;
import edu.kit.informatik.programming.model.Publication;

import java.util.List;

public class AddKeywordsToCommand implements Command {
    private static final String COMMAND_ARGUMENT = "to";
    private static final String SERIES_VENUE = "series";
    private static final String JOURNAL_VENUE = "journal";
    private static final String PUBLICATION_NOT_FOUND = "publication does not exist.";
    private static final String ID_LIST_SEPARATOR = ":";
    private static final String LIST_SEPARATOR = ";";


    @Override
    public CommandResult execute(LiteratureSystem model, String[] commandArguments) {
        if (!commandArguments[0].equals(COMMAND_ARGUMENT)) {
            throw new IllegalCommandException();
        }

        String[] idList = commandArguments[commandArguments.length - 1].split(ID_LIST_SEPARATOR);

        if (commandArguments[1].equals(SERIES_VENUE)) {
            if (model.getSeries().containsKey(idList[0])) {
                model.getSeries().get(idList[0]).addKeywords(List.of(idList[1].split(LIST_SEPARATOR)));
            } else {
                return new CommandResult(CommandResultType.FAILURE, "series does not exist.");
            }
        } else if (commandArguments[1].equals(JOURNAL_VENUE)) {
            if (model.getJournals().containsKey(idList[0])) {
                model.getJournals().get(idList[0]).addKeywords(List.of(idList[1].split(LIST_SEPARATOR)));
            } else {
                return new CommandResult(CommandResultType.FAILURE, "journal does not exist.");
            }
        } else if (model.getPublications().containsKey(idList[0])) {
            model.getPublications().get(idList[0]).addKeywords(List.of(idList[1].split(LIST_SEPARATOR)));
        } else {
            return new CommandResult(CommandResultType.FAILURE, PUBLICATION_NOT_FOUND);
        }

        return new CommandResult(CommandResultType.SUCCESS, null);
    }

    @Override
    public int getNumberOfArguments() {
        return 0; //2 oder 3
    }
}
