package pieces;

import game.Cell;
import javafx.scene.image.Image;

import java.util.ArrayList;

public abstract class Piece {

    ArrayList<Cell> availableCell = new ArrayList<>();
    private boolean player;
    public enum PieceType {
        ROOK, BISHOP, KNIGHT, BISHOP_KNIGHT, KNIGHT_ROOK, ROOK_BISHOP, QUEEN
    }
    private PieceType pieceType;
    private boolean isSplit;
    private boolean isRook = false;
    private boolean isKnight = false;
    private boolean isBishop = false;

    void setIsRook() {
        this.isRook = true;
    }

    void setIsKnight() {
        this.isKnight = true;
    }

    void setIsBishop() {
        this.isBishop = true;
    }

    public boolean getIsRook() {
        return this.isRook;
    }

    public boolean getIsKnight() {
        return this.isKnight;
    }

    public boolean getIsBishop() {
        return this.isBishop;
    }

    void setSplit(boolean split) {
        this.isSplit = split;
    }

    public boolean getSplit() {
        return this.isSplit;
    }

    void setPieceType(PieceType pieceType) {
        this.pieceType = pieceType;
    }

    public PieceType getPieceType() {
        return this.pieceType;
    }

    public Piece(boolean player) {
        this.player = player;
    }

    public boolean getPlayer() {
        return this.player;
    }

    public abstract ArrayList<Cell> checkValidMove(Cell board[][], int x, int y);

    void resetAvailableCell() {
        this.availableCell.clear();
    }

    public abstract Image getImage();

    void addValidRookMove(Cell board[][], int x, int y) {
        int rTempX, rTempY;
        rTempX = x + 1;
        rTempY = y;
        while (rTempX < 6 && (rTempX - x) <= 2) {
            if (board[rTempX][rTempY].getPiece() == null) {
                this.availableCell.add(board[rTempX][rTempY]);
            }
            else {
                if (board[rTempX][rTempY].getPiece().getPlayer() == this.getPlayer() && board[rTempX][rTempY].getPiece().getPieceType() == this.getPieceType()) {
                    break;
                }
                this.availableCell.add(board[rTempX][rTempY]);
                break;
            }
            rTempX++;
        }
        rTempX = x - 1;
        rTempY = y;
        while (rTempX >= 0 && (x - rTempX) <= 2) {
            if (board[rTempX][rTempY].getPiece() == null) {
                this.availableCell.add(board[rTempX][rTempY]);
            }
            else {
                if (board[rTempX][rTempY].getPiece().getPlayer() == this.getPlayer() && board[rTempX][rTempY].getPiece().getPieceType() == this.getPieceType()) {
                    break;
                }
                this.availableCell.add(board[rTempX][rTempY]);
                break;
            }
            rTempX--;
        }
        rTempX = x;
        rTempY = y - 1;
        while (rTempY >= 0 && (y - rTempY) <= 2) {
            if (board[rTempX][rTempY].getPiece() == null) {
                this.availableCell.add(board[rTempX][rTempY]);
            }
            else {
                if (board[rTempX][rTempY].getPiece().getPlayer() == this.getPlayer() && board[rTempX][rTempY].getPiece().getPieceType() == this.getPieceType()) {
                    break;
                }
                this.availableCell.add(board[rTempX][rTempY]);
                break;
            }
            rTempY--;
        }
        rTempX = x;
        rTempY = y + 1;
        while (rTempY < 6 && (rTempY - y) <= 2) {
            if (board[rTempX][rTempY].getPiece() == null) {
                this.availableCell.add(board[rTempX][rTempY]);
            }
            else {
                if (board[rTempX][rTempY].getPiece().getPlayer() == this.getPlayer() && board[rTempX][rTempY].getPiece().getPieceType() == this.getPieceType()) {
                    break;
                }
                this.availableCell.add(board[rTempX][rTempY]);
                break;
            }
            rTempY++;
        }
    }

    void addValidKnightMove(Cell board[][], int x, int y) {
        int[] kTempX = {x - 2, x - 2, x - 1, x - 1, x + 1, x + 1, x + 2, x + 2};
        int[] kTempY = {y + 1, y - 1, y - 2, y + 2, y - 2, y + 2, y + 1, y - 1};
        int i = 0;
        while (i < 8) {
            if (kTempX[i] >= 0 && kTempX[i] < 6 && kTempY[i] >= 0 && kTempY[i] < 6) {
                if (board[kTempX[i]][kTempY[i]].getPiece() == null) {
                    this.availableCell.add(board[kTempX[i]][kTempY[i]]);
                }
                else {
                    if ((board[kTempX[i]][kTempY[i]].getPiece().getPieceType() != this.getPieceType()) || (board[kTempX[i]][kTempY[i]].getPiece().getPlayer() != this.getPlayer())) {
                        this.availableCell.add(board[kTempX[i]][kTempY[i]]);
                    }
                }
            }
            i++;
        }
    }

    void addValidBishopMove(Cell board[][], int x, int y) {
        int bTempX, bTempY;
        bTempX = x + 1;
        bTempY = y + 1;
        while (bTempX < 6 && bTempY < 6 && (bTempX - x) <= 2 && (bTempY - y) <= 2) {
            if (board[bTempX][bTempY].getPiece() == null) {
                this.availableCell.add(board[bTempX][bTempY]);
            }
            else {
                this.availableCell.add(board[bTempX][bTempY]);
                break;
            }
            bTempX++;
            bTempY++;
        }
        bTempX = x - 1;
        bTempY = y + 1;
        while (bTempX >= 0 && bTempY < 6 && (x - bTempX) <= 2 && (bTempY - y) <= 2) {
            if (board[bTempX][bTempY].getPiece() == null) {
                this.availableCell.add(board[bTempX][bTempY]);
            }
            else {
                this.availableCell.add(board[bTempX][bTempY]);
                break;
            }
            bTempX--;
            bTempY++;
        }
        bTempX = x - 1;
        bTempY = y - 1;
        while (bTempX >= 0 && bTempY >= 0 && (x - bTempX) <= 2 && (y - bTempY) <= 2) {
            if (board[bTempX][bTempY].getPiece() == null) {
                this.availableCell.add(board[bTempX][bTempY]);
            }
            else {
                this.availableCell.add(board[bTempX][bTempY]);
                break;
            }
            bTempX--;
            bTempY--;
        }
        bTempX = x + 1;
        bTempY = y - 1;
        while (bTempX < 6 && bTempY >= 0 && (bTempX - x) <= 2 && (y - bTempY) <= 2) {
            if (board[bTempX][bTempY].getPiece() == null) {
                this.availableCell.add(board[bTempX][bTempY]);
            }
            else {
                this.availableCell.add(board[bTempX][bTempY]);
                break;
            }
            bTempX++;
            bTempY--;
        }
    }

    void removeInvalidRookMerge() {

        int i = 0;
        int end = this.availableCell.size();
        while (i < end) {
            if (this.availableCell.get(i).getPiece() == null) {
                i++;
                continue;
            }
            if ((this.player == this.availableCell.get(i).getPiece().getPlayer()) && ((this.availableCell.get(i).getPiece().getPieceType() == PieceType.ROOK) || (this.availableCell.get(i).getPiece().getPieceType() == PieceType.ROOK_BISHOP) || (this.availableCell.get(i).getPiece().getPieceType() == PieceType.KNIGHT_ROOK) || (this.availableCell.get(i).getPiece().getPieceType() == PieceType.QUEEN))) {
                this.availableCell.remove(i);
                end--;
            } else {
                i++;
            }
        }
    }

    void removeInvalidBishopMerge() {

        int i = 0;
        int end = this.availableCell.size();
        while (i < end) {
            if (this.availableCell.get(i).getPiece() == null) {
                i++;
                continue;
            }
            if ((this.player == this.availableCell.get(i).getPiece().getPlayer()) && ((this.availableCell.get(i).getPiece().getPieceType() == PieceType.BISHOP) || (this.availableCell.get(i).getPiece().getPieceType() == PieceType.ROOK_BISHOP) || (this.availableCell.get(i).getPiece().getPieceType() == PieceType.BISHOP_KNIGHT) || (this.availableCell.get(i).getPiece().getPieceType() == PieceType.QUEEN))) {
                this.availableCell.remove(i);
                end--;
            } else {
                i++;
            }
        }
    }

    void removeInvalidKnightMerge() {

        int i = 0;
        int end = this.availableCell.size();
        while (i < end) {
            if (this.availableCell.get(i).getPiece() == null) {
                i++;
                continue;
            }
            if ((this.player == this.availableCell.get(i).getPiece().getPlayer()) && ((this.availableCell.get(i).getPiece().getPieceType() == PieceType.KNIGHT) || (this.availableCell.get(i).getPiece().getPieceType() == PieceType.KNIGHT_ROOK) || (this.availableCell.get(i).getPiece().getPieceType() == PieceType.BISHOP_KNIGHT) || (this.availableCell.get(i).getPiece().getPieceType() == PieceType.QUEEN))) {
                this.availableCell.remove(i);
                end--;
            } else {
                i++;
            }
        }
    }

}