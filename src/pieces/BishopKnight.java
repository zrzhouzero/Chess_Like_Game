package pieces;

import game.Cell;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class BishopKnight extends Piece{

    public BishopKnight(boolean player) {
        super(player);
        super.setSplit(true);
        super.setIsKnight();
        super.setIsBishop();
        super.setPieceType(PieceType.BISHOP_KNIGHT);
    }

    @Override
    public ArrayList<Cell> checkValidMove(Cell[][] board, int x, int y) {

        this.resetAvailableCell();
        this.addValidBishopMove(board, x, y);
        this.addValidKnightMove(board, x, y);
        this.removeInvalidBishopMerge();
        this.removeInvalidKnightMerge();
        return this.availableCell;

    }

    @Override
    public Image getImage() {
        if (this.getPlayer()) {
            return new Image(getClass().getResourceAsStream("/Source/KBB.png"));
        } else {
            return new Image(getClass().getResourceAsStream("/Source/KBW.png"));
        }
    }
}
