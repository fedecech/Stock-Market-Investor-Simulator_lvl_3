 

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Market implements Serializable {
    private ArrayList<Stock> stocks;
    private ArrayList<Account> investors;

    public Market(ArrayList<Stock> stocks, ArrayList<Account> investors){
        this.stocks = stocks;
        this.investors = investors;
    }

    public Market(){
        this.stocks = new ArrayList<>();
        this.investors = new ArrayList<>();
    }

    /************* FILE IO ***************/
    public static Market fromFile(){
        Market market = null;
        try{
            FileInputStream file = new FileInputStream("market.market");
            ObjectInputStream in = new ObjectInputStream(file);

            market = (Market) in.readObject();

            in.close();
            file.close();
        }catch (IOException | ClassNotFoundException e){
            System.out.println("Could not read market from file");
        }
        return market;
    }

    public void toFile(){
        try{
            FileOutputStream file = new FileOutputStream("market.market");
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(this);

            out.close();
            file.close();

        }catch(IOException e){
            System.out.println("Could not write market to file");
        }
    }

    /************ CRUD investors ***************/
    public void addInvestor(Account investor){
        this.investors.add(investor);
    }

    public void removeInvestor(Account investor){
        this.investors.remove(investor);
    }

    /************ CRUD stocks ***************/
    public void addStock(Stock stock){
        this.stocks.add(stock);
    }

    public void removeStock(Stock stock){
        this.stocks.remove(stock);
    }


    public Account findAccountByUsername(String username){
        for (Account investor : this.investors) {
            if(investor.getUsername().equals(username)){
                return investor;
            }
        }
        return null;
    }

    public ArrayList<Account> getInvestors() {
        return investors;
    }

    public ArrayList<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(ArrayList<Stock> stocks) {
        this.stocks = stocks;
    }

    public void setTopStocks() throws Exception {
        APICaller apiCaller = new APICaller("tickers");
        ArrayList<Stock> stocks = apiCaller.call(Stock[].class);

        for (Stock stock : stocks) {
            HashMap<String, String> options = new HashMap<>();
            options.put("symbols", stock.getTicker());

            apiCaller.setUrl("eod", options);
            System.out.println(apiCaller.getUrl());
            stock.setPrices(apiCaller.<Price>call(Price[].class));
        }
        setStocks(stocks);
    }
}
