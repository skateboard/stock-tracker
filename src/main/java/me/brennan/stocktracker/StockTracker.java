package me.brennan.stocktracker;

import me.brennan.stocktracker.command.CommandManager;
import me.brennan.stocktracker.listeners.MessageListener;
import me.brennan.stocktracker.thread.ActivityThread;
import me.brennan.stocktracker.thread.TrackerThread;
import me.brennan.stocktracker.util.ConfigUtil;
import me.brennan.stocktracker.util.MarketUtil;
import me.brennan.stocktracker.util.model.Config;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Brennan
 * @since 1/27/2021
 **/
public enum StockTracker {
    INSTANCE;

    private JDA jda;

    private CommandManager commandManager;

    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    private final List<TrackerThread> threads = new LinkedList<>();

    private Config config;

    private Guild guild;
    private Role staffRole;

    public void start() throws Exception {
        this.config = ConfigUtil.readConfig();
        if(config == null) {
            return;
        }

        this.jda = JDABuilder
                .createDefault(config.getToken())
                .addEventListeners(new MessageListener())
                .build()
                .awaitReady();

        // do cool status change
        this.executorService
                .scheduleAtFixedRate(new ActivityThread(),
                        0, 1, TimeUnit.HOURS);

        this.guild = jda.getGuildById(config.getGuildID());

        if(guild != null) {
            this.staffRole = guild.getRoleById(config.getStaffRole());
        }

        this.commandManager = new CommandManager();
    }

    public List<TrackerThread> getThreads() {
        return threads;
    }

    public Config getConfig() {
        return config;
    }

    public JDA getJda() {
        return jda;
    }

    public Guild getGuild() {
        return guild;
    }

    public Role getStaffRole() {
        return staffRole;
    }

    public ScheduledExecutorService getExecutorService() {
        return executorService;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }
}
