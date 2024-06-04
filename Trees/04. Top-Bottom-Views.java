import java.util.*;
// Definition of a binary tree node
class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int value) {
        val = value;
        left = right = null;
    }
}
public class BinaryTreeViews {

    // Function to print the top view of the binary tree
    public static void topView(TreeNode root) {
        if (root == null)
            return;

        // Map to store the horizontal distance and node value of the top view nodes
        Map<Integer, Integer> map = new TreeMap<>();
        // Queue for level order traversal
        Queue<TreeNode> queue = new LinkedList<>();
        // Queue for horizontal distance tracking
        Queue<Integer> columnQueue = new LinkedList<>();

        // Start level order traversal from the root
        queue.add(root);
        columnQueue.add(0);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int column = columnQueue.poll();

            // If the column is not present in the map, add it along with the node value
            if (!map.containsKey(column)) {
                map.put(column, node.val);
            }

            // Process the left child
            if (node.left != null) {
                queue.add(node.left);
                columnQueue.add(column - 1); // Horizontal distance for left child
            }

            // Process the right child
            if (node.right != null) {
                queue.add(node.right);
                columnQueue.add(column + 1); // Horizontal distance for right child
            }
        }

        // Print the node values of the top view
        for (int val : map.values()) {
            System.out.print(val + " ");
        }
    }

    // Function to print the bottom view of the binary tree
    public static void bottomView(TreeNode root) {
        if (root == null)
            return;

        // Map to store the horizontal distance and node value of the bottom view nodes
        Map<Integer, Integer> map = new TreeMap<>();
        // Queue for level order traversal
        Queue<TreeNode> queue = new LinkedList<>();
        // Queue for horizontal distance tracking
        Queue<Integer> columnQueue = new LinkedList<>();

        // Start level order traversal from the root
        queue.add(root);
        columnQueue.add(0);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int column = columnQueue.poll();

            // Update the map with the latest node value for the corresponding horizontal distance
            map.put(column, node.val);

            // Process the left child
            if (node.left != null) {
                queue.add(node.left);
                columnQueue.add(column - 1); // Horizontal distance for left child
            }

            // Process the right child
            if (node.right != null) {
                queue.add(node.right);
                columnQueue.add(column + 1); // Horizontal distance for right child
            }
        }

        // Print the node values of the bottom view
        for (int val : map.values()) {
            System.out.print(val + " ");
        }
    }
}
