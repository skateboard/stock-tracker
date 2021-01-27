package me.brennan.stocktracker.command.impl;

import me.brennan.stocktracker.command.Command;
import me.brennan.stocktracker.util.CurrencySymbols;
import me.brennan.stocktracker.util.TextUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.awt.*;

/**
 * @author Brennan
 * @since 1/27/2021
 **/
public class GetCommand extends Command {

    public GetCommand() {
        super("get", "Gets information about a stock!", EnumPermission.EVERYONE);
    }

    @Override
    public void execute(Message message, String[] args) {
        if(args != null) {
            if(args.length == 1) {
                try {
                    final Stock stock = YahooFinance.get(args[0]);

                    if(stock != null) {
                        final EmbedBuilder embedBuilder = TextUtil.stockToEmbed(stock);

                        TextUtil.sendEmbed(message, embedBuilder.build(), 10);
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
                        .setDescription("Invalid usage! !get <symbol>");
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
