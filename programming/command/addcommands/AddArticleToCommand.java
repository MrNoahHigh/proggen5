package edu.kit.informatik.programming.command.addcommands;

import edu.kit.informatik.programming.command.CommandResultType;
import edu.kit.informatik.programming.command.IllegalCommandException;
import edu.kit.informatik.programming.model.LiteratureSystem;
import edu.kit.informatik.programming.command.Command;
import edu.kit.informatik.programming.command.CommandResult;
import edu.kit.informatik.programming.model.Publication;

public class AddArticleToCommand implements Command {
    private static final String COMMAND_ARGUMENT = "to";
    private static final String SERIES_VENUE = "series";
    private static final String JOURNAL_VENUE = "journal";
    private static final String NUMBER_ERROR_MESSAGE = "year must be a number.";
    private static final String INVALID_YEAR_ERROR_MESSAGE = "year must be between 0 and 9999.";
    private static final String INVALID_VENUE_ERROR_MESSAGE = "venue type must be series or journal.";
    private static final String INVALID_VENUE_NAME_ERROR_MESSAGE = "venue name '%s' does not exist.";
    private static final String INVALID_PUBLICATION_ID_ERROR_MESSAGE = "publication id must only contain small letters and digits.";
    private static final String VENUE_SEPARATOR = ":";
    private static final String INFORMATION_SEPARATOR = ",";
    private static final String ID_REGEX = "[a-z0-9]+"; //lowercase letters and digits, multiple possible
    private static final int YEAR_MIN_VALUE = 0;
    private static final int YEAR_MAX_VALUE = 9999;
    private static final int NUMBER_OF_ARGUMENTS = 2;

    @Override
    public CommandResult execute(LiteratureSystem model, String[] commandArguments) {
        if (!commandArguments[0].equals(COMMAND_ARGUMENT)) {
            throw new IllegalCommandException();
        }

        String venueType = commandArguments[1];

        if (!venueType.equals(SERIES_VENUE) && !venueType.equals(JOURNAL_VENUE)) {
            return new CommandResult(CommandResultType.FAILURE, INVALID_VENUE_ERROR_MESSAGE);
        }

        String[] articleArguments = commandArguments[2].split(VENUE_SEPARATOR);
        String venueName = articleArguments[0];
        String[] information = articleArguments[1].split(INFORMATION_SEPARATOR);

        int year;
        try {
            year = Integer.parseInt(information[1]);
        } catch (NumberFormatException exception) {
            return new CommandResult(CommandResultType.FAILURE, NUMBER_ERROR_MESSAGE);
        }

        if ((year < YEAR_MIN_VALUE) || (year > YEAR_MAX_VALUE)) {
            return new CommandResult(CommandResultType.FAILURE, INVALID_YEAR_ERROR_MESSAGE);
        }

        if (!information[0].matches(ID_REGEX)) {
            return new CommandResult(CommandResultType.FAILURE, INVALID_PUBLICATION_ID_ERROR_MESSAGE);
        }

        Publication publication;
        if (venueType.equals(SERIES_VENUE)) {
            if (!model.getSeries().containsKey(venueName)) {
                return new CommandResult(CommandResultType.FAILURE, INVALID_VENUE_NAME_ERROR_MESSAGE.formatted(venueName));
            }
            publication = new Publication(information[0], year, information[2], venueName, null);
            model.getSeries().get(venueName).addPublication(publication);
        } else {
            if (!model.getJournals().containsKey(venueName)) {
                return new CommandResult(CommandResultType.FAILURE, INVALID_VENUE_NAME_ERROR_MESSAGE.formatted(venueName));
            }
            publication = new Publication(information[0], year, information[2], null, venueName);
            model.getJournals().get(venueName).addPublication(publication);
        }

        model.addPublication(information[0], publication);
        model.addInvalidPublication(information[0], publication);

        return new CommandResult(CommandResultType.SUCCESS, null);
    }

    @Override
    public int getNumberOfArguments() {
        return NUMBER_OF_ARGUMENTS;
    }
}
