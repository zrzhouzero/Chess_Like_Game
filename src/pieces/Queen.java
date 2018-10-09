package pieces;

import game.Cell;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Queen extends Piece {

    public Queen(boolean player) {
        super(player);
        super.setSplit(true);
        super.setIsBishop();
        super.setIsKnight();
        super.setIsRook();
        super.setPieceType(PieceType.QUEEN);
    }

    @Override
    public ArrayList<Cell> checkValidMove(Cell[][] board, int x, int y) {

        this.resetAvailableCell();
        this.addValidBishopMove(board, x, y);
        this.addValidKnightMove(board, x, y);
        this.addValidRookMove(board, x, y);
        this.removeInvalidBishopMerge();
        this.removeInvalidKnightMerge();
        this.removeInvalidRookMerge();
        return this.availableCell;

    }

    @Override
    public Image getImage() {
        if (this.getPlayer()) {
            return new Image(getClass().getResourceAsStream("/Source/RKBB.png"));
        } else {
            return new Image(getClass().getResourceAsStream("/Source/BRKW.png"));
        }
    }
}
