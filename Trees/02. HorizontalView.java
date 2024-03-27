import java.util.*;
class TreeNode {
char val;
TreeNode left;
TreeNode right;
public TreeNode(char val) {
this.val = val;
left = null;
right = null;
}}
public class Main {
// Function to obtain the Horizontal
View of a binary tree
public static List<Character>
horizontalView(TreeNode root) {
List<Character> horizontalView =
new ArrayList<>();
if (root == null) {
return horizontalView;
}
Queue<TreeNode> queue = new
LinkedList<>();
queue.offer(root);
while (!queue.isEmpty()) {
int levelSize = queue.size();
for (int i = 0; i <
levelSize; i++) {
TreeNode node =
queue.poll();
horizontalView.add(node.val);
if (node.left != null) {
queue.offer(node.left);
}
if (node.right != null) {
queue.offer(node.right);
}
}
}return horizontalView;
}
public static void main(String[]
args) {
// Sample binary tree input
TreeNode root = new
TreeNode('A');
root.left = new TreeNode('B');
root.right = new TreeNode('C');
root.left.left = new
TreeNode('D');
root.left.right = new
TreeNode('E');
root.right.left = new
TreeNode('F');
root.right.right = new
TreeNode('G');
List<Character> horizontalViewResult =
horizontalView(root);
// Printing the Horizontal View
System.out.print("Horizontal
View: ");
for (char node :
horizontalViewResult) {
System.out.print(node + " ");
}
System.out.println();
}
}
