import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    private Account currentUser;
    private Market market;

    public HomeController(Account currentUser, Market market){
        this.currentUser = currentUser;
        this.market = market;
    }

    @FXML
    private Label username;

    @FXML
    private Label balance;

    @FXML
    private Label stockTitle;

    @FXML
    private TextField key;

    @FXML
    private ListView<String> stocksList;

    @FXML
    private Button buyStockButton;

    @FXML
    private Button sellStockButton;

    @FXML
    private Label ownedStock;

    @FXML
    private LineChart<String, Double> stockChart;

    @FXML
    private SplitPane stocksPane;

    @FXML
    private SplitPane accountsPane;

    @FXML
    private ListView<String> accountsList;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label totalStocksLabel;

    @FXML
    private Button deleteButton;

    @FXML
    private Button blockButton;

    @FXML
    void buyStock(MouseEvent event){
        Stock selected = market.getStocks().get(stocksList.getSelectionModel().getSelectedIndex());
        ((User)currentUser).buyStock(selected);
        setStockDetails(selected);
    }

    @FXML
    void sellStock(MouseEvent event){
        Stock selected = market.getStocks().get(stocksList.getSelectionModel().getSelectedIndex());
        ((User)currentUser).sellStock(selected);
        setStockDetails(selected);
    }

    @FXML
    void deleteAccount(MouseEvent event){
        int selectedIndex = accountsList.getSelectionModel().getSelectedIndex();
        Account selected = market.getInvestors().get(selectedIndex);
        market.removeInvestor(selected);
        updateAccountList();
        accountsList.getSelectionModel().select(0 );
        setAccountDetails(market.getInvestors().get(0));
    }

    @FXML
    void blockAccount(MouseEvent event){
        int selectedIndex = accountsList.getSelectionModel().getSelectedIndex();
        Account selected = market.getInvestors().get(selectedIndex);
        market.removeInvestor(selected);
        updateAccountList();
        accountsList.getSelectionModel().select(0 );
        setAccountDetails(market.getInvestors().get(0));
    }

    @FXML
    void changeStockSelected(MouseEvent event){
        setStockDetails(market.getStocks().get(stocksList.getSelectionModel().getSelectedIndex()));
        setStockChart(market.getStocks().get(stocksList.getSelectionModel().getSelectedIndex()));
    }

    @FXML
    void changeAccountSelected(MouseEvent event){
        setAccountDetails(market.getInvestors().get(accountsList.getSelectionModel().getSelectedIndex()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username.setText(currentUser.getUsername().toUpperCase());
        if(currentUser instanceof Admin){
            stocksPane.setVisible(false);
            key.setText(((Admin)currentUser).getKey());
            updateAccountList();
            setAccountDetails(market.getInvestors().get(0));
        }else{
            accountsPane.setVisible(false);
            balance.setText("$" + String.format( "%.3f",((User) currentUser).getBalance()));
            key.setVisible(false);
            updateStockList();
            setStockDetails(market.getStocks().get(0));
            setStockChart(market.getStocks().get(0));
        }
    }

    public void saveState(){
        market.toFile();
    }

    public void setStockDetails(Stock stock){
        stockTitle.setText(stock.getCompany() + " (" + stock.getTicker() + ")");
        balance.setText("$" + String.format( "%.3f",((User) currentUser).getBalance()));
        buyStockButton.setText("Buy " + stock.getTicker());
        sellStockButton.setText("Sell " + stock.getTicker());
        ownedStock.setText("Owned: " + ((User)currentUser).getNStockByTicker(stock.getTicker()));
    }

    public void setAccountDetails(Account account){
        usernameLabel.setText(account.getUsername());
        if(account instanceof User) {
            totalStocksLabel.setText("Total stocks: " + ((User) account).getMyStocks().size());
        }else {
            totalStocksLabel.setVisible(false);
        }
        deleteButton.setText("Delete " + account.getUsername());
        blockButton.setText("Block " + account.getUsername());
    }

    public void updateAccountList(){
        ObservableList<String> usernames = FXCollections.observableArrayList();
        for(Account account: market.getInvestors()){
            usernames.add(account.getUsername());
        }
        accountsList.setItems(usernames);
        accountsList.getSelectionModel().select(0);
    }

    public void updateStockList(){
        ObservableList<String> tickers = FXCollections.observableArrayList();
        for(Stock stock: market.getStocks()){
            tickers.add(stock.getTicker());
        }
        stocksList.setItems(tickers);
        stocksList.getSelectionModel().select(0);
    }

    public void setStockChart(Stock stock){
        XYChart.Series series = new XYChart.Series();
        for (Price price: stock.getPrices()){
            series.getData().add(new XYChart.Data(price.getDate().toString().substring(4,7), price.getClose()));
        }
        stockChart.getData().setAll(series);
    }
}


