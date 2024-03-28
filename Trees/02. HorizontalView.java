import java.util.*;

class TreeNode {
    char val;
    TreeNode left;
    TreeNode right;

    public TreeNode(char val) {
        this.val = val;
    }
}

public class HorizontalView {
    public static List<Character> horizontalView(TreeNode root) {
        List<Character> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            result.add(node.val);

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
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

        // Obtain the horizontal view of the binary tree
        List<Character> horizontalViewResult = horizontalView(root);

        // Printing the Horizontal View
        System.out.println("Horizontal View:");
        for (char node : horizontalViewResult) {
            System.out.print(node + " ");
        }
    }
}
