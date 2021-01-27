package me.brennan.stocktracker.thread;

import me.brennan.stocktracker.util.CurrencySymbols;
import me.brennan.stocktracker.util.TextUtil;
import me.brennan.stocktracker.util.model.Tracker;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.awt.*;

/**
 * @author Brennan
 * @since 1/27/2021
 **/
public class TrackerThread implements Runnable {
    private final Tracker tracker;

    public TrackerThread(Tracker tracker) {
        this.tracker = tracker;
    }

    @Override
    public void run() {
        try {
            final Stock stock = YahooFinance.get(tracker.getSymbol());
            final EmbedBuilder embedBuilder = TextUtil.stockToEmbed(stock);

            tracker.getChannel().editMessageById(tracker.getId(), embedBuilder.build()).queue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
