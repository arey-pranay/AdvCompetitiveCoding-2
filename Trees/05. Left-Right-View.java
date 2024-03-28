 public static void leftView(TreeNode root) {
        if (root == null)
            return;

        // Queue for level order traversal
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                // Print the first node in each level (leftmost node)
                if (i == 0) {
                    System.out.print(node.val + " ");
                }
                // Add the left child to the queue
                if (node.left != null) {
                    queue.add(node.left);
                }
                // Add the right child to the queue
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
    }

    // Function to print the right view of the binary tree
    public static void rightView(TreeNode root) {
        if (root == null)
            return;

        // Queue for level order traversal
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                // Print the last node in each level (rightmost node)
                if (i == size - 1) {
                    System.out.print(node.val + " ");
                }
                // Add the left child to the queue
                if (node.left != null) {
                    queue.add(node.left);
                }
                // Add the right child to the queue
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
    }
