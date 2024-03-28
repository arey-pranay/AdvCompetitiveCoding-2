import java.io.*;

// Class representing a node in the Binomial Heap
class BinomialHeapNode {
    int key, degree;
    BinomialHeapNode parent, sibling, child;

    // Constructor to initialize a node with a given key
    public BinomialHeapNode(int k) {
        key = k;
        degree = 0;
        parent = null;
        sibling = null;
        child = null;
    }

    // Function to reverse the sibling pointers of a node
    public BinomialHeapNode reverse(BinomialHeapNode sibl) {
        BinomialHeapNode ret;
        if (sibling != null)
            ret = sibling.reverse(this);
        else
            ret = this;
        sibling = sibl;
        return ret;
    }

    // Function to find the minimum node in the heap
    public BinomialHeapNode findMinNode() {
        BinomialHeapNode x = this, y = this;
        int min = x.key;
        while (x != null) {
            if (x.key < min) {
                y = x;
                min = x.key;
            }
            x = x.sibling;
        }
        return y;
    }

    // Function to find a node with a given key in the heap
    public BinomialHeapNode findANodeWithKey(int value) {
        BinomialHeapNode temp = this, node = null;
        while (temp != null) {
            if (temp.key == value) {
                node = temp;
                break;
            }
            if (temp.child == null)
                temp = temp.sibling;
            else {
                node = temp.child.findANodeWithKey(value);
                if (node == null)
                    temp = temp.sibling;
                else
                    break;
            }
        }
        return node;
    }

    // Function to get the size of the heap
    public int getSize() {
        return (1 + ((child == null) ? 0 : child.getSize()) + ((sibling == null) ? 0 : sibling.getSize()));
    }
}

// Class representing the Binomial Heap
class BinomialHeap {
    private BinomialHeapNode Nodes;
    private int size;

    // Constructor to initialize the heap
    public BinomialHeap() {
        Nodes = null;
        size = 0;
    }

    // Function to check if the heap is empty
    public boolean isEmpty() {
        return Nodes == null;
    }

    // Function to get the size of the heap
    public int getSize() {
        return size;
    }

    // Function to make the heap empty
    public void makeEmpty() {
        Nodes = null;
        size = 0;
    }

    // Function to insert a new key into the heap
    public void insert(int value) {
        if (value > 0) {
            BinomialHeapNode temp = new BinomialHeapNode(value);
            if (Nodes == null) {
                Nodes = temp;
                size = 1;
            } else {
                unionNodes(temp);
                size++;
            }
        }
    }

    // Function to merge two binomial heaps
    private void merge(BinomialHeapNode binHeap) {
        BinomialHeapNode temp1 = Nodes, temp2 = binHeap;
        while ((temp1 != null) && (temp2 != null)) {
            if (temp1.degree == temp2.degree) {
                BinomialHeapNode tmp = temp2;
                temp2 = temp2.sibling;
                tmp.sibling = temp1.sibling;
                temp1.sibling = tmp;
                temp1 = tmp.sibling;
            } else {
                if (temp1.degree < temp2.degree) {
                    if ((temp1.sibling == null) || (temp1.sibling.degree > temp2.degree)) {
                        BinomialHeapNode tmp = temp2;
                        temp2 = temp2.sibling;
                        tmp.sibling = temp1.sibling;
                        temp1.sibling = tmp;
                        temp1 = tmp.sibling;
                    } else {
                        temp1 = temp1.sibling;
                    }
                } else {
                    BinomialHeapNode tmp = temp1;
                    temp1 = temp2;
                    temp2 = temp2.sibling;
                    temp1.sibling = tmp;
                    if (tmp == Nodes) {
                        Nodes = temp1;
                    } else {
                    }
                }
            }
        }
        if (temp1 == null) {
            temp1 = Nodes;
            while (temp1.sibling != null) {
                temp1 = temp1.sibling;
            }
            temp1.sibling = temp2;
        } else {
        }
    }

    // Function to union two binomial heaps
    private void unionNodes(BinomialHeapNode binHeap) {
        merge(binHeap);
        BinomialHeapNode prevTemp = null, temp = Nodes, nextTemp = Nodes.sibling;
        while (nextTemp != null) {
            if ((temp.degree != nextTemp.degree) || ((nextTemp.sibling != null) && (nextTemp.sibling.degree == temp.degree))) {
                prevTemp = temp;
                temp = nextTemp;
            } else {
                if (temp.key <= nextTemp.key) {
                    temp.sibling = nextTemp.sibling;
                    nextTemp.parent = temp;
                    nextTemp.sibling = temp.child;
                    temp.child = nextTemp;
                    temp.degree++;
                } else {
                    if (prevTemp == null) {
                        Nodes = nextTemp;
                    } else {
                        prevTemp.sibling = nextTemp;
                    }
                    temp.parent = nextTemp;
                    temp.sibling = nextTemp.child;
                    nextTemp.child = temp;
                    nextTemp.degree++;
                    temp = nextTemp;
                }
            }
            nextTemp = temp.sibling;
        }
    }

    // Function to find the minimum key in the heap
    public int findMinimum() {
        return Nodes.findMinNode().key;
    }

    // Function to delete a key from the heap
    public void delete(int value) {
        if ((Nodes != null) && (Nodes.findANodeWithKey(value) != null)) {
            decreaseKeyValue(value, findMinimum() - 1);
            extractMin();
        }
    }

    // Function to decrease the value of a key in the heap
    public void decreaseKeyValue(int old_value, int new_value) {
        BinomialHeapNode temp = Nodes.findANodeWithKey(old_value);
        if (temp == null)
            return;
        temp.key = new_value;
        BinomialHeapNode tempParent = temp.parent;
        while ((tempParent != null) && (temp.key < tempParent.key)) {
            int z = temp.key;
            temp.key = tempParent.key;
            tempParent.key = z;
            temp = tempParent;
            tempParent = tempParent.parent;
        }
    }

    // Function to extract the minimum key from the heap
    public int extractMin() {
        if (Nodes == null)
            return -1;
        BinomialHeapNode temp = Nodes, prevTemp = null;
        BinomialHeapNode minNode = Nodes.findMinNode();
        while (temp.key != minNode.key) {
            prevTemp = temp;
            temp = temp.sibling;
        }
        if (prevTemp == null) {
            Nodes = temp.sibling;
        } else {
            prevTemp.sibling = temp.sibling;
        }
        temp = temp.child;
        BinomialHeapNode fakeNode = temp;
        while (temp != null) {
            temp.parent = null;
            temp = temp.sibling;
        }
        if ((Nodes == null) && (fakeNode == null)) {
            size = 0;
        } else {
            if ((Nodes == null) && (fakeNode != null)) {
                Nodes = fakeNode.reverse(null);
                size = Nodes.getSize();
            } else {
                if ((Nodes != null) && (fakeNode == null)) {
                    size = Nodes.getSize();
                } else {
                    unionNodes(fakeNode.reverse(null));
                    size = Nodes.getSize();
                }
            }
        }
        return minNode.key;
    }

    // Function to display the heap
    public void displayHeap() {
        System.out.print("\nHeap : ");
        displayHeap(Nodes);
        System.out.println("\n");
    }

    // Function to recursively display the heap
    private void displayHeap(BinomialHeapNode r) {
        if (r != null) {
            displayHeap(r.child);
            System.out.print(r.key + " ");
            displayHeap(r.sibling);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        BinomialHeap binHeap = new BinomialHeap();
        binHeap.insert(12);
        binHeap.insert(8);
        binHeap.insert(5);
        binHeap.insert(15);
        binHeap.insert(7);
        binHeap.insert(2);
        binHeap.insert(9);

        System.out.println("Size of the binomial heap is " + binHeap.getSize());
        binHeap.displayHeap();

        binHeap.delete(15);
        binHeap.delete(8);

        System.out.println("Size of the binomial heap is " + binHeap.getSize());
        binHeap.displayHeap();

        binHeap.makeEmpty();
        System.out.println(binHeap.isEmpty());
    }
}
