package game;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

class LoginPage extends VBox {

    private Button login = new Button("Login");
    private Button register = new Button("Register");
    private TextField userName = new TextField();
    private PasswordField password = new PasswordField();
    private Label label;

    LoginPage(String s) {

        this.setAlignment(Pos.CENTER);
        this.setMaxSize(220, 300);
        this.setMinSize(220, 300);
        this.setStyle("-fx-background-color: #92D9DC; -fx-background-radius: 50; -fx-spacing: 10");

        this.label = new Label(s);

        this.userName.setPromptText("Enter User Name");
        this.password.setPromptText("Enter Password");

        this.userName.setAlignment(Pos.CENTER);
        this.password.setAlignment(Pos.CENTER);
        label.setAlignment(Pos.CENTER);
        this.login.setAlignment(Pos.CENTER);
        this.register.setAlignment(Pos.CENTER);

        label.setStyle("-fx-font-family: Monaco; -fx-font-size: 10; -fx-text-fill: #1B2B6D; -fx-background-color: #F19EC2; -fx-background-radius: 30");
        label.setMaxSize(150, 40);
        label.setMinSize(150, 40);

        this.userName.setStyle("-fx-font-family: Monaco; -fx-font-size: 10; -fx-text-fill: #1B2B6D; -fx-background-color: #F19EC2; -fx-background-radius: 30");
        this.userName.setMaxSize(150, 40);
        this.userName.setMinSize(150, 40);

        this.password.setStyle("-fx-font-family: Monaco; -fx-font-size: 10; -fx-text-fill: #1B2B6D; -fx-background-color: #F19EC2; -fx-background-radius: 30");
        this.password.setMaxSize(150, 40);
        this.password.setMinSize(150, 40);

        this.login.setStyle("-fx-font-family: Monaco; -fx-font-size: 10; -fx-text-fill: #1B2B6D; -fx-background-color: #F19EC2; -fx-background-radius: 30");
        this.login.setMaxSize(150, 40);
        this.login.setMinSize(150, 40);

        this.register.setStyle("-fx-font-family: Monaco; -fx-font-size: 10; -fx-text-fill: #1B2B6D; -fx-background-color: #F19EC2; -fx-background-radius: 30");
        this.register.setMaxSize(150, 40);
        this.register.setMinSize(150, 40);

        this.getChildren().addAll(label, this.userName, this.password, this.login, this.register);

    }

    Button getLogin() {
        return this.login;
    }

    PasswordField getPassword() {
        return this.password;
    }

    TextField getUserName() {
        return this.userName;
    }

    Button getRegister() {
        return this.register;
    }

    void loginSuccessful(String s) {
        this.getChildren().clear();
        Label label = new Label(s);
        label.setStyle("-fx-font-family: Monaco; -fx-font-size: 18; -fx-text-fill: #1B2B6D");
        this.getChildren().add(label);
    }

    void logOutSuccessful() {
        this.getChildren().clear();
        this.getChildren().addAll(this.label, this.userName, this.password, this.login, this.register);
    }

}
