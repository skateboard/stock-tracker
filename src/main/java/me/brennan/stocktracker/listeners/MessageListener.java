package me.brennan.stocktracker.listeners;

import me.brennan.stocktracker.StockTracker;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

/**
 * @author Brennan
 * @since 1/27/2021
 **/
public class MessageListener extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if (event.getAuthor() != event.getJDA().getSelfUser()) {
            final String content = event.getMessage().getContentRaw();
            if (content.startsWith("!")) {
                String commandID = content.split(" ")[0].replaceFirst("!", "");
                String[] args = null;
                if (content.split(" ").length > 1) {
                    args = content.replaceFirst("!" + commandID + " ", "").split(" ");
                }

                String[] finalArgs = args;
                StockTracker.INSTANCE.getCommandManager().getCommands()
                        .stream()
                        .filter(command -> command.getName().equalsIgnoreCase(commandID))
                        .forEach(command -> {
                            event.getMessage().delete().queue();
                            switch (command.getEnumPermission()) {
                                case STAFF:
                                    if(doesUserHaveRole(StockTracker.INSTANCE.getStaffRole(), event.getAuthor(), event.getGuild()))
                                        command.execute(event.getMessage(), finalArgs);
                                    break;
                                case EVERYONE:
                                    command.execute(event.getMessage(), finalArgs);
                                    break;
                            }
                        });
            }
        }
    }

    private boolean doesUserHaveRole(Role role, User user, Guild guild) {
        return guild.getMember(user).getRoles().contains(role);
    }

}