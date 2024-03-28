import java.util.Arrays;

public class WinnerTree {
    private int[] tree;
    private int[] players;

    public WinnerTree(int[] players) {
        this.players = players;
        int n = players.length;
        int treeSize = 1;
        while (treeSize < n) {
            treeSize *= 2;
        }
        tree = new int[2 * treeSize - 1];
        Arrays.fill(tree, -1);
        for (int i = 0; i < n; i++) {
            tree[treeSize - 1 + i] = i;
        }
        build(0, 0, treeSize - 1);
    }

    private void build(int node, int left, int right) {
        if (left == right) return;
        int mid = (left + right) / 2;
        build(2 * node + 1, left, mid);
        build(2 * node + 2, mid + 1, right);
        tree[node] = players[tree[2 * node + 1]] <= players[tree[2 * node + 2]] ? tree[2 * node + 1] : tree[2 * node + 2];
    }

    public int getWinner() {
        return tree[0];
    }

    public static void main(String[] args) {
        int[] players = {3, 7, 1, 9, 4, 2, 8, 5};
        WinnerTree winnerTree = new WinnerTree(players);
        int winnerIndex = winnerTree.getWinner();
        System.out.println("The player with the highest score is at index: " + winnerIndex);
        System.out.println("The score of the winning player is: " + players[winnerIndex]);
    }
}
