package me.brennan.stocktracker.util;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import yahoofinance.Stock;

import java.awt.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

/**
 * @author Brennan
 * @since 1/27/2021
 **/
public class TextUtil {

    public static void sendEmbed(Message event, MessageEmbed messageEmbed, int delay) {
        event.getChannel().sendMessage(messageEmbed).queue(message -> message.delete().queueAfter(delay, TimeUnit.SECONDS));
    }

    public static void sendPrivateEmbed(Message event, MessageEmbed messageEmbed) {
        event.getChannel().sendMessage(messageEmbed).queue();
    }

    public static EmbedBuilder stockToEmbed(Stock stock) {
        final String currencySymbol = CurrencySymbols.getSymbol(stock.getCurrency());

        String earningsStr = "/";
        if(stock.getStats().getEarningsAnnouncement() != null) {
            earningsStr = stock.getStats().getEarningsAnnouncement().getTime().toString();
        }

        return new EmbedBuilder()
                .setColor(Color.GREEN)
                .setTitle(stock.getSymbol())
                .addField("Company", stock.getName(), true)
                .addField("Currency", stock.getCurrency(), true)
                .addField("Exchange", stock.getStockExchange(), true)
                .addField("Open Price", currencySymbol + stock.getQuote().getOpen(), true)
                .addField("Current Price", currencySymbol + stock.getQuote().getPrice(), true)
                .addField("Previous Close", currencySymbol + stock.getQuote().getPreviousClose(), true)
                .addField("Ask", currencySymbol + stock.getQuote().getAsk(), true)
                .addField("Bid", currencySymbol + stock.getQuote().getBid(), true)
                .addField("Dividend", stock.getDividend().toString(), true)
                .addField("Daily High", currencySymbol + stock.getQuote().getDayHigh(), true)
                .addField("Daily Low", currencySymbol + stock.getQuote().getDayLow(), true)
                .addField("Volume", String.valueOf(stock.getQuote().getVolume()), true)
                .addField("Market Cap", currencySymbol + format(stock.getStats().getMarketCap()), true)
                .addField("EPS", String.valueOf(stock.getStats().getEps()), true)
                .addField("PE", String.valueOf(stock.getStats().getPe()), true)
                .addField("Earnings announcement", earningsStr, true)
                .setFooter("All Stock information acquired from YahooFinance");
    }

    private static String format(BigDecimal input) {
        DecimalFormat df = new DecimalFormat("#,##0.000");
        return df.format(input);
    }

}
