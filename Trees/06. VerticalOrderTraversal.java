import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
        left = null;
        right = null;
    }
}

public class Main {
    // Function to perform Vertical Order Traversal of a binary tree
    public static List<List<Integer>> verticalOrderTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        // Check if the tree is empty
        if (root == null) {
            return result;
        }

        // TreeMap to store nodes based on their column index
        Map<Integer, List<Integer>> verticalMap = new TreeMap<>();

        // Queues to perform level-order traversal
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> columnQueue = new LinkedList<>();

        // Add root node to queues with column index 0
        nodeQueue.offer(root);
        columnQueue.offer(0);

        // Perform level-order traversal
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int col = columnQueue.poll();

            // Add node value to the corresponding column in verticalMap
            if (!verticalMap.containsKey(col)) {
                verticalMap.put(col, new ArrayList<>());
            }
            verticalMap.get(col).add(node.val);

            // Add left child to queues with updated column index
            if (node.left != null) {
                nodeQueue.offer(node.left);
                columnQueue.offer(col - 1);
            }

            // Add right child to queues with updated column index
            if (node.right != null) {
                nodeQueue.offer(node.right);
                columnQueue.offer(col + 1);
            }
        }

        // Convert values from verticalMap to result list
        for (List<Integer> values : verticalMap.values()) {
            result.add(values);
        }

        return result;
    }

    public static void main(String[] args) {
        // Sample binary tree input
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        // Perform vertical order traversal
        List<List<Integer>> verticalOrderResult = verticalOrderTraversal(root);

        // Printing the Vertical Order Traversal result
        for (List<Integer> column : verticalOrderResult) {
            for (int val : column) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}
