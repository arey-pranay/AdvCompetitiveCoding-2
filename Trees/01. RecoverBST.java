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

public class MorrisTraversalBSTRecovery {
    public static void recoverBST(TreeNode root) {
        TreeNode current = root;

        while (current != null) {
            if (current.left == null) {
                // Process current node here (for example, print its value)
                System.out.print(current.val + " ");

                // Move to the right child
                current = current.right;
            } else {
                // Find the predecessor of current node
                TreeNode predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {
                    // Make predecessor point to current node
                    predecessor.right = current;
                    current = current.left;
                } else {
                    // Revert the change made to predecessor
                    predecessor.right = null;

                    // Process current node here (for example, print its value)
                    System.out.print(current.val + " ");

                    // Move to the right child
                    current = current.right;
                }
            }
        }
    }

    public static void main(String[] args) {
        // Construct the BST
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);

        // Recover the structure of the BST using Morris Traversal
        System.out.println("Recovered BST structure:");
        recoverBST(root);
    }
}
