package pieces;

import game.Cell;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Rook extends Piece {

    public Rook(boolean player) {
        super(player);
        super.setSplit(false);
        super.setIsRook();
        super.setPieceType(PieceType.ROOK);
    }

    @Override
    public ArrayList<Cell> checkValidMove(Cell[][] board, int x, int y) {

        this.resetAvailableCell();
        this.addValidRookMove(board, x, y);
        this.removeInvalidRookMerge();
        return this.availableCell;

    }

    @Override
    public Image getImage() {
        if (this.getPlayer()) {
            return new Image(getClass().getResourceAsStream("/Source/RB.png"));
        } else {
            return new Image(getClass().getResourceAsStream("/Source/RW.png"));
        }
    }
}
