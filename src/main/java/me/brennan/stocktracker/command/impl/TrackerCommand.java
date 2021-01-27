package me.brennan.stocktracker.command.impl;

import me.brennan.stocktracker.StockTracker;
import me.brennan.stocktracker.command.Command;
import me.brennan.stocktracker.thread.TrackerThread;
import me.brennan.stocktracker.util.TextUtil;
import me.brennan.stocktracker.util.model.Tracker;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.awt.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Brennan
 * @since 1/27/2021
 **/
public class TrackerCommand extends Command {

    public TrackerCommand() {
        super("track", "Tracks a stock", EnumPermission.STAFF);
    }

    @Override
    public void execute(Message message, String[] args) {
        if(args != null) {
            if(args.length == 1) {
                try {
                    final Stock stock = YahooFinance.get(args[0]);

                    if(stock != null) {
                        final EmbedBuilder embedBuilder = new EmbedBuilder()
                                .setColor(Color.GREEN)
                                .setTitle(stock.getSymbol())
                                .setDescription("Gathering Information...")
                                .setFooter("All Stock information acquired from YahooFinance");

                        message.getChannel().sendMessage(embedBuilder.build()).queue(msg -> {
                            final Tracker tracker = new Tracker(stock.getSymbol(), msg.getTextChannel(), msg.getId());

                            final TrackerThread trackerThread = new TrackerThread(tracker);
                            StockTracker.INSTANCE.getThreads().add(trackerThread);

                            StockTracker.INSTANCE.getExecutorService()
                                    .scheduleAtFixedRate(trackerThread,
                                            0, StockTracker.INSTANCE.getConfig().getThreadLoop(), TimeUnit.SECONDS);

                        });
                    } else {
                        final EmbedBuilder embedBuilder = new EmbedBuilder()
                                .setColor(Color.RED)
                                .setDescription("Stock not found!");
                        TextUtil.sendEmbed(message, embedBuilder.build(), 5);
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                    final EmbedBuilder embedBuilder = new EmbedBuilder()
                            .setColor(Color.RED)
                            .setDescription("ERROR! Failed to get stock!");
                    TextUtil.sendEmbed(message, embedBuilder.build(), 5);
                }
            } else {
                final EmbedBuilder embedBuilder = new EmbedBuilder()
                        .setColor(Color.RED)
                        .setDescription("Invalid usage! !track <symbol>");
                TextUtil.sendEmbed(message, embedBuilder.build(), 5);
            }
        } else {
            final EmbedBuilder embedBuilder = new EmbedBuilder()
                    .setColor(Color.RED)
                    .setDescription("Not enough args");
            TextUtil.sendEmbed(message, embedBuilder.build(), 5);
        }
    }
}
