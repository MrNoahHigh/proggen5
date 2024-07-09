package edu.kit.informatik.programming.command.addcommands;

import edu.kit.informatik.programming.command.CommandResultType;
import edu.kit.informatik.programming.model.LiteratureSystem;
import edu.kit.informatik.programming.command.Command;
import edu.kit.informatik.programming.command.CommandResult;
import edu.kit.informatik.programming.model.Series;

public class AddSeriesCommand implements Command {
    private static final int NUMBER_OF_ARGUMENTS = 1;

    @Override
    public CommandResult execute(LiteratureSystem model, String[] commandArguments) {
        model.addSeries(commandArguments[0], new Series(commandArguments[0]));
        return new CommandResult(CommandResultType.SUCCESS, null);
    }

    @Override
    public int getNumberOfArguments() {
        return NUMBER_OF_ARGUMENTS;
    }
}
