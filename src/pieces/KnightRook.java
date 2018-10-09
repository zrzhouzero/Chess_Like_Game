package pieces;

import game.Cell;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class KnightRook extends Piece {

    public KnightRook(boolean player) {
        super(player);
        super.setSplit(true);
        super.setIsKnight();
        super.setIsRook();
        super.setPieceType(PieceType.KNIGHT_ROOK);
    }

    @Override
    public ArrayList<Cell> checkValidMove(Cell[][] board, int x, int y) {

        this.resetAvailableCell();
        this.addValidKnightMove(board, x, y);
        this.addValidRookMove(board, x, y);
        this.removeInvalidKnightMerge();
        this.removeInvalidRookMerge();
        return this.availableCell;

    }

    @Override
    public Image getImage() {
        if (this.getPlayer()) {
            return new Image(getClass().getResourceAsStream("/Source/RKB.png"));
        } else {
            return new Image(getClass().getResourceAsStream("/Source/RKW.png"));
        }
    }
}
