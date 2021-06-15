 

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;

public class SignupController {

    private Market market;

    public SignupController(Market market){
        this.market = market;
    }

    @FXML
    private Label errorLabel;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField confirmPswTextField;

    @FXML
    private Button registerButton;

    @FXML
    private Label loginButton;

    @FXML
    private CheckBox adminToggle;

    @FXML
    private TextField adminKey;

    @FXML
    private TextField adminUsername;

    @FXML
    void register(MouseEvent event) throws IOException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String confirmPsw = confirmPswTextField.getText();

        Account account = market.findAccountByUsername(username);

        if(account != null){
            errorLabel.setText("Username already in use");
            return;
        }

        if(password.equals(confirmPsw)){
            Account newAccount = null;
            if(adminToggle.isSelected()){

                if(!adminKey.getText().equals("ADMIN")){
                    Account refAccount = market.findAccountByUsername(adminUsername.getText());
                    if(refAccount != null && refAccount instanceof Admin && ((Admin) refAccount).getKey().equals(adminKey.getText())){
                        newAccount = new Admin(username, password);
                    }else {
                        errorLabel.setText("Admin credential are incorrect");
                    }   return;
                }else{
                    newAccount = new Admin(username, password);
                }
            }else {
                newAccount =  new User(username, password);
            }

            market.addInvestor(newAccount);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
            HomeController homeController = new HomeController(newAccount, market);
            loader.setController(homeController);
            Parent root = loader.load();
            Stage stage = (Stage)registerButton.getScene().getWindow();
            Scene homeScene = new Scene(root);
            stage.setScene(homeScene);
            stage.setOnHidden(e -> homeController.saveState());
            stage.show();
        }else {
            errorLabel.setText("Password do not match");
        }
    }

    @FXML
    void goToLogin(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        loader.setController(new LoginController(market));
        Parent root = loader.load();

        Stage stage = (Stage) loginButton.getScene().getWindow();
        Scene registerScene = new Scene(root);
        stage.setScene(registerScene);
        stage.show();
    }

    @FXML
    void changeAdminSelection(){
        adminKey.setVisible(adminToggle.isSelected());
        adminUsername.setVisible(adminToggle.isSelected());
    }

}
