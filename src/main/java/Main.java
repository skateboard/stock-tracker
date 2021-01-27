import me.brennan.stocktracker.StockTracker;

/**
 * @author Brennan
 * @since 1/27/2021
 **/
public class Main {

    public static void main(String[] args) {
        try {
            StockTracker.INSTANCE.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
