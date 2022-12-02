import java.util.Random;
import java.util.Scanner;
class Nodes {
    int key, height;
    Node left, right;
    Nodes(int d) {
        key = d;
        height = 1;
    }
}
class Binary_Tree_Threading {
    private int return_height;
    private boolean FLAG;
    public boolean isFLAG() {
        return FLAG;
    }
    public int getReturn_height() {
        return return_height;
    }
    Node root;
    int height(Node N) {
        if (N == null)
            return 0;
        return_height=N.height;
        return N.height;
    }
    int max(int a, int b) {
        return (a > b) ? a : b;
    }
    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }
    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }
    int getBalance(Node N) {
        if (N == null)
            return 0;

        return height(N.left) - height(N.right);
    }
    Node find(int key) {

        Node current = root;
        while (current != null) {
            if (current.key == key) {
                FLAG=true;
            }
            current = current.key < key ? current.right : current.left;
        }
        return current;
    }
    Node insert(Node node, int key) {
        if (node == null)
            return (new Node(key));

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node;

        node.height = 1 + max(height(node.left),
                height(node.right));
        int balance = getBalance(node);
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }
    void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        Scanner sc =new Scanner(System.in);
        System.out.print("Enter size of Array: ");
        int SIZE=sc.nextInt();
        Random random = new Random();
        long insert_start_time=System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            int temp=random.nextInt();
            tree.root = tree.insert(tree.root, temp);
        }
        long insert_end_time=System.currentTimeMillis();

        System.out.println("-------------------Preorder traversal:------------------------");
        tree.preOrder(tree.root);

        System.out.print("\nEnter a Node to search in tree:");
        int search= sc.nextInt();
        long search_start_time=System.currentTimeMillis();
        tree.find(search);
        if(tree.isFLAG()==true){
            System.out.println("Element found!!!\n");
        }else {
            System.out.println("Element not found!!!\n");
        }
        long search_end_time=System.currentTimeMillis();
        long height_start_time=System.currentTimeMillis();
        int height= tree.getReturn_height();
        long height_end_time=System.currentTimeMillis();
        System.out.println("\nHeight of tree: "+height);
        System.out.println("\n\n------------------------TIMING------------------------------\n");
        System.out.println("Time taken by Insertion    :   "+(insert_end_time-insert_start_time)+"ms\n");
        System.out.println("Time taken by Searching    :   "+(search_end_time-search_start_time)+"ms\n");
        System.out.println("Time taken by Height       :   "+(height_end_time-height_start_time)+"ms\n");
    }
}

