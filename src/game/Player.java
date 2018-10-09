package game;

public class Player {
    private String accountId;
    private String password;
    private int currentPoint;
    private int totalPoint;
    private boolean playerInGame;

    Player(String accountId, String password) {
        this.accountId = accountId;
        this.password = password;
        this.currentPoint = 0;
        this.totalPoint = 0;
    }

    Player(String accountId, String password, int totalPoint) {
        this.accountId = accountId;
        this.password = password;
        this.currentPoint = 0;
        this.totalPoint = totalPoint;
    }

    String getAccountId() {
        return this.accountId;
    }

    boolean validPasswordWrong(String inputPassword) {
        return !inputPassword.equals(this.password);
    }

    int getCurrentPoint() {
        return this.currentPoint;
    }

    int getTotalPoint() {
        return this.totalPoint;
    }

    void addPoint(int point) {
        this.currentPoint += point;
        this.totalPoint += point;
    }

    void subtractPoint(int point) {
        this.currentPoint -= point;
        this.totalPoint -= point;
    }

    void setPlayerInGame(boolean player) {
        this.playerInGame = player;
    }

    boolean getPlayerInGame() {
        return this.playerInGame;
    }

    @Override
    public String toString() {
        return this.accountId + ":" + this.password + ":" + this.totalPoint;
    }

}
