package me.brennan.stocktracker.util;

/**
 * @author Brennan
 * @since 1/27/2021
 **/
public enum CurrencySymbols {
    USD("$"),
    EUR("€");

    private final String label;

    CurrencySymbols(String label) {
        this.label = label;
    }

    public static String getSymbol(String name) {
        for(CurrencySymbols symbol : values()) {
            if(symbol.name().equalsIgnoreCase(name))
                return symbol.getLabel();
        }

        return "$";
    }

    public String getLabel() {
        return label;
    }
}
