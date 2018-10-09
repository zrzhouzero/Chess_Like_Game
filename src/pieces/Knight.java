package pieces;

import game.Cell;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Knight extends Piece {

    public Knight(boolean player) {
        super(player);
        super.setSplit(false);
        super.setIsKnight();
        super.setPieceType(PieceType.KNIGHT);
    }

    @Override
    public ArrayList<Cell> checkValidMove(Cell[][] board, int x, int y) {

        this.resetAvailableCell();
        this.addValidKnightMove(board, x, y);
        this.removeInvalidKnightMerge();
        return this.availableCell;

    }

    @Override
    public Image getImage() {
        if (this.getPlayer()) {
            return new Image(getClass().getResourceAsStream("/Source/KB.png"));
        } else {
            return new Image(getClass().getResourceAsStream("/Source/KW.png"));
        }
    }
}