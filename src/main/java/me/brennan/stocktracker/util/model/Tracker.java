package me.brennan.stocktracker.util.model;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import yahoofinance.Stock;

/**
 * @author Brennan
 * @since 1/27/2021
 **/
public class Tracker {
    private final String symbol;
    private final TextChannel channel;
    private final String id;


    public Tracker(String symbol, TextChannel channel, String id) {
        this.symbol = symbol;
        this.channel = channel;
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public TextChannel getChannel() {
        return channel;
    }

    public String getId() {
        return id;
    }
}
