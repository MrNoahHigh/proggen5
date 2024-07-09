package edu.kit.informatik.programming.command.othercommands;

import edu.kit.informatik.programming.model.LiteratureSystem;
import edu.kit.informatik.programming.command.Command;
import edu.kit.informatik.programming.command.CommandResult;

public class JaccardCommand implements Command {
    @Override
    public CommandResult execute(LiteratureSystem model, String[] commandArguments) {
        return null;
    }

    @Override
    public int getNumberOfArguments() {
        return 2;
    }
}
