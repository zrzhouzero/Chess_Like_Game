package run;

import game.GameWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utilities.ExitBox;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        GameWindow gameWindow = new GameWindow();
        primaryStage.setTitle("Chess-Like Game");
        final int WIDTH = 1000;
        final int HEIGHT = 600;
        primaryStage.setScene(new Scene(gameWindow, WIDTH, HEIGHT));
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(e -> {
            gameWindow.exportPlayerData();
            e.consume();
            new ExitBox();
        });
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
