class TreeNode {
int val;
TreeNode left;
TreeNode right;
TreeNode(int val) {
this.val = val;
}
}
public class Main {
TreeNode firstIncorrectNode = null;
TreeNode secondIncorrectNode = null;
TreeNode prevNode = new
TreeNode(Integer.MIN_VALUE);
public void recoverTree(TreeNode root) {
// Perform inorder traversal
inorder(root);
// Swap the values of the two incorrectly placed nodes
int temp = firstIncorrectNode.val;
firstIncorrectNode.val = secondIncorrectNode.val;
secondIncorrectNode.val = temp;
}
private void inorder(TreeNode node)
{
if (node == null) return;
inorder(node.left);
// Check for incorrectly placed nodes
if (firstIncorrectNode == null && prevNode.val >= node.val) {
firstIncorrectNode = prevNode;
}
if (firstIncorrectNode != null && prevNode.val >= node.val) {
secondIncorrectNode = node;
}
prevNode = node;
inorder(node.right);
}
public static void main(String[] args) {
// Create a sample binary search tree with incorrect nodes
TreeNode root = new TreeNode(3);
root.left = new TreeNode(1);
root.right = new TreeNode(4);
root.right.left = new TreeNode(2);
Main solution = new Main();
solution.recoverTree(root);
// Print the corrected BST
System.out.println("Inorder Traversal of Recovered BST:");
printInorder(root);
}
// Helper function to print the inorder traversal of a tree
private static void printInorder(TreeNode node) {
if (node == null) return;
printInorder(node.left);
System.out.print(node.val + " ");
printInorder(node.right);
}
}
