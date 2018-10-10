package game;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pieces.*;
import utilities.Pair;

public class Cell extends Button {

    private Pair coordinate;
    private Piece piece;
    private Image icon;

    Cell(int x, int y) {

        this.coordinate = new Pair(x, y);
        if ((x + y) % 2 == 0) {
            this.setStyle("-fx-background-color: #D8EFFA");
        }
        else {
            this.setStyle("-fx-background-color: #92DADB");
        }
    }

    public Piece getPiece() {
        return this.piece;
    }

    Pair getCoordinate() {
        return this.coordinate;
    }

    void setPiece(Piece piece) {
        this.piece = piece;
    }

    void removePiece() {
        this.piece = null;
    }

    void setIcon(Image image) {
        this.setGraphic(new ImageView(image));
        this.icon = image;
    }

    Image getIcon() {
        return this.icon;
    }

}
