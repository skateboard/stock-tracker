package me.brennan.stocktracker.command.impl;

import me.brennan.stocktracker.StockTracker;
import me.brennan.stocktracker.command.Command;
import me.brennan.stocktracker.util.TextUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;

import java.awt.*;

/**
 * @author Brennan
 * @since 1/27/2021
 **/
public class ThreadCommand extends Command {

    public ThreadCommand() {
        super("threads", "Shows current tracker threads", EnumPermission.STAFF);
    }

    @Override
    public void execute(Message message, String[] args) {
        final EmbedBuilder embedBuilder = new EmbedBuilder()
                .setColor(Color.GREEN)
                .addField("Amount", String.valueOf(StockTracker.INSTANCE.getThreads().size()), true);

        TextUtil.sendEmbed(message, embedBuilder.build(), 5);
    }
}
