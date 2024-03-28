import java.util.*;

class TreeNode {
    char val;
    TreeNode left;
    TreeNode right;

    public TreeNode(char val) {
        this.val = val;
    }
}

public class VerticalView {
    public static List<List<Character>> verticalView(TreeNode root) {
        List<List<Character>> result = new ArrayList<>();
        if (root == null) return result;

        Map<Integer, List<Character>> map = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> hdQueue = new LinkedList<>();
        int minHd = 0, maxHd = 0;

        queue.offer(root);
        hdQueue.offer(0);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int hd = hdQueue.poll();

            map.computeIfAbsent(hd, k -> new ArrayList<>()).add(node.val);
            minHd = Math.min(minHd, hd);
            maxHd = Math.max(maxHd, hd);

            if (node.left != null) {
                queue.offer(node.left);
                hdQueue.offer(hd - 1);
            }
            if (node.right != null) {
                queue.offer(node.right);
                hdQueue.offer(hd + 1);
            }
        }

        for (int i = minHd; i <= maxHd; i++) {
            result.add(map.get(i));
        }

        return result;
    }

    public static void main(String[] args) {
        // Sample binary tree input
        TreeNode root = new TreeNode('A');
        root.left = new TreeNode('B');
        root.right = new TreeNode('C');
        root.left.left = new TreeNode('D');
        root.right.left = new TreeNode('E');
        root.right.right = new TreeNode('F');

        // Obtain the vertical view of the binary tree
        List<List<Character>> verticalViewResult = verticalView(root);

        // Printing the Vertical View
        System.out.println("Vertical View:");
        for (List<Character> column : verticalViewResult) {
            for (char node : column) {
                System.out.print(node + " ");
            }
            System.out.println();
        }
    }
}
