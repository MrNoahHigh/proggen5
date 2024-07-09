/*
 * Copyright (c) 2024, KASTEL. All rights reserved.
 */

package edu.kit.informatik.programming.command;

import edu.kit.informatik.programming.model.LiteratureSystem;
import edu.kit.informatik.programming.command.addcommands.AddArticleToCommand;
import edu.kit.informatik.programming.command.addcommands.AddAuthorCommand;
import edu.kit.informatik.programming.command.addcommands.AddConferenceCommand;
import edu.kit.informatik.programming.command.addcommands.AddJournalCommand;
import edu.kit.informatik.programming.command.addcommands.AddKeywordsToCommand;
import edu.kit.informatik.programming.command.addcommands.AddSeriesCommand;
import edu.kit.informatik.programming.command.othercommands.AllPublicationsCommand;
import edu.kit.informatik.programming.command.othercommands.CitesCommand;
import edu.kit.informatik.programming.command.othercommands.CoauthorsOfCommand;
import edu.kit.informatik.programming.command.othercommands.FindByKeywordsCommand;
import edu.kit.informatik.programming.command.othercommands.ForeignCitationsOfCommand;
import edu.kit.informatik.programming.command.othercommands.InProceedingsCommand;
import edu.kit.informatik.programming.command.othercommands.JaccardCommand;
import edu.kit.informatik.programming.command.othercommands.ListInvalidPublicationsCommand;
import edu.kit.informatik.programming.command.othercommands.PrintBibliographyCommand;
import edu.kit.informatik.programming.command.othercommands.PublicationsByCommand;
import edu.kit.informatik.programming.command.othercommands.QuitCommand;
import edu.kit.informatik.programming.command.othercommands.SimilarityCommand;
import edu.kit.informatik.programming.command.othercommands.WrittenByCommand;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This class handles the user input and executes the commands.
 * 
 * @author Programmieren-Team
 * @author uymkb
 */
public final class CommandHandler {
    private static final String COMMAND_SEPARATOR_REGEX = " +";
    private static final String ERROR_PREFIX = "Error, ";
    private static final String COMMAND_NOT_FOUND_FORMAT = "command '%s' not found!";
    private static final String WRONG_ARGUMENTS_COUNT_FORMAT = "wrong number of arguments for command '%s'!";
    private static final int COMMAND_NAME_INDEX = 0;
    private static final int COMMAND_ARGS_START_INDEX = 1;

    private static final String QUIT_COMMAND_NAME = "quit";
    private static final String ADD_COMMAND_NAME = "add";
    private static final String ADD_AUTHOR_COMMAND_NAME = "author";
    private static final String ADD_JOURNAL_COMMAND_NAME = "journal";
    private static final String ADD_SERIES_COMMAND_NAME = "series";
    private static final String ADD_CONFERENCE_COMMAND_NAME = "conference";
    private static final String ADD_ARTICLE_TO_COMMAND_NAME = "article";
    private static final String WRITTEN_BY_COMMAND_NAME = "written";
    private static final String CITES_COMMAND_NAME = "cites";
    private static final String ADD_KEYWORDS_TO_COMMAND_NAME = "keywords";
    private static final String ALL_PUBLICATIONS_COMMAND_NAME = "all";
    private static final String LIST_INVALID_PUBLICATIONS_COMMAND_NAME = "list";
    private static final String PUBLICATIONS_BY_COMMAND_NAME = "publications";
    private static final String IN_PROCEEDINGS_COMMAND_NAME = "in";
    private static final String FIND_BY_KEYWORDS_COMMAND_NAME = "find";
    private static final String JACCARD_COMMAND_NAME = "jaccard";
    private static final String SIMILARITY_COMMAND_NAME = "similarity";
    private static final String COAUTHORS_OF_COMMAND_NAME = "coauthors";
    private static final String FOREIGN_CITATIONS_OF_COMMAND_NAME = "foreign";
    private static final String PRINT_BIBLIOGRAPHY_COMMAND_NAME = "print";
    

    private static final String INVALID_RESULT_TYPE_FORMAT = "Unexpected value: %s";

    private final LiteratureSystem literatureSystem;
    private final Map<String, Command> commands;
    private boolean isRunning = false;

    /**
     * Constructs a new CommandHandler.
     *
     * @param literatureSystem the literatureSystem that this instance manages
     */
    public CommandHandler(LiteratureSystem literatureSystem) {
        this.literatureSystem = literatureSystem;
        this.commands = new HashMap<>();
        this.initCommands();
    }

    /**
     * Starts the interaction with the user.
     */
    public void handleUserInput() {
        this.isRunning = true;

        try (Scanner scanner = new Scanner(System.in)) {
            while (isRunning && scanner.hasNextLine()) {
                executeCommand(scanner.nextLine());
            }
        }
    }

    /**
     * Quits the interaction with the user.
     */
    public void quit() {
        this.isRunning = false;
    }

    private void executeCommand(String commandWithArguments) {
        String[] splittedCommand = commandWithArguments.trim().split(COMMAND_SEPARATOR_REGEX);
        String commandName = splittedCommand[COMMAND_NAME_INDEX];
        String[] commandArguments = Arrays.copyOfRange(splittedCommand, COMMAND_ARGS_START_INDEX, splittedCommand.length);

        executeCommand(commandName, commandArguments, commandWithArguments);
    }

    private void executeCommand(String commandName, String[] commandArguments, String commandWithArguments) {
        if (commandName.equals(ADD_COMMAND_NAME)) {
            executeCommand(commandArguments[0], Arrays.copyOfRange(commandArguments, 1, commandArguments.length),
                    commandWithArguments);
            return;
        }
        if (!commands.containsKey(commandName)) {
            System.err.println(ERROR_PREFIX + COMMAND_NOT_FOUND_FORMAT.formatted(commandName));
        } else if (!(commandName.equals(WRITTEN_BY_COMMAND_NAME) || commandName.equals(ADD_KEYWORDS_TO_COMMAND_NAME)
                || commandName.equals(PUBLICATIONS_BY_COMMAND_NAME) || commandName.equals(ADD_ARTICLE_TO_COMMAND_NAME))
                && commands.get(commandName).getNumberOfArguments() != commandArguments.length) {
            System.err.println(ERROR_PREFIX + WRONG_ARGUMENTS_COUNT_FORMAT.formatted(commandName));
        } else {
            CommandResult result;
            try {
                result = commands.get(commandName).execute(literatureSystem, commandArguments);
            } catch (IllegalCommandException exception) {
                System.err.println(ERROR_PREFIX + COMMAND_NOT_FOUND_FORMAT.formatted(commandWithArguments));
                return;
            }
            String output = switch (result.getType()) {
                case SUCCESS -> result.getMessage();
                case FAILURE -> ERROR_PREFIX + result.getMessage();
            };
            if (output != null) {
                switch (result.getType()) {
                    case SUCCESS -> System.out.println(output);
                    case FAILURE -> System.err.println(output);
                    default -> throw new IllegalStateException(INVALID_RESULT_TYPE_FORMAT.formatted(result.getType()));
                }
            }
        }
    }

    private void initCommands() {
        this.addCommand(QUIT_COMMAND_NAME, new QuitCommand(this));
        this.addCommand(ADD_AUTHOR_COMMAND_NAME, new AddAuthorCommand());
        this.addCommand(ADD_JOURNAL_COMMAND_NAME, new AddJournalCommand());
        this.addCommand(ADD_SERIES_COMMAND_NAME, new AddSeriesCommand());
        this.addCommand(ADD_CONFERENCE_COMMAND_NAME, new AddConferenceCommand());
        this.addCommand(ADD_ARTICLE_TO_COMMAND_NAME, new AddArticleToCommand());
        this.addCommand(WRITTEN_BY_COMMAND_NAME, new WrittenByCommand());
        this.addCommand(CITES_COMMAND_NAME, new CitesCommand());
        this.addCommand(ADD_KEYWORDS_TO_COMMAND_NAME, new AddKeywordsToCommand());
        this.addCommand(ALL_PUBLICATIONS_COMMAND_NAME, new AllPublicationsCommand());
        this.addCommand(LIST_INVALID_PUBLICATIONS_COMMAND_NAME, new ListInvalidPublicationsCommand());
        this.addCommand(PUBLICATIONS_BY_COMMAND_NAME, new PublicationsByCommand());
        this.addCommand(IN_PROCEEDINGS_COMMAND_NAME, new InProceedingsCommand());
        this.addCommand(FIND_BY_KEYWORDS_COMMAND_NAME, new FindByKeywordsCommand());
        this.addCommand(JACCARD_COMMAND_NAME, new JaccardCommand());
        this.addCommand(SIMILARITY_COMMAND_NAME, new SimilarityCommand());
        this.addCommand(COAUTHORS_OF_COMMAND_NAME, new CoauthorsOfCommand());
        this.addCommand(FOREIGN_CITATIONS_OF_COMMAND_NAME, new ForeignCitationsOfCommand());
        this.addCommand(PRINT_BIBLIOGRAPHY_COMMAND_NAME, new PrintBibliographyCommand());
    }

    private void addCommand(String commandName, Command command) {
        this.commands.put(commandName, command);
    }
}
