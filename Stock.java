 

import java.io.Serializable;
import java.util.ArrayList;


public class Stock implements Serializable {
    private double currentPrice;
    private String name;
    private String symbol;
    private ArrayList<Price> prices;

    public Stock(ArrayList<Price> prices, String company, String symbol){
        this.prices = prices;
        this.name = company;
        this.symbol = symbol;
        this.currentPrice = prices.get(prices.size() - 1).getClose();
    }


    public String getTicker() {
        return symbol;
    }

    public String getCompany() {
        return name;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setPrices(ArrayList<Price> prices) {
        this.prices = prices;
        if (prices.size() > 0) {
            currentPrice = prices.get(prices.size() - 1).getClose();
        }
    }

    public ArrayList<Price> getPrices() {
        return prices;
    }
}
