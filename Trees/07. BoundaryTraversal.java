import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;

    // Constructor to initialize TreeNode with a value
    public TreeNode(int value) {
        val = value;
        left = right = null;
    }
}

public class BinaryTreeBoundaryTraversal {

    // Function to print the leaf nodes of the tree
    private static void printLeaves(TreeNode root, List<Integer> result) {
        if (root != null) {
            // Traverse left subtree to find leaf nodes
            printLeaves(root.left, result);
            // If node is leaf, add its value to the result list
            if (root.left == null && root.right == null)
                result.add(root.val);
            // Traverse right subtree to find leaf nodes
            printLeaves(root.right, result);
        }
    }

    // Function to perform boundary traversal of the tree
    public static List<Integer> boundaryTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root != null) {
            // Print root node
            result.add(root.val);
            // Print left boundary (excluding root and leaf nodes)
            printLeftBoundary(root.left, result);
            // Print leaf nodes
            printLeaves(root.left, result);
            printLeaves(root.right, result);
            // Print right boundary (excluding root and leaf nodes)
            printRightBoundary(root.right, result);
        }
        return result;
    }

    // Function to print left boundary of the tree
    private static void printLeftBoundary(TreeNode root, List<Integer> result) {
        if (root == null || (root.left == null && root.right == null))
            return;
        result.add(root.val);
        if (root.left != null)
            printLeftBoundary(root.left, result);
        else
            printLeftBoundary(root.right, result);
    }

    // Function to print right boundary of the tree
    private static void printRightBoundary(TreeNode root, List<Integer> result) {
        if (root == null || (root.left == null && root.right == null))
            return;
        if (root.right != null)
            printRightBoundary(root.right, result);
        else
            printRightBoundary(root.left, result);
        result.add(root.val);
    }

    public static void main(String[] args) {
        // Constructing the example tree
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(3);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(6);
        root.left.right.left = new TreeNode(4);
        root.left.right.right = new TreeNode(7);
        root.right.right = new TreeNode(14);
        root.right.right.left = new TreeNode(13);

        // Calling the boundaryTraversal function to perform boundary traversal
        List<Integer> boundaryTraversalResult = boundaryTraversal(root);
        // Printing the boundary traversal result
        System.out.println("Boundary Traversal of the Binary Tree:");
        for (int val : boundaryTraversalResult) {
            System.out.print(val + " ");
        }
    }
}
