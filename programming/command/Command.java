/*
 * Copyright (c) 2024, KASTEL. All rights reserved.
 */

package edu.kit.informatik.programming.command;

import edu.kit.informatik.programming.model.LiteratureSystem;

/**
 * This interface represents an executable command.
 *
 * @author Programmieren-Team
 * @author uymkb
 */
public interface Command {

    /**
     * Executes the command.
     * 
     * @param model             the model to execute the command on
     * @param commandArguments  the arguments of the command
     * @return the result of the command
     */
    CommandResult execute(LiteratureSystem model, String[] commandArguments);

    /**
     * Returns the number of arguments that the command expects.
     * 
     * @return the number of arguments that the command expects
     */
    int getNumberOfArguments();
}
