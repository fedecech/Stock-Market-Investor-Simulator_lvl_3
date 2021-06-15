 

import java.util.*;

public class User extends Account
{
    private double balance;
    private ArrayList<Stock> myStocks;

    public User(String username, String password){
        super(username, password);
        this.myStocks = new ArrayList<>();
        this.balance = 10000;
    }

    public void buyStock(Stock stock){
        if(balance >= stock.getCurrentPrice()){
            this.myStocks.add(stock);
            balance -= stock.getCurrentPrice();
        }
    }

    public void sellStock(Stock stock){
        for(Stock s: myStocks){
            if(s.getTicker().equals(stock.getTicker())){
                this.myStocks.remove(stock);
                balance += stock.getCurrentPrice();
                return;
            }
        }
    }

    public int getNStockByTicker(String ticker){
        int total = 0;
        for (Stock stock : myStocks) {
            if(stock.getTicker().equals(ticker)){
                total += 1;
            }
        }
        return total;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<Stock> getMyStocks() {
        return myStocks;
    }
}
