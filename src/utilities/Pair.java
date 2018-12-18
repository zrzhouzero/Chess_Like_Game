package utilities;

public class Pair {

    private int L;
    private int R;

    public Pair(int L, int R) {
        this.L = L;
        this.R = R;
    }

    public int getLeft() { return this.L; }
    public int getRight() { return this.R; }

/*
    public boolean isSame(Pair pair) {
        return this.L == pair.getLeft() && this.R == pair.getRight();
    }

    public static boolean isSame(Pair pair1, Pair pair2) {
        return (pair1.getLeft() == pair2.getLeft()) && (pair1.getRight() == pair2.getRight());
    }
*/

}
