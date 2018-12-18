package pieces;

import game.Cell;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Bishop extends Piece {

    public Bishop(boolean player) {
        super(player);
        super.setSplit(false);
        super.setIsBishop();
        super.setPieceType(PieceType.BISHOP);
    }

    @Override
    public ArrayList<Cell> checkValidMove(Cell[][] board, int x, int y) {

        this.resetAvailableCell();
        this.addValidBishopMove(board, x, y);
        this.removeInvalidBishopMerge();
        return this.availableCell;

    }

    @Override
    public Image getImage() {
        if (this.getPlayer()) {
            return new Image(getClass().getResourceAsStream("/Source/BB.png"));
        } else {
            return new Image(getClass().getResourceAsStream("/Source/BW.png"));
        }
    }
}
