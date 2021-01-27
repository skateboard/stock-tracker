package me.brennan.stocktracker.command;

import me.brennan.stocktracker.command.impl.GetCommand;
import me.brennan.stocktracker.command.impl.ThreadCommand;
import me.brennan.stocktracker.command.impl.TrackerCommand;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Brennan
 * @since 1/27/2021
 **/
public class CommandManager {
    private final List<Command> commands = new LinkedList<>();

    public CommandManager() {
        this.commands.add(new TrackerCommand());
        this.commands.add(new GetCommand());
        this.commands.add(new ThreadCommand());
    }

    public Command getCommand(String name) {
        for (Command command : commands)
            if (command.getName().equalsIgnoreCase(name)) {
                return command;
            }

        return null;
    }

    public List<Command> getCommands() {
        return commands;
    }
}
