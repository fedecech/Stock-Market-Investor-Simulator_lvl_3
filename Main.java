import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.File;



public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Market market = null;
        if(new File("market.market").exists()){
            market = Market.fromFile();
        }else{
            market = new Market();
            market.setTopStocks();
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        LoginController controller = new LoginController(market);

        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}
