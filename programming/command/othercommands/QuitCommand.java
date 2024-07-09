/*
 * Copyright (c) 2024, KASTEL. All rights reserved.
 */

package edu.kit.informatik.programming.command.othercommands;

import edu.kit.informatik.programming.model.LiteratureSystem;
import edu.kit.informatik.programming.command.Command;
import edu.kit.informatik.programming.command.CommandHandler;
import edu.kit.informatik.programming.command.CommandResult;
import edu.kit.informatik.programming.command.CommandResultType;

/**
 * This command quits a {@link CommandHandler command handler}.
 *
 * @author Programmieren-Team
 * @author uymkb
 */
public final class QuitCommand implements Command {
    private static final int NUMBER_OF_ARGUMENTS = 0;

    private final CommandHandler commandHandler;

    /**
     * Constructs a new QuitCommand.
     * 
     * @param commandHandler the command handler to be quitted
     */
    public QuitCommand(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @Override
    public CommandResult execute(LiteratureSystem model, String[] commandArguments) {
        commandHandler.quit();
        return new CommandResult(CommandResultType.SUCCESS, null);
    }

    @Override
    public int getNumberOfArguments() {
        return NUMBER_OF_ARGUMENTS;
    }
}
