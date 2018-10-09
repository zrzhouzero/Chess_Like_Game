package game;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pieces.*;
import utilities.Pair;
import java.util.ArrayList;

class Board extends GridPane {

    private Cell board[][] = new Cell[6][6];
    private Piece selectedPiece;
    private ArrayList<Cell> currentAvailableMove;
    private Pair previousPosition;
    private Image previousIcon;
    private Integer totalMove;
    private Label totalMoveLabel = new Label();
    private Integer maxMove;
    private TextField maxMoveInputField = new TextField();
    private TextField maxMoveShowField = new TextField();
    private boolean onSplit;
    private Label onSplitLabel = new Label();
    private Player player1 = null;
    private Player player2 = null;
    private PlayerPad playerPad1 = null;
    private PlayerPad playerPad2 = null;

    //variables for withdraw
    private boolean canWithdraw = false;
    private ArrayList<Pair> withdrawOriginatorPosition = new ArrayList<>();
    private ArrayList<Piece> withdrawOriginatorPiece = new ArrayList<>();
    private ArrayList<Image> withdrawOriginatorIcon = new ArrayList<>();
    private ArrayList<Pair> withdrawTargetPosition = new ArrayList<>();
    private ArrayList<Piece> withdrawTargetPiece = new ArrayList<>();
    private ArrayList<Image> withdrawTargetIcon = new ArrayList<>();
    private Button withdrawButton = new Button();

    PlayerPad getPlayerPad1() {
        return this.playerPad1;
    }

    PlayerPad getPlayerPad2() {
        return this.playerPad2;
    }

    void setPlayer1(Player player1) {
        this.player1 = player1;
        this.playerPad1 = new PlayerPad(player1);
    }

    void setPlayer2(Player player2) {
        this.player2 = player2;
        this.playerPad2 = new PlayerPad(player2);
    }

    void removePlayers() {
        this.player1 = null;
        this.player2 = null;
        this.playerPad1 = null;
        this.playerPad2 = null;
    }

    Player getPlayer1() {
        return this.player1;
    }

    Player getPlayer2() {
        return this.player2;
    }

    boolean getOnSplit() {
        return this.onSplit;
    }

    void turnOnSplit() {
        this.onSplit = true;
        this.onSplitLabel.setText(String.valueOf("Split: ON"));
        this.onSplitLabel.setAlignment(Pos.CENTER);
        this.onSplitLabel.setStyle("-fx-border-color: rgb(146, 217, 220); -fx-border-radius: 5; -fx-border-width: 2; -fx-opacity: 0.9; -fx-font-size: 12; -fx-background-color: #FFFFC8; -fx-background-radius: 5; -fx-text-fill: #CFB53B; -fx-font-family: Monaco");
        this.onSplitLabel.setMinSize(100, 40);
        this.onSplitLabel.setMaxSize(100, 40);
    }

    void turnOffSplit() {
        this.onSplit = false;
        this.onSplitLabel.setText("Split: OFF");
        this.onSplitLabel.setAlignment(Pos.CENTER);
        this.onSplitLabel.setStyle("-fx-border-color: rgb(146, 217, 220); -fx-border-radius: 5; -fx-border-width: 2; -fx-opacity: 0.9; -fx-font-size: 12; -fx-background-color: #D8EFFA; -fx-background-radius: 5; -fx-text-fill: #6A8C8F; -fx-font-family: Monaco");
        this.onSplitLabel.setMinSize(100, 40);
        this.onSplitLabel.setMaxSize(100, 40);
    }

    Label getOnSplitLabel() {
        return this.onSplitLabel;
    }

    Button getWithdrawButton() {
        return this.withdrawButton;
    }

    TextField getMaxMoveInputField() {
        return this.maxMoveInputField;
    }

    private boolean currentTurn;

    private void setMaxMove(int move) {
        this.maxMove = move;
    }

    private int getMaxMove() {
        return this.maxMove;
    }

    Label getTotalMoveLabel() {
        return this.totalMoveLabel;
    }

    TextField getMaxMoveShowField() {
        return this.maxMoveShowField;
    }

    void initialiseBoard() {

        this.setMinSize(560, 560);
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setVgap(10);
        this.setHgap(10);
        this.setAlignment(Pos.CENTER);

        int i, j;
        for (i = 0; i < 6; i++) {
            for (j = 0; j < 6; j++) {
                this.board[i][j] = new Cell(i, j);
                this.board[i][j].setPrefHeight(85);
                this.board[i][j].setPrefWidth(85);
                this.add(this.board[i][j], i, j);
            }
        }

        this.currentTurn = false;

        this.canWithdraw = false;
        this.withdrawOriginatorPosition = new ArrayList<>();
        this.withdrawOriginatorPiece = new ArrayList<>();
        this.withdrawOriginatorIcon = new ArrayList<>();
        this.withdrawTargetPosition = new ArrayList<>();
        this.withdrawTargetPiece = new ArrayList<>();
        this.withdrawTargetIcon = new ArrayList<>();

        this.maxMoveInputField.setStyle("-fx-border-color: rgb(146, 217, 220); -fx-border-radius: 5; -fx-border-width: 2; -fx-opacity: 0.9; -fx-font-size: 11; -fx-background-color: #D8EFFA; -fx-background-radius: 5; -fx-text-fill: #6A8C8F; -fx-font-family: Monaco");
        this.maxMoveInputField.setAlignment(Pos.CENTER);
        this.maxMoveInputField.setMaxSize(100, 40);
        this.maxMoveInputField.setMinSize(100, 40);
        this.maxMoveInputField.setPromptText("Set Max Move");

        try {
            int maxMove = Integer.parseInt(this.maxMoveInputField.getText());
            if (maxMove == 0) {
                this.setMaxMove(20);
            } else {
                this.setMaxMove(maxMove);
            }
        } catch (Exception e) {
            this.setMaxMove(20);
        }

        this.maxMoveShowField.setStyle("-fx-border-color: rgb(146, 217, 220); -fx-border-radius: 5; -fx-border-width: 2; -fx-opacity: 0.9; -fx-font-size: 11; -fx-background-color: #D8EFFA; -fx-background-radius: 5; -fx-text-fill: #6A8C8F; -fx-font-family: Monaco");
        this.maxMoveShowField.setAlignment(Pos.CENTER);
        this.maxMoveShowField.setMaxSize(100, 40);
        this.maxMoveShowField.setMinSize(100, 40);
        this.maxMoveShowField.setText("Max Move: " + String.valueOf(this.maxMove));
        this.maxMoveShowField.setEditable(false);

        this.totalMove = 0;
        this.totalMoveLabel.setAlignment(Pos.CENTER);
        this.totalMoveLabel.setText("Total Move: " + String.valueOf(this.totalMove));
        this.totalMoveLabel.setStyle("-fx-border-color: rgb(146, 217, 220); -fx-border-radius: 5; -fx-border-width: 2; -fx-opacity: 0.9; -fx-font-size: 10; -fx-background-color: #D8EFFA; -fx-background-radius: 5; -fx-text-fill: #6A8C8F; -fx-font-family: Monaco");
        this.totalMoveLabel.setMinSize(100, 40);
        this.totalMoveLabel.setMaxSize(100, 40);

        this.turnOffSplit();

        this.withdrawButton.setText("No Withdraw");
        this.withdrawButton.setAlignment(Pos.CENTER);
        this.withdrawButton.setStyle("-fx-border-color: rgb(146, 217, 220); -fx-border-radius: 5; -fx-border-width: 2; -fx-opacity: 0.9; -fx-font-size: 12; -fx-background-color: #D8EFFA; -fx-background-radius: 5; -fx-text-fill: #6A8C8F; -fx-font-family: Monaco");
        this.withdrawButton.setMinSize(100, 40);
        this.withdrawButton.setMaxSize(100, 40);
        this.withdrawButton.setOnAction(e -> this.withdraw());

        Piece blackRook = new Rook(true);
        Piece blackBishop = new Bishop(true);
        Piece blackKnight = new Knight(true);

        Piece whiteRook = new Rook(false);
        Piece whiteBishop = new Bishop(false);
        Piece whiteKnight = new Knight(false);

        this.board[0][0].setPiece(blackRook);
        this.board[0][0].setIcon(blackRook.getImage());

        this.board[5][0].setPiece(blackRook);
        this.board[5][0].setIcon(blackRook.getImage());

        this.board[0][5].setPiece(whiteRook);
        this.board[0][5].setIcon(whiteRook.getImage());

        this.board[5][5].setPiece(whiteRook);
        this.board[5][5].setIcon(whiteRook.getImage());

        this.board[2][0].setPiece(blackKnight);
        this.board[2][0].setIcon(blackKnight.getImage());

        this.board[3][0].setPiece(blackKnight);
        this.board[3][0].setIcon(blackKnight.getImage());

        this.board[2][5].setPiece(whiteKnight);
        this.board[2][5].setIcon(whiteKnight.getImage());

        this.board[3][5].setPiece(whiteKnight);
        this.board[3][5].setIcon(whiteKnight.getImage());

        this.board[1][0].setPiece(blackBishop);
        this.board[1][0].setIcon(blackBishop.getImage());

        this.board[4][0].setPiece(blackBishop);
        this.board[4][0].setIcon(blackBishop.getImage());

        this.board[1][5].setPiece(whiteBishop);
        this.board[1][5].setIcon(whiteBishop.getImage());

        this.board[4][5].setPiece(whiteBishop);
        this.board[4][5].setIcon(whiteBishop.getImage());

    }

    private void resetBoard() {

        for (int a = 0; a < 6; a++) {
            for (int b = 0; b < 6; b++) {
                if ((a + b) % 2 == 0) {
                    this.board[a][b].setStyle("-fx-background-color: #D8EFFA");
                } else {
                    this.board[a][b].setStyle("-fx-background-color: #92DADB");
                }
            }
        }
    }

    private boolean moveCheck(Cell targetCell, ArrayList<Cell> availableCell) {
        boolean ifFound = false;
        if (availableCell == null) {
            return false;
        }
        for (Cell anAvailableCell : availableCell) {
            if ((targetCell.getCoordinate().getLeft() == anAvailableCell.getCoordinate().getLeft()) && (targetCell.getCoordinate().getRight() == anAvailableCell.getCoordinate().getRight())) {
                ifFound = true;
                break;
            }
        }
        return ifFound;
    }

    /*
     * if there is no piece selected (that is selectedPiece is null), then select the piece
     * if there is one piece selected (that is selectedPiece has value), then check for the next click
     * if the next click is an available move, then move the piece to the target piece, otherwise drop the selectedPiece
     *
     * In addition, this method does not check whose the current turn is.
     */

    private void removeAllChessAction() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                this.board[i][j].setOnAction(null);
            }
        }
    }

    private int getNumOfPieces(boolean player) {
        int num = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (this.board[i][j].getPiece() != null) {
                    if (this.board[i][j].getPiece().getPlayer() == player) {
                        num++;
                    }
                }
            }
        }
        return num;
    }

    private void ifReachLastMove() {

        if (this.totalMove >= this.getMaxMove()) {

            Stage winner = new Stage();
            winner.setTitle("Congratulations!");

            // Calculate the points of the two players, and create a new window showing who is the winner (or a draw game).
            this.removeAllChessAction();
            this.resetBoard();

            VBox winWindow = new VBox();
            winWindow.setAlignment(Pos.CENTER);
            winWindow.setMaxSize(200, 100);
            winWindow.setMaxSize(200, 100);
            Label win = new Label();
            win.setStyle("-fx-font-size: 12; -fx-font-family: Monaco");
            if (getNumOfPieces(false) > getNumOfPieces(true)) {
                win.setText("White Wins");    // Need to change the text
            } else if (getNumOfPieces(false) < getNumOfPieces(true)) {
                win.setText("Black Wins");   // Need to change the text
            } else {
                /*winner.setTitle("Whiskey Tango Foxtrot");*/
                winner.setTitle("Game Over!");
                win.setText("Draw game!");    // Need to change the text
            }
            winWindow.getChildren().add(win);

            winner.setScene(new Scene(winWindow, 300, 200));
            winner.show();
        }

    }

    void setClickAction() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                final int fi = i;
                final int fj = j;
                this.board[i][j].setOnAction((ActionEvent e) -> {
                    if (selectedPiece == null) {
                        this.resetBoard();
                        if (this.board[fi][fj].getPiece() == null) {
                            return;
                        }
                        if (this.board[fi][fj].getPiece().getPlayer() != this.currentTurn) {
                            return;
                        }
                        this.selectedPiece = this.board[fi][fj].getPiece();
                        this.previousPosition = new Pair(fi, fj);
                        this.previousIcon = this.board[fi][fj].getIcon();
                        ArrayList<Cell> availableCell = this.board[fi][fj].getPiece().checkValidMove(this.board, fi, fj);
                        this.currentAvailableMove = availableCell;
                        int end = availableCell.size();
                        int k = 0;
                        while (k < end) {
                            if (this.board[fi][fj].getPiece().getPieceType() == Piece.PieceType.KNIGHT) {
                                this.board[availableCell.get(k).getCoordinate().getLeft()][availableCell.get(k).getCoordinate().getRight()].setStyle("-fx-background-color: #FFFFC8");
                            } else if (this.board[fi][fj].getPiece().getPieceType() == Piece.PieceType.BISHOP) {
                                this.board[availableCell.get(k).getCoordinate().getLeft()][availableCell.get(k).getCoordinate().getRight()].setStyle("-fx-background-color: #F68BFA");
                            } else if (this.board[fi][fj].getPiece().getPieceType() == Piece.PieceType.ROOK) {
                                this.board[availableCell.get(k).getCoordinate().getLeft()][availableCell.get(k).getCoordinate().getRight()].setStyle("-fx-background-color: #3567C2");
                            } else if(this.board[fi][fj].getPiece().getPieceType() == Piece.PieceType.BISHOP_KNIGHT) {
                                this.board[availableCell.get(k).getCoordinate().getLeft()][availableCell.get(k).getCoordinate().getRight()].setStyle("-fx-background-color: #FFA500");
                            } else if(this.board[fi][fj].getPiece().getPieceType() == Piece.PieceType.ROOK_BISHOP) {
                                this.board[availableCell.get(k).getCoordinate().getLeft()][availableCell.get(k).getCoordinate().getRight()].setStyle("-fx-background-color: #8B008B");
                            } else if(this.board[fi][fj].getPiece().getPieceType() == Piece.PieceType.KNIGHT_ROOK) {
                                this.board[availableCell.get(k).getCoordinate().getLeft()][availableCell.get(k).getCoordinate().getRight()].setStyle("-fx-background-color: #00FF00");
                            } else {
                                this.board[availableCell.get(k).getCoordinate().getLeft()][availableCell.get(k).getCoordinate().getRight()].setStyle("-fx-background-color: #EE3A15");
                            }
                            k++;
                        }
                    } else {
                        if (moveCheck(this.board[fi][fj], this.currentAvailableMove)) {
                            /* record move for withdraw */
                            this.canWithdraw = true;
                            this.withdrawOriginatorPiece.add(this.selectedPiece);
                            this.withdrawOriginatorPosition.add(this.previousPosition);
                            this.withdrawOriginatorIcon.add(this.previousIcon);
                            this.withdrawTargetPiece.add(this.board[fi][fj].getPiece());
                            this.withdrawTargetPosition.add(new Pair(fi, fj));
                            this.withdrawTargetIcon.add(this.board[fi][fj].getIcon());
                            this.withdrawButton.setText("Withdraw");
                            /* if split mode is not on or the piece is not able to be split */
                            if (!this.board[this.previousPosition.getLeft()][this.previousPosition.getRight()].getPiece().getSplit() || !this.onSplit) {
                                this.board[this.previousPosition.getLeft()][this.previousPosition.getRight()].removePiece();
                                this.board[this.previousPosition.getLeft()][this.previousPosition.getRight()].setIcon(null);

                                /* generally move to an empty cell */
                                if (this.board[fi][fj].getPiece() == null) {
                                    this.board[fi][fj].setPiece(this.selectedPiece);
                                    this.board[fi][fj].setIcon(this.previousIcon);
                                }
                                /* move to a cell which has piece */
                                else {
                                    /* perform capture */
                                    if (this.selectedPiece.getPlayer() != this.board[fi][fj].getPiece().getPlayer()) {
                                        int point;
                                        if (this.board[fi][fj].getPiece().getPieceType() == Piece.PieceType.ROOK || this.board[fi][fj].getPiece().getPieceType() == Piece.PieceType.BISHOP || this.board[fi][fj].getPiece().getPieceType() == Piece.PieceType.KNIGHT) {
                                            point = 5;
                                        } else if (this.board[fi][fj].getPiece().getPieceType() == Piece.PieceType.QUEEN) {
                                            point = 15;
                                        } else {
                                            point = 10;
                                        }
                                        this.board[fi][fj].setPiece(this.selectedPiece);
                                        this.board[fi][fj].setIcon(this.previousIcon);
                                        /* need to rise mark for the player when capture is preformed */
                                        if (this.currentTurn) {
                                            this.player1.addPoint(point);
                                            this.playerPad1.refreshCurrentPointLabel();
                                            this.playerPad1.refreshTotalPointLabel();
                                        } else {
                                            this.player2.addPoint(point);
                                            this.playerPad2.refreshCurrentPointLabel();
                                            this.playerPad2.refreshTotalPointLabel();
                                        }
                                    }
                                    /* perform merge */
                                    else {
                                        this.board[fi][fj].setPiece(this.pieceMerge(selectedPiece, this.board[fi][fj].getPiece(), this.currentTurn));
                                        if (this.currentTurn) {
                                            this.board[fi][fj].setIcon(this.pieceMerge(selectedPiece, this.board[fi][fj].getPiece(), this.currentTurn).getImage());
                                        } else {
                                            this.board[fi][fj].setIcon(this.pieceMerge(selectedPiece, this.board[fi][fj].getPiece(), this.currentTurn).getImage());
                                        }
                                    }
                                }
                            } else {
                                /* split mode on and the selected piece is able to be split */
                                if (isBishopSplit(previousPosition, board[fi][fj].getCoordinate())) {
                                    Bishop bishop = new Bishop(this.currentTurn);
                                    this.board[fi][fj].setPiece(bishop);
                                    this.board[fi][fj].setIcon(bishop.getImage());
                                    if (this.selectedPiece.getPieceType() == Piece.PieceType.BISHOP_KNIGHT) {
                                        Knight knight = new Knight(this.currentTurn);
                                        this.board[this.previousPosition.getLeft()][this.previousPosition.getRight()].setPiece(knight);
                                        this.board[this.previousPosition.getLeft()][this.previousPosition.getRight()].setIcon(knight.getImage());
                                    } else if (this.selectedPiece.getPieceType() == Piece.PieceType.ROOK_BISHOP) {
                                        Rook rook = new Rook(this.currentTurn);
                                        this.board[this.previousPosition.getLeft()][this.previousPosition.getRight()].setPiece(rook);
                                        this.board[this.previousPosition.getLeft()][this.previousPosition.getRight()].setIcon(rook.getImage());
                                    } else {
                                        KnightRook knightRook = new KnightRook(this.currentTurn);
                                        this.board[this.previousPosition.getLeft()][this.previousPosition.getRight()].setPiece(knightRook);
                                        this.board[this.previousPosition.getLeft()][this.previousPosition.getRight()].setIcon(knightRook.getImage());
                                    }
                                } else if (isKnightSplit(previousPosition, board[fi][fj].getCoordinate())) {
                                    Knight knight = new Knight(this.currentTurn);
                                    this.board[fi][fj].setPiece(knight);
                                    this.board[fi][fj].setIcon(knight.getImage());
                                    if (this.selectedPiece.getPieceType() == Piece.PieceType.BISHOP_KNIGHT) {
                                        Bishop bishop = new Bishop(this.currentTurn);
                                        this.board[this.previousPosition.getLeft()][this.previousPosition.getRight()].setPiece(bishop);
                                        this.board[this.previousPosition.getLeft()][this.previousPosition.getRight()].setIcon(bishop.getImage());
                                    } else if (this.selectedPiece.getPieceType() == Piece.PieceType.KNIGHT_ROOK) {
                                        Rook rook = new Rook(this.currentTurn);
                                        this.board[this.previousPosition.getLeft()][this.previousPosition.getRight()].setPiece(rook);
                                        this.board[this.previousPosition.getLeft()][this.previousPosition.getRight()].setIcon(rook.getImage());
                                    } else {
                                        RookBishop rookBishop = new RookBishop(this.currentTurn);
                                        this.board[this.previousPosition.getLeft()][this.previousPosition.getRight()].setPiece(rookBishop);
                                        this.board[this.previousPosition.getLeft()][this.previousPosition.getRight()].setIcon(rookBishop.getImage());
                                    }
                                } else {
                                    Rook rook = new Rook(this.currentTurn);
                                    this.board[fi][fj].setPiece(rook);
                                    this.board[fi][fj].setIcon(rook.getImage());
                                    if (this.selectedPiece.getPieceType() == Piece.PieceType.ROOK_BISHOP) {
                                        Bishop bishop = new Bishop(this.currentTurn);
                                        this.board[this.previousPosition.getLeft()][this.previousPosition.getRight()].setPiece(bishop);
                                        this.board[this.previousPosition.getLeft()][this.previousPosition.getRight()].setIcon(bishop.getImage());
                                    } else if (this.selectedPiece.getPieceType() == Piece.PieceType.KNIGHT_ROOK) {
                                        Knight knight = new Knight(this.currentTurn);
                                        this.board[this.previousPosition.getLeft()][this.previousPosition.getRight()].setPiece(knight);
                                        this.board[this.previousPosition.getLeft()][this.previousPosition.getRight()].setIcon(knight.getImage());
                                    } else {
                                        BishopKnight bishopKnight = new BishopKnight(this.currentTurn);
                                        this.board[this.previousPosition.getLeft()][this.previousPosition.getRight()].setPiece(bishopKnight);
                                        this.board[this.previousPosition.getLeft()][this.previousPosition.getRight()].setIcon(bishopKnight.getImage());
                                    }
                                }
                            }
                            this.totalMove++;
                            this.totalMoveLabel.setText("Total Move: " + String.valueOf(this.totalMove));
                            this.selectedPiece = null;
                            this.previousIcon = null;
                            this.previousPosition = null;
                            if (this.getNumOfPieces(!this.currentTurn) == 0) {
                                this.removeAllChessAction();
                                this.resetBoard();

                                VBox winWindow = new VBox();
                                winWindow.setAlignment(Pos.CENTER);
                                winWindow.setMaxSize(200, 100);
                                winWindow.setMaxSize(200, 100);
                                Label win = new Label();
                                win.setStyle("-fx-font-size: 12; -fx-font-family: Monaco");
                                if (!this.currentTurn) {
                                    win.setText("White Wins");    // Need to change the text
                                } else {
                                    win.setText("Black Wins");   // Need to change the text
                                }
                                winWindow.getChildren().add(win);

                                Stage winner = new Stage();
                                winner.setTitle("Congratulations!");
                                winner.setScene(new Scene(winWindow, 300, 200));
                                winner.show();
                            } else {
                                switchTurnInfo(!this.currentTurn);
                                this.currentTurn = !this.currentTurn;
                            }
                            this.ifReachLastMove();
                        } else {
                            this.selectedPiece = null;
                            this.previousIcon = null;
                            this.previousPosition = null;
                        }
                        this.resetBoard();
                    }
                });
            }
        }
    }

    private boolean isKnightSplit(Pair previousPosition, Pair targetPosition) {
        return (Math.abs(previousPosition.getLeft() - targetPosition.getLeft()) + Math.abs(previousPosition.getRight() - targetPosition.getRight())) == 3;
    }

    private boolean isBishopSplit(Pair previousPosition, Pair targetPosition) {
        return Math.abs(previousPosition.getLeft() - targetPosition.getLeft()) == Math.abs(previousPosition.getRight() - targetPosition.getRight());
    }

/*
    private boolean isRookSplit(Pair previousPosition, Pair targetPosition) {
        return (Math.abs(previousPosition.getLeft() - targetPosition.getLeft())) == 0 || (Math.abs(previousPosition.getRight() - targetPosition.getRight()) == 0);
    }
*/

    private Piece pieceMerge(Piece piece1, Piece piece2, boolean player) {
        boolean isBishop = false;
        boolean isKnight = false;
        boolean isRook = false;
        if (piece1.getIsBishop() || piece2.getIsBishop()) {
            isBishop = true;
        }
        if (piece1.getIsKnight() || piece2.getIsKnight()) {
            isKnight = true;
        }
        if (piece1.getIsRook() || piece2.getIsRook()) {
            isRook = true;
        }
        if (isBishop && isKnight && isRook) {
            return new Queen(player);
        }
        else if (isBishop && isKnight) {
            return new BishopKnight(player);
        }
        else if (isKnight && isRook) {
            return new KnightRook(player);
        }
        else {
            return new RookBishop(player);
        }
    }

    private void withdraw() {
        if (!canWithdraw) {
            return;
        }
        int lastIndex = this.withdrawOriginatorPosition.size() - 1;
        this.board[this.withdrawOriginatorPosition.get(lastIndex).getLeft()][this.withdrawOriginatorPosition.get(lastIndex).getRight()].setPiece(this.withdrawOriginatorPiece.get(lastIndex));
        this.board[this.withdrawOriginatorPosition.get(lastIndex).getLeft()][this.withdrawOriginatorPosition.get(lastIndex).getRight()].setIcon(this.withdrawOriginatorIcon.get(lastIndex));
        this.board[this.withdrawTargetPosition.get(lastIndex).getLeft()][this.withdrawTargetPosition.get(lastIndex).getRight()].setPiece(this.withdrawTargetPiece.get(lastIndex));
        this.board[this.withdrawTargetPosition.get(lastIndex).getLeft()][this.withdrawTargetPosition.get(lastIndex).getRight()].setIcon(this.withdrawTargetIcon.get(lastIndex));
        this.totalMove--;
        this.totalMoveLabel.setText("Total Move: " + String.valueOf(this.totalMove));
        if (this.board[this.withdrawTargetPosition.get(lastIndex).getLeft()][this.withdrawTargetPosition.get(lastIndex).getRight()].getPiece() != null) {
            if (this.board[this.withdrawTargetPosition.get(lastIndex).getLeft()][this.withdrawTargetPosition.get(lastIndex).getRight()].getPiece().getPlayer() == this.currentTurn) {
                int point;
                if (this.board[this.withdrawTargetPosition.get(lastIndex).getLeft()][this.withdrawTargetPosition.get(lastIndex).getRight()].getPiece().getPieceType() == Piece.PieceType.ROOK || this.board[this.withdrawTargetPosition.get(lastIndex).getLeft()][this.withdrawTargetPosition.get(lastIndex).getRight()].getPiece().getPieceType() == Piece.PieceType.BISHOP || this.board[this.withdrawTargetPosition.get(lastIndex).getLeft()][this.withdrawTargetPosition.get(lastIndex).getRight()].getPiece().getPieceType() == Piece.PieceType.KNIGHT) {
                    point = 5;
                } else if (this.board[this.withdrawTargetPosition.get(lastIndex).getLeft()][this.withdrawTargetPosition.get(lastIndex).getRight()].getPiece().getPieceType() == Piece.PieceType.QUEEN) {
                    point = 15;
                } else {
                    point = 10;
                }
                if (currentTurn) {
                    this.player2.subtractPoint(point);
                    this.playerPad2.refreshCurrentPointLabel();
                    this.playerPad2.refreshTotalPointLabel();
                } else {
                    this.player1.subtractPoint(point);
                    this.playerPad1.refreshCurrentPointLabel();
                    this.playerPad1.refreshTotalPointLabel();
                }
            }
        }
        switchTurnInfo(!this.currentTurn);
        this.currentTurn = !this.currentTurn;

        this.withdrawOriginatorPosition.remove(lastIndex);
        this.withdrawOriginatorPiece.remove(lastIndex);
        this.withdrawOriginatorIcon.remove(lastIndex);
        this.withdrawTargetPosition.remove(lastIndex);
        this.withdrawTargetPiece.remove(lastIndex);
        this.withdrawTargetIcon.remove(lastIndex);
        if (this.withdrawOriginatorPosition.size() == 0) {
            this.canWithdraw = false;
            this.withdrawButton.setText("No Withdraw");
        }
    }

    private void switchTurnInfo(Boolean currentTurn) {
        this.playerPad1.setCurrentTurn(currentTurn);
        this.playerPad2.setCurrentTurn(!currentTurn);
    }

}
