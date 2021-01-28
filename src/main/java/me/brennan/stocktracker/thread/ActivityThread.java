package me.brennan.stocktracker.thread;

import me.brennan.stocktracker.StockTracker;
import me.brennan.stocktracker.util.MarketUtil;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

/**
 * @author Brennan
 * @since 1/27/2021
 **/
public class ActivityThread implements Runnable {

    @Override
    public void run() {
        if(MarketUtil.isOpen()) {
            StockTracker.INSTANCE.getJda().getPresence().setPresence(OnlineStatus.ONLINE, Activity.watching("The markets"));
        } else {
            StockTracker.INSTANCE.getJda().getPresence().setPresence(OnlineStatus.DO_NOT_DISTURB, Activity.listening("r/wallstreetbets"));
        }
    }
}
