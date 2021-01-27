package me.brennan.stocktracker.command;

import net.dv8tion.jda.api.entities.Message;

/**
 * @author Brennan
 * @since 1/27/2021
 **/
public abstract class Command {
    private final String name, description;
    private final EnumPermission enumPermission;

    public Command(String name, String description, EnumPermission enumPermission) {
        this.name = name;
        this.description = description;
        this.enumPermission = enumPermission;
    }

    public abstract void execute(Message message, String[] args);

    public String getName() {
        return name;
    }

    public EnumPermission getEnumPermission() {
        return enumPermission;
    }

    public String getDescription() {
        return description;
    }

    public enum EnumPermission {
        EVERYONE,
        STAFF
    }


}