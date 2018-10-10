package game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

class InstructionPage extends Stage {

    InstructionPage() {

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(20, 10, 10, 10));
        vBox.setStyle("-fx-background-color: #D8EFFA");
        vBox.setPrefSize(500, 500);

        Label label0 = new Label("Chess Game Instructions");
        Label label1 = new Label("1. Both players need to login before starting the game.");
        Label label2 = new Label("2. Need to input the max move and click on \"Set Max Move\" before start, or the max move will be set to default(20).");
        Label label3 = new Label("3. Each player take turns to move an individual piece.");
        Label label4 = new Label("4. Rooks, located in the corner, can only move by two cells horizontally or vertically.");
        Label label5 = new Label("5. Knights, located in the centre, can only move in an L-shape (2 vertically, 1 horizontally) in any direction.");
        Label label6 = new Label("6. Bishops, located between Rooks and Knights can only move by two cells diagonally in any direction.");
        Label label7 = new Label("7. Players can merge their own pieces by landing on the same cell.");
        Label label8 = new Label("8. The merged piece have both the move ability of the two merging piece.");
        Label label9 = new Label("9. The same pieces cannot be merged, as well as the piece contains the same piece.");
        Label label10 = new Label("10. Players can destroy their opponent's pieces by landing on the same cell.");
        Label label11 = new Label("11. Players earns 5 points for every opponent's unmerged piece that is destroyed.");
        Label label12 = new Label("12. The winner is determined by the player with the most points when all turns are completed, or the opponent has no pieces.");

        label0.setTextAlignment(TextAlignment.CENTER);
        label1.setTextAlignment(TextAlignment.LEFT);
        label2.setTextAlignment(TextAlignment.LEFT);
        label3.setTextAlignment(TextAlignment.LEFT);
        label4.setTextAlignment(TextAlignment.LEFT);
        label5.setTextAlignment(TextAlignment.LEFT);
        label6.setTextAlignment(TextAlignment.LEFT);
        label7.setTextAlignment(TextAlignment.LEFT);
        label8.setTextAlignment(TextAlignment.LEFT);
        label9.setTextAlignment(TextAlignment.LEFT);
        label10.setTextAlignment(TextAlignment.LEFT);
        label11.setTextAlignment(TextAlignment.LEFT);
        label12.setTextAlignment(TextAlignment.LEFT);

        label0.setStyle("-fx-font-size: 13; -fx-font-family: Monaco; -fx-text-fill: #6A8C8F");
        label1.setStyle("-fx-font-size: 11; -fx-font-family: Monaco; -fx-text-fill: #6A8C8F");
        label2.setStyle("-fx-font-size: 11; -fx-font-family: Monaco; -fx-text-fill: #6A8C8F");
        label3.setStyle("-fx-font-size: 11; -fx-font-family: Monaco; -fx-text-fill: #6A8C8F");
        label4.setStyle("-fx-font-size: 11; -fx-font-family: Monaco; -fx-text-fill: #6A8C8F");
        label5.setStyle("-fx-font-size: 11; -fx-font-family: Monaco; -fx-text-fill: #6A8C8F");
        label6.setStyle("-fx-font-size: 11; -fx-font-family: Monaco; -fx-text-fill: #6A8C8F");
        label7.setStyle("-fx-font-size: 11; -fx-font-family: Monaco; -fx-text-fill: #6A8C8F");
        label8.setStyle("-fx-font-size: 11; -fx-font-family: Monaco; -fx-text-fill: #6A8C8F");
        label9.setStyle("-fx-font-size: 11; -fx-font-family: Monaco; -fx-text-fill: #6A8C8F");
        label10.setStyle("-fx-font-size: 11; -fx-font-family: Monaco; -fx-text-fill: #6A8C8F");
        label11.setStyle("-fx-font-size: 11; -fx-font-family: Monaco; -fx-text-fill: #6A8C8F");
        label12.setStyle("-fx-font-size: 11; -fx-font-family: Monaco; -fx-text-fill: #6A8C8F");

        label1.setPrefWidth(480);
        label2.setPrefWidth(480);
        label3.setPrefWidth(480);
        label4.setPrefWidth(480);
        label5.setPrefWidth(480);
        label6.setPrefWidth(480);
        label7.setPrefWidth(480);
        label8.setPrefWidth(480);
        label9.setPrefWidth(480);
        label10.setPrefWidth(480);
        label11.setPrefWidth(480);
        label12.setPrefWidth(480);

        label1.setWrapText(true);
        label2.setWrapText(true);
        label3.setWrapText(true);
        label4.setWrapText(true);
        label5.setWrapText(true);
        label6.setWrapText(true);
        label7.setWrapText(true);
        label8.setWrapText(true);
        label9.setWrapText(true);
        label10.setWrapText(true);
        label11.setWrapText(true);
        label12.setWrapText(true);

        vBox.getChildren().addAll(label0, label1, label2, label3, label4, label5, label6, label7, label8, label9, label10, label11, label12);
        this.setHeight(500);
        this.setWidth(500);
        this.setScene(new Scene(vBox, 500, 500));

    }

}
