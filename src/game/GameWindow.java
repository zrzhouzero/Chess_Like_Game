package game;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import utilities.TipWindow;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class GameWindow extends Pane {

    private ArrayList<Player> playerList = new ArrayList<>();
    private Board gameBoard = new Board();

    public GameWindow() {

        this.importPlayerData();

        this.setStyle("-fx-background-image: url(/Source/Background.jpg); -fx-background-size: cover");

        HBox gameWindow = new HBox();
        gameWindow.setAlignment(Pos.CENTER);
        gameWindow.setMinSize(1000, 600);
        gameWindow.setMaxSize(1000, 600);

        HBox chessZone = new HBox();
        chessZone.setAlignment(Pos.CENTER);
        chessZone.setMinSize(600, 600);
        chessZone.setMaxSize(600, 600);

        HBox leftZone = new HBox();
        leftZone.setAlignment(Pos.CENTER);
        leftZone.setMinSize(200, 600);
        leftZone.setMaxSize(200, 600);

        HBox rightZone = new HBox();
        rightZone.setAlignment(Pos.CENTER);
        rightZone.setMinSize(200, 600);
        rightZone.setMaxSize(200, 600);

        HBox chessBorder = new HBox();
        chessBorder.setAlignment(Pos.CENTER);
        chessBorder.setMinSize(580, 580);
        chessBorder.setMaxSize(580, 580);
        chessBorder.setStyle("-fx-border-color: rgb(146, 217, 220); -fx-border-radius: 10; -fx-border-width: 3");

        VBox leftBorder = new VBox();
        leftBorder.setAlignment(Pos.CENTER);
        leftBorder.setMinSize(180, 580);
        leftBorder.setMaxSize(180, 580);
        leftBorder.setSpacing(10);
        leftBorder.setStyle("-fx-border-color: rgb(146, 217, 220); -fx-border-radius: 10; -fx-border-width: 3");

        VBox rightBorder = new VBox();
        rightBorder.setAlignment(Pos.CENTER);
        rightBorder.setSpacing(10);
        rightBorder.setMinSize(180, 580);
        rightBorder.setMaxSize(180, 580);
        rightBorder.setStyle("-fx-border-color: rgb(146, 217, 220); -fx-border-radius: 10; -fx-border-width: 3");

        VBox leftUpperPlayerBox = new VBox();
        VBox leftMiddleControlBox = new VBox();
        VBox leftLowerPlayerBox = new VBox();

        leftUpperPlayerBox.setMaxSize(160, 170);
        leftMiddleControlBox.setMaxSize(160, 200);
        leftLowerPlayerBox.setMaxSize(160, 170);
        leftUpperPlayerBox.setMinSize(160, 170);
        leftMiddleControlBox.setMinSize(160, 200);
        leftLowerPlayerBox.setMinSize(160, 170);

        leftUpperPlayerBox.setAlignment(Pos.CENTER);
        leftMiddleControlBox.setAlignment(Pos.CENTER);
        leftLowerPlayerBox.setAlignment(Pos.CENTER);

        leftUpperPlayerBox.setSpacing(10);
        leftMiddleControlBox.setSpacing(10);
        leftLowerPlayerBox.setSpacing(10);

        leftUpperPlayerBox.setPadding(new Insets(10, 10, 10, 10));
        leftMiddleControlBox.setPadding(new Insets(10, 10, 10, 10));
        leftLowerPlayerBox.setPadding(new Insets(10, 10, 10, 10));

        this.gameBoard.initialiseBoard();

        Button restartButton = new Button("Start Game");
        restartButton.setAlignment(Pos.CENTER);
        AtomicBoolean firstStart = new AtomicBoolean(true);
        restartButton.setMaxSize(100, 40);
        restartButton.setMinSize(100, 40);
        restartButton.setStyle("-fx-border-color: rgb(146, 217, 220); -fx-border-radius: 5; -fx-border-width: 2; -fx-opacity: 0.9; -fx-font-size: 12; -fx-background-color: #D8EFFA; -fx-background-radius: 5; -fx-text-fill: #6A8C8F; -fx-font-family: Monaco");
        restartButton.setOnMouseEntered(t -> restartButton.setStyle("-fx-border-color: #5F8C8E; -fx-border-radius: 5; -fx-border-width: 2; -fx-opacity: 0.9; -fx-font-size: 12; -fx-background-color: #D8EFFA; -fx-background-radius: 5; -fx-text-fill: #6A8C8F; -fx-font-family: Monaco"));
        restartButton.setOnMouseExited(t -> restartButton.setStyle("-fx-border-color: rgb(146, 217, 220); -fx-border-radius: 5; -fx-border-width: 2; -fx-opacity: 0.9; -fx-font-size: 12; -fx-background-color: #D8EFFA; -fx-background-radius: 5; -fx-text-fill: #6A8C8F; -fx-font-family: Monaco"));
        restartButton.setOnMousePressed(t -> restartButton.setStyle("-fx-border-color: #5F8C8E; -fx-border-radius: 5; -fx-border-width: 2; -fx-opacity: 0.9; -fx-font-size: 12; -fx-background-color: #659597; -fx-background-radius: 5; -fx-text-fill: #6A8C8F; -fx-font-family: Monaco"));
        restartButton.setOnMouseReleased(t -> restartButton.setStyle("-fx-border-color: rgb(146, 217, 220); -fx-border-radius: 5; -fx-border-width: 2; -fx-opacity: 0.9; -fx-font-size: 12; -fx-background-color: #D8EFFA; -fx-background-radius: 5; -fx-text-fill: #6A8C8F; -fx-font-family: Monaco"));
        restartButton.setOnAction((ActionEvent e) -> {
            if (this.gameBoard.getPlayer1() == null || this.gameBoard.getPlayer2() == null) {
                new TipWindow("Error", "Both players must login before start");
                return;
            }
            if (firstStart.get()) {
                chessBorder.getChildren().clear();
                chessBorder.getChildren().add(this.gameBoard);
                leftUpperPlayerBox.getChildren().clear();
                leftLowerPlayerBox.getChildren().clear();
                leftUpperPlayerBox.getChildren().add(this.gameBoard.getPlayerPad1());
                leftLowerPlayerBox.getChildren().add(this.gameBoard.getPlayerPad2());
                this.gameBoard.initialiseBoard();
                this.gameBoard.setClickAction();
                restartButton.setText("Restart");
                this.resetPlayerPad();
                firstStart.set(false);
            } else {
                ConfirmBox confirmBox = new ConfirmBox("Restart!");
                confirmBox.getConfirmButton().setOnAction(e2 -> {
                    chessBorder.getChildren().clear();
                    chessBorder.getChildren().add(this.gameBoard);
                    leftUpperPlayerBox.getChildren().clear();
                    leftLowerPlayerBox.getChildren().clear();
                    leftUpperPlayerBox.getChildren().add(this.gameBoard.getPlayerPad1());
                    leftLowerPlayerBox.getChildren().add(this.gameBoard.getPlayerPad2());
                    this.gameBoard.initialiseBoard();
                    this.gameBoard.setClickAction();
                    this.resetPlayerPad();
                    confirmBox.close();
                });
                confirmBox.show();
            }
        });

        Button onSplitButton = new Button("Turn On Split");
        onSplitButton.setMaxSize(100, 40);
        onSplitButton.setMinSize(100, 40);
        onSplitButton.setStyle("-fx-border-color: rgb(146, 217, 220); -fx-border-radius: 5; -fx-border-width: 2; -fx-opacity: 0.9; -fx-font-size: 9; -fx-background-color: #D8EFFA; -fx-background-radius: 5; -fx-text-fill: #6A8C8F; -fx-font-family: Monaco");
        onSplitButton.setOnMouseEntered(t -> onSplitButton.setStyle("-fx-border-color: #5F8C8E; -fx-border-radius: 5; -fx-border-width: 2; -fx-opacity: 0.9; -fx-font-size: 9; -fx-background-color: #D8EFFA; -fx-background-radius: 5; -fx-text-fill: #6A8C8F; -fx-font-family: Monaco"));
        onSplitButton.setOnMouseExited(t -> onSplitButton.setStyle("-fx-border-color: rgb(146, 217, 220); -fx-border-radius: 5; -fx-border-width: 2; -fx-opacity: 0.9; -fx-font-size: 9; -fx-background-color: #D8EFFA; -fx-background-radius: 5; -fx-text-fill: #6A8C8F; -fx-font-family: Monaco"));
        onSplitButton.setOnMousePressed(t -> onSplitButton.setStyle("-fx-border-color: #5F8C8E; -fx-border-radius: 5; -fx-border-width: 2; -fx-opacity: 0.9; -fx-font-size: 9; -fx-background-color: #659597; -fx-background-radius: 5; -fx-text-fill: #6A8C8F; -fx-font-family: Monaco"));
        onSplitButton.setOnMouseReleased(t -> onSplitButton.setStyle("-fx-border-color: rgb(146, 217, 220); -fx-border-radius: 5; -fx-border-width: 2; -fx-opacity: 0.9; -fx-font-size: 9; -fx-background-color: #D8EFFA; -fx-background-radius: 5; -fx-text-fill: #6A8C8F; -fx-font-family: Monaco"));
        onSplitButton.setOnAction((ActionEvent e) -> {
            if (this.gameBoard.getOnSplit()) {
                this.gameBoard.turnOffSplit();
                onSplitButton.setText("Turn On Split");
            } else {
                this.gameBoard.turnOnSplit();
                onSplitButton.setText("Turn Off Split");
            }
        });

        Button instructionButton = new Button("Instructions");
        instructionButton.setAlignment(Pos.CENTER);
        instructionButton.setMaxSize(100, 30);
        instructionButton.setMinSize(100, 30);
        instructionButton.setStyle("-fx-border-color: rgb(146, 217, 220); -fx-border-radius: 5; -fx-border-width: 2; -fx-opacity: 0.9; -fx-font-size: 10; -fx-background-color: #D8EFFA; -fx-background-radius: 5; -fx-text-fill: #6A8C8F; -fx-font-family: Monaco");
        instructionButton.setOnMouseEntered(t -> instructionButton.setStyle("-fx-border-color: #5F8C8E; -fx-border-radius: 5; -fx-border-width: 2; -fx-opacity: 0.9; -fx-font-size: 10; -fx-background-color: #D8EFFA; -fx-background-radius: 5; -fx-text-fill: #6A8C8F; -fx-font-family: Monaco"));
        instructionButton.setOnMouseExited(t -> instructionButton.setStyle("-fx-border-color: rgb(146, 217, 220); -fx-border-radius: 5; -fx-border-width: 2; -fx-opacity: 0.9; -fx-font-size: 10; -fx-background-color: #D8EFFA; -fx-background-radius: 5; -fx-text-fill: #6A8C8F; -fx-font-family: Monaco"));
        instructionButton.setOnMousePressed(t -> instructionButton.setStyle("-fx-border-color: #5F8C8E; -fx-border-radius: 5; -fx-border-width: 2; -fx-opacity: 0.9; -fx-font-size: 10; -fx-background-color: #659597; -fx-background-radius: 5; -fx-text-fill: #6A8C8F; -fx-font-family: Monaco"));
        instructionButton.setOnMouseReleased(t -> instructionButton.setStyle("-fx-border-color: rgb(146, 217, 220); -fx-border-radius: 5; -fx-border-width: 2; -fx-opacity: 0.9; -fx-font-size: 10; -fx-background-color: #D8EFFA; -fx-background-radius: 5; -fx-text-fill: #6A8C8F; -fx-font-family: Monaco"));
        instructionButton.setOnAction(e -> {
            InstructionPage instructionPage = new InstructionPage();
            instructionPage.show();
        });

        LoginPage loginPage1 = new LoginPage("Player1 Login");
        LoginPage loginPage2 = new LoginPage("Player2 Login");

        loginPage1.getLogin().setOnAction(e -> {
            int index = this.findUserIndex(loginPage1.getUserName().getText());
            if (index < 0) {
                new TipWindow("System Error", "This player name is not existed.");
                return;
            }
            if (this.gameBoard.getPlayer2() != null) {
                if (this.playerList.get(index).getAccountId().equals(this.gameBoard.getPlayer2().getAccountId())) {
                    new TipWindow("System Error", "This player is already logged in");
                    return;
                }
            }
            if (this.playerList.get(index).validPasswordWrong(loginPage1.getPassword().getText())) {
                new TipWindow("System Error", "The player name does not match password.");
                return;
            }
            this.playerList.get(index).setPlayerInGame(true);
            this.gameBoard.setPlayer1(this.playerList.get(index));
            loginPage1.loginSuccessful(this.playerList.get(index).getAccountId());
        });

        loginPage2.getLogin().setOnAction(e -> {
            int index = this.findUserIndex(loginPage2.getUserName().getText());
            if (index < 0) {
                new TipWindow("System Error", "This player name is not existed.");
                return;
            }
            if (this.gameBoard.getPlayer1() != null) {
                if (this.playerList.get(index).getAccountId().equals(this.gameBoard.getPlayer1().getAccountId())) {
                    new TipWindow("System Error", "This player is already logged in");
                    return;
                }
            }
            if (this.playerList.get(index).validPasswordWrong(loginPage2.getPassword().getText())) {
                new TipWindow("System Error", "The player name does not match password.");
                return;
            }
            this.playerList.get(index).setPlayerInGame(false);
            this.gameBoard.setPlayer2(this.playerList.get(index));
            loginPage2.loginSuccessful(this.playerList.get(index).getAccountId());
        });

        loginPage1.getRegister().setOnAction(e -> {
            String userName = loginPage1.getUserName().getText();
            if (userName == null || userName.equals("")) {
                new TipWindow("System Error", "Please enter player ID.");
                return;
            }
            int index = this.findUserIndex(userName);
            if (index >= 0) {
                new TipWindow("System Error", "This player ID is already in use.");
                return;
            }
            String password = loginPage1.getPassword().getText();
            if (password == null || password.equals("")) {
                new TipWindow("System Error", "Please enter password.");
                return;
            }
            this.playerList.add(new Player(userName, password));
            int i = findUserIndex(userName);
            this.playerList.get(i).setPlayerInGame(true);
            this.gameBoard.setPlayer1(this.playerList.get(i));
            loginPage1.loginSuccessful(this.playerList.get(i).getAccountId());
        });

        loginPage2.getRegister().setOnAction(e -> {
            String userName = loginPage2.getUserName().getText();
            if (userName == null || userName.equals("")) {
                new TipWindow("System Error", "Please enter player ID.");
                return;
            }
            int index = this.findUserIndex(userName);
            if (index >= 0) {
                new TipWindow("System Error", "This player ID is already in use.");
                return;
            }
            String password = loginPage2.getPassword().getText();
            if (password == null || password.equals("")) {
                new TipWindow("System Error", "Please enter password.");
                return;
            }
            this.playerList.add(new Player(userName, password));
            int i = findUserIndex(userName);
            this.playerList.get(i).setPlayerInGame(false);
            this.gameBoard.setPlayer2(this.playerList.get(i));
            loginPage2.loginSuccessful(this.playerList.get(i).getAccountId());
        });


        Button logOut = new Button("Logout");
        logOut.setAlignment(Pos.CENTER);
        logOut.setMaxSize(100, 40);
        logOut.setMinSize(100, 40);
        logOut.setStyle("-fx-border-color: rgb(146, 217, 220); -fx-border-radius: 5; -fx-border-width: 2; -fx-opacity: 0.9; -fx-font-size: 12; -fx-background-color: #D8EFFA; -fx-background-radius: 5; -fx-text-fill: #6A8C8F; -fx-font-family: Monaco");
        logOut.setOnMouseEntered(t -> logOut.setStyle("-fx-border-color: #5F8C8E; -fx-border-radius: 5; -fx-border-width: 2; -fx-opacity: 0.9; -fx-font-size: 12; -fx-background-color: #D8EFFA; -fx-background-radius: 5; -fx-text-fill: #6A8C8F; -fx-font-family: Monaco"));
        logOut.setOnMouseExited(t -> logOut.setStyle("-fx-border-color: rgb(146, 217, 220); -fx-border-radius: 5; -fx-border-width: 2; -fx-opacity: 0.9; -fx-font-size: 12; -fx-background-color: #D8EFFA; -fx-background-radius: 5; -fx-text-fill: #6A8C8F; -fx-font-family: Monaco"));
        logOut.setOnMousePressed(t -> logOut.setStyle("-fx-border-color: #5F8C8E; -fx-border-radius: 5; -fx-border-width: 2; -fx-opacity: 0.9; -fx-font-size: 12; -fx-background-color: #659597; -fx-background-radius: 5; -fx-text-fill: #6A8C8F; -fx-font-family: Monaco"));
        logOut.setOnMouseReleased(t -> logOut.setStyle("-fx-border-color: rgb(146, 217, 220); -fx-border-radius: 5; -fx-border-width: 2; -fx-opacity: 0.9; -fx-font-size: 12; -fx-background-color: #D8EFFA; -fx-background-radius: 5; -fx-text-fill: #6A8C8F; -fx-font-family: Monaco"));
        logOut.setOnAction((ActionEvent e) -> {
            if (this.gameBoard.getPlayer1() == null && this.gameBoard.getPlayer2() == null) {
                return;
            }
            ConfirmBox confirmBox = new ConfirmBox("Log Out!");
            confirmBox.getConfirmButton().setOnAction(e2 -> {
                this.gameBoard.removePlayers();
                leftUpperPlayerBox.getChildren().clear();
                leftLowerPlayerBox.getChildren().clear();
                chessBorder.getChildren().clear();
                chessBorder.getChildren().addAll(loginPage1, loginPage2);
                loginPage1.logOutSuccessful();
                loginPage2.logOutSuccessful();
                confirmBox.close();
            });
            confirmBox.show();
        });

        chessBorder.setSpacing(20);
        chessBorder.getChildren().addAll(loginPage1, loginPage2);

        leftMiddleControlBox.getChildren().addAll(gameBoard.getMaxMoveInputField(), restartButton, instructionButton, logOut);
        leftBorder.getChildren().addAll(leftUpperPlayerBox, leftMiddleControlBox, leftLowerPlayerBox);
        rightBorder.getChildren().add(gameBoard.getOnSplitLabel());
        rightBorder.getChildren().add(onSplitButton);
        rightBorder.getChildren().add(gameBoard.getMaxMoveShowField());
        rightBorder.getChildren().add(gameBoard.getTotalMoveLabel());
        rightBorder.getChildren().add(gameBoard.getWithdrawButton());

        chessZone.getChildren().add(chessBorder);
        leftZone.getChildren().add(leftBorder);
        rightZone.getChildren().add(rightBorder);
        gameWindow.getChildren().addAll(leftZone, chessZone, rightZone);

        this.getChildren().add(gameWindow);

    }

    private void resetPlayerPad() {
        this.gameBoard.getPlayerPad1().resetCurrentPointLabel();
        this.gameBoard.getPlayerPad2().resetCurrentPointLabel();
        this.gameBoard.getPlayerPad1().refreshTotalPointLabel();
        this.gameBoard.getPlayerPad2().refreshTotalPointLabel();
        this.gameBoard.getPlayerPad1().setCurrentTurn(false);
        this.gameBoard.getPlayerPad2().setCurrentTurn(true);
    }

    private void importPlayerData() {
        ArrayList<String> convertList = new ArrayList<>();
        try (Scanner input = new Scanner(new File("src/data/playerdata.txt"))) {
            while (input.hasNextLine()) {
                String str = input.nextLine();
                if (!str.equals("")) {
                    convertList.add(str);
                }
            }
        } catch (FileNotFoundException | NullPointerException e) {
            new TipWindow("System Error", "The save file is lost!");
            return;
        }
        for (String s : convertList) {
            String convertLine[] = s.split(":");
            this.playerList.add(new Player(convertLine[0], convertLine[1], Integer.parseInt(convertLine[2])));
        }

    }

    public void exportPlayerData() {
        File file = new File("src/data/playerdata.txt");
        try {
            PrintWriter output = new PrintWriter(file);
            for (Player player : this.playerList) {
                output.write(player.toString() + "\n");
            }
            output.close();
        } catch (FileNotFoundException | NullPointerException e) {
            new TipWindow("System Error", "The save file is lost!");
        }
    }

    private int findUserIndex(String userName) {
        boolean ifFound = false;
        int i;
        int end = this.playerList.size();
        for (i = 0; i < end; i++) {
            if (this.playerList.get(i).getAccountId().equals(userName)) {
                ifFound = true;
                break;
            }
        }
        if (ifFound) {
            return i;
        }
        return -1;
    }

    class ConfirmBox extends Stage {

        private Button confirmButton = new Button();

        ConfirmBox(String s) {
            this.setHeight(100);
            this.setWidth(300);
            this.setResizable(false);
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            hBox.setSpacing(10);
            Label label = new Label();
            label.setTextAlignment(TextAlignment.CENTER);
            label.setAlignment(Pos.CENTER);
            label.setStyle("-fx-opacity: 0.9; -fx-font-size: 11; -fx-text-fill: #6A8C8F; -fx-font-family: Monaco");
            label.setText("Confirm?");
            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER);
            vBox.setPrefSize(300, 100);
            vBox.setSpacing(10);
            vBox.setStyle("-fx-background-color: #D8EFFA");
            Button button = new Button("Cancel");
            button.setStyle("-fx-border-color: rgb(146, 217, 220); -fx-border-radius: 5; -fx-border-width: 2; -fx-opacity: 0.9; -fx-font-size: 11; -fx-background-color: #D8EFFA; -fx-background-radius: 5; -fx-text-fill: #6A8C8F; -fx-font-family: Monaco");
            button.setAlignment(Pos.CENTER);
            button.setMaxSize(80, 30);
            button.setMinSize(80, 30);
            button.setOnAction(e -> this.close());
            this.confirmButton.setText(s);
            this.confirmButton.setStyle("-fx-border-color: rgb(146, 217, 220); -fx-border-radius: 5; -fx-border-width: 2; -fx-opacity: 0.9; -fx-font-size: 11; -fx-background-color: #D8EFFA; -fx-background-radius: 5; -fx-text-fill: #6A8C8F; -fx-font-family: Monaco");
            this.confirmButton.setAlignment(Pos.CENTER);
            this.confirmButton.setMaxSize(80, 30);
            this.confirmButton.setMinSize(80, 30);
            hBox.getChildren().addAll(button, this.confirmButton);
            vBox.getChildren().addAll(label, hBox);
            this.setScene(new Scene(vBox, 300, 100));
        }

        Button getConfirmButton() {
            return this.confirmButton;
        }
    }
}