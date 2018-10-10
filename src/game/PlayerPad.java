package game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

class PlayerPad extends VBox {

    private Player player;
    private Label currentPointLabel = new Label();
    private Label totalPointLabel = new Label();
    private Label currentTurn = new Label();

    PlayerPad(Player player) {

        this.player = player;

        this.setMaxSize(180, 190);
        this.setMinSize(180, 190);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(20, 10, 10, 20));
        this.setSpacing(10);
        this.setStyle("-fx-border-color: rgb(146, 217, 220); -fx-border-radius: 5; -fx-border-width: 2; -fx-opacity: 0.9; -fx-background-color: #D8EFFA; -fx-background-radius: 5");

        Label nameLabel = new Label();
        nameLabel.setText(this.player.getAccountId());
        this.currentPointLabel.setText(String.valueOf(this.player.getCurrentPoint()));
        this.totalPointLabel.setText(String.valueOf(this.player.getTotalPoint()));
        this.currentTurn.setText("Your Turn");

        nameLabel.setPrefHeight(30);
        this.currentPointLabel.setPrefHeight(30);
        this.totalPointLabel.setPrefHeight(30);
        this.currentTurn.setPrefHeight(30);

        nameLabel.setStyle("-fx-opacity: 0.9; -fx-font-size: 15;-fx-text-fill: #6A8C8F; -fx-font-family: Monaco");
        this.currentPointLabel.setStyle("-fx-opacity: 0.9; -fx-font-size: 15;-fx-text-fill: #6A8C8F; -fx-font-family: Monaco");
        this.totalPointLabel.setStyle("-fx-opacity: 0.9; -fx-font-size: 15;-fx-text-fill: #6A8C8F; -fx-font-family: Monaco");

        if (player.getPlayerInGame()) {
            this.currentTurn.setStyle("-fx-opacity: 0.9; -fx-font-size: 15;-fx-text-fill: #000000; -fx-font-family: Monaco");
        } else {
            this.currentTurn.setStyle("-fx-opacity: 0.9; -fx-font-size: 15;-fx-text-fill: #FFFFFF; -fx-font-family: Monaco");
        }

        this.getChildren().addAll(nameLabel, this.currentPointLabel, this.totalPointLabel, this.currentTurn);

    }

    void refreshCurrentPointLabel() {
        this.currentPointLabel.setText(String.valueOf(this.player.getCurrentPoint()));
    }

    void resetCurrentPointLabel() {
        this.currentPointLabel.setText(String.valueOf(0));
    }

    void refreshTotalPointLabel() {
        this.totalPointLabel.setText(String.valueOf(this.player.getTotalPoint()));
    }

    void setCurrentTurn(boolean player) {
        this.currentTurn.setVisible(player);
    }

}
