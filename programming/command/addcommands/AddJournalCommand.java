package edu.kit.informatik.programming.command.addcommands;

import edu.kit.informatik.programming.command.CommandResultType;
import edu.kit.informatik.programming.model.Journal;
import edu.kit.informatik.programming.model.LiteratureSystem;
import edu.kit.informatik.programming.command.Command;
import edu.kit.informatik.programming.command.CommandResult;

public class AddJournalCommand implements Command {
    private static final String JOURNAL_ARGUMENT_SEPARATOR = ",";
    private static final int NUMBER_OF_ARGUMENTS = 1;

    @Override
    public CommandResult execute(LiteratureSystem model, String[] commandArguments) {
        String[] journalArguments = commandArguments[0].split(JOURNAL_ARGUMENT_SEPARATOR);
        model.addJournal(journalArguments[0], new Journal(journalArguments[0], journalArguments[1]));
        return new CommandResult(CommandResultType.SUCCESS, null);
    }

    @Override
    public int getNumberOfArguments() {
        return NUMBER_OF_ARGUMENTS;
    }
}
