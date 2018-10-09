package pieces;

import game.Cell;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class RookBishop extends Piece {

    public RookBishop(boolean player) {
        super(player);
        super.setSplit(true);
        super.setIsBishop();
        super.setIsRook();
        super.setPieceType(PieceType.ROOK_BISHOP);
    }

    @Override
    public ArrayList<Cell> checkValidMove(Cell[][] board, int x, int y) {

        this.resetAvailableCell();
        this.addValidBishopMove(board, x, y);
        this.addValidRookMove(board, x, y);
        this.removeInvalidRookMerge();
        this.removeInvalidBishopMerge();
        return this.availableCell;

    }

    @Override
    public Image getImage() {
        if (this.getPlayer()) {
            return new Image(getClass().getResourceAsStream("/Source/RBB.png"));
        } else {
            return new Image(getClass().getResourceAsStream("/Source/RBW.png"));
        }
    }
}
