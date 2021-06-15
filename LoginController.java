import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.fxml.FXML;

public class LoginController {

    private Market market;

    public LoginController(Market market) {
        this.market = market;
    }

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Label registerButton;

    @FXML
    private Button loginButton;

    @FXML
    private Label errorLabel;

    @FXML
    void goToRegister(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Signup.fxml"));
        loader.setController(new SignupController(market));
        Parent root = loader.load();

        Stage stage = (Stage) loginButton.getScene().getWindow();
        Scene registerScene = new Scene(root);
        stage.setScene(registerScene);
        stage.show();
    }

    @FXML
    void login(MouseEvent event) throws IOException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        Account account = market.findAccountByUsername(username);
        if(account == null){
            errorLabel.setText("User not found");
            return;
        }else {
            if(account.signIn(username, password)){

                //redirect to home
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
                HomeController homeController = new HomeController(account, market);
                loader.setController(new HomeController(account, market));
                Parent root = loader.load();
                Stage stage = (Stage)loginButton.getScene().getWindow();
                Scene homeScene = new Scene(root);
                stage.setScene(homeScene);
                stage.setOnHidden(e -> homeController.saveState());
                stage.show();
            }else{
                errorLabel.setText("Wrong password");
                return;
            }
        }
    }
}
