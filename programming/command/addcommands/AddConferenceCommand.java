package edu.kit.informatik.programming.command.addcommands;

import edu.kit.informatik.programming.command.CommandResultType;
import edu.kit.informatik.programming.model.Conference;
import edu.kit.informatik.programming.model.LiteratureSystem;
import edu.kit.informatik.programming.command.Command;
import edu.kit.informatik.programming.command.CommandResult;

public class AddConferenceCommand implements Command {
    private static final String CONFERENCE_ARGUMENTS_SEPARATOR = ",";
    private static final String NUMBER_ERROR_MESSAGE = "year must be a number.";
    private static final String INVALID_YEAR_ERROR_MESSAGE = "year must be between 0 and 9999.";
    private static final String SERIES_NOT_FOUND_MESSAGE = "series '%s' does not exist";
    private static final int NUMBER_OF_ARGUMENTS = 1;
    private static final int YEAR_MIN_VALUE = 0;
    private static final int YEAR_MAX_VALUE = 9999;

    @Override
    public CommandResult execute(LiteratureSystem model, String[] commandArguments) {
        String[] conferenceArguments = commandArguments[0].split(CONFERENCE_ARGUMENTS_SEPARATOR);
        if (!model.getSeries().containsKey(conferenceArguments[0])) {
            return new CommandResult(CommandResultType.FAILURE, SERIES_NOT_FOUND_MESSAGE.formatted(conferenceArguments[0]));
        }

        int year;
        try {
            year = Integer.parseInt(conferenceArguments[1]);
        } catch (NumberFormatException exception) {
            return new CommandResult(CommandResultType.FAILURE, NUMBER_ERROR_MESSAGE);
        }

        if ((year < YEAR_MIN_VALUE) || (year > YEAR_MAX_VALUE)) {
            return new CommandResult(CommandResultType.FAILURE, INVALID_YEAR_ERROR_MESSAGE);
        }

        model.getSeries().get(conferenceArguments[0]).addConference(new Conference(conferenceArguments[0], year, conferenceArguments[2]));
        return new CommandResult(CommandResultType.SUCCESS, null);
    }

    @Override
    public int getNumberOfArguments() {
        return NUMBER_OF_ARGUMENTS;
    }
}
