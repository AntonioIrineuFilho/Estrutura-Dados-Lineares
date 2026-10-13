import java.util.ArrayList;
import java.util.Iterator;
import java.lang.Math;

class Node {
    private int key;
    private Node parent, leftChild, rightChild;

    public Node(int key) {
        this.key = key;
        this.parent = null;
        this.leftChild = null;
        this.rightChild = null;
    }

    public int getKey() {
        return this.key;
    }

    public Node getParent() {
        return this.parent;
    }

    public Node getLeftChild() {
        return this.leftChild;
    }

    public Node getRightChild() {
        return this.rightChild;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setLeftChild(Node left) {
        this.leftChild = left;
    }

    public void setRightChild(Node right) {
        this.rightChild = right;
    }
}

class ArvoreBinariaPesquisa {
    private Node root;
    private int size;

    public ArvoreBinariaPesquisa() {
        this.root = null;
        this.size = 0;
    }

    public Node root() {
        return this.root;
    }   
    
    public Node parent(Node n) {
        return n.getParent();
    }

    public Node leftChild(Node n) {
        return n.getLeftChild();
    }

    public Node rightChild(Node n) {
        return n.getRightChild();
    }

    public void elementsInOrder(ArrayList elements, Node n) {
        if (this.hasLeftChild(n)) {
            elementsInOrder(elements, this.leftChild(n));
        }
        elements.add(n.getKey());
        if (this.hasRightChild(n)) {
            elementsInOrder(elements, this.rightChild(n));
        }
    }

    public Iterator<Integer> elements() {
        ArrayList<Integer> elements = new ArrayList<>();
        this.elementsInOrder(elements, this.root);
        return elements.iterator();
    }

    public void nodesInOrder(ArrayList nodes, Node n) {
        if (this.hasLeftChild(n)) {
            nodesInOrder(nodes, this.leftChild(n));
        }
        nodes.add(n);
        if (this.hasRightChild(n)) {
            nodesInOrder(nodes, this.rightChild(n));
        }
    }

    public Iterator<Node> nodes() {
        ArrayList<Node> nodes = new ArrayList<>();
        this.nodesInOrder(nodes, this.root);
        return nodes.iterator();
    }

    public int depth(Node n) {
        if (n == this.root) {
            return 0;
        }
        return 1 + depth(this.parent(n));
    }

    public int height() {
        int h = 0;
        Iterator<Node> nodes = this.nodes();
        while (nodes.hasNext()) {
            Node node = nodes.next();
            if (this.isExternal(node)) {
                h = Math.max(this.depth(node), h);
            }
        }
        return h;
    }

    public Node search(Node subRoot, int key) throws RuntimeException {
        if (this.isEmpty()) {
            throw new RuntimeException("Árvore vazia");
        }
        if (key < subRoot.getKey()) {
            if (!this.hasLeftChild(subRoot)) {
                return subRoot;
            }
            return search(this.leftChild(subRoot), key);
        } else if (key > subRoot.getKey()) {
            if (!this.hasRightChild(subRoot)) {
                return subRoot;
            }
            return search(this.rightChild(subRoot), key);
        } else {
            return subRoot;
        }
    }

    public void insert(int key) throws RuntimeException {
        Node newNode = new Node(key);
        if (this.isEmpty()) {
            this.root = newNode;
        } else {
            Node target = this.search(this.root, key);
            if (key == target.getKey()) {
                throw new RuntimeException("Chave já existe");
            } else if (key < target.getKey()) {
                newNode.setParent(target);
                target.setLeftChild(newNode);
            } else {
                newNode.setParent(target);
                target.setRightChild(newNode);
            }
        }
        this.size++;
    }

    public void remove(int key) {
        this.root = remove(this.root, key);
        this.size--;
        if (this.root != null) {
            this.root.setParent(null);
        }
    }

    private Node remove(Node subRoot, int key) throws RuntimeException {
        if (this.isEmpty()) {
            throw new RuntimeException("Árvore vazia");
        }
        if (subRoot == null) {
            throw new RuntimeException("Chave não existe");
        }
        if (key < subRoot.getKey()) {
            Node newLeft = remove(this.leftChild(subRoot), key); // seta o valor do filho esquerdo com base no retorno das chamadas posteriores
            subRoot.setLeftChild(newLeft);
            if (newLeft != null) {
                newLeft.setParent(subRoot);
            }
        } else if (key > subRoot.getKey()) {
            Node newRight = remove(this.rightChild(subRoot), key); // seta o valor do filho direito com base no retorno das chamadas posteriores
            subRoot.setRightChild(newRight);
            if (newRight != null) {
                newRight.setParent(subRoot);
            }
        } else {
            if (this.isExternal(subRoot)) {
                return null; // retorna nulo para a chamada anterior, setando como novo valor do raiz da sub árvore
            } else if (!this.hasLeftChild(subRoot)) {
                Node child = this.rightChild(subRoot);
                child.setParent(subRoot.getParent());
                return child; // retorna o filho direito do raiz da sub árvore para substituí-lo na chamada anterior
            } else if (!this.hasRightChild(subRoot)) {
                Node child = this.leftChild(subRoot);
                child.setParent(subRoot.getParent());
                return child; // retorna o filho esquerdo do raiz da sub árvore para substituí-lo na chamada anterior
            } else {
                Node successor = this.successor(subRoot);
                subRoot.setKey(successor.getKey());
                Node newRight = remove(this.rightChild(subRoot), successor.getKey());
                subRoot.setRightChild(newRight);
                if (newRight != null) {
                    newRight.setParent(subRoot);
                }
            }
        }
        return subRoot;
    }

    public Node successor(Node n) {
        Node node = this.rightChild(n);
        while (this.hasLeftChild(node)) {
            node = this.leftChild(node);
        }
        return node;
    }

    public boolean isRoot(Node n) {
        return this.root == n;
    } 

    public boolean isExternal(Node n) {
        return this.leftChild(n) == null && this.rightChild(n) == null; 
    }

    public boolean isInternal(Node n) {
        return this.leftChild(n) != null || this.rightChild(n) != null;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean hasLeftChild(Node n) {
        return this.leftChild(n) != null;
    }

    public boolean hasRightChild(Node n) {
        return this.rightChild(n) != null;
    }
    
    public int size() {
        return this.size;
    }

    public void draw() {
        if (this.isEmpty()) {
            System.out.println("Árvore vazia");
        } else {
            int height = this.height();
            int rows = height + 1;
            int cols = (int) Math.pow(2, rows) - 1;
            String[][] matrix = new String[rows][cols];
            fillMatrix(this.root, matrix, 0, 0, cols - 1);
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    System.out.print(matrix[i][j] == null ? "\t" : matrix[i][j]);
                }
                System.out.println();
            }
        }
    }

    private void fillMatrix(Node n, String[][] matrix, int row, int left,int right) {
        if (n == null || row >= matrix.length || left > right) {
            return;
        }
        int mid = (left + right) / 2;
        matrix[row][mid] = String.valueOf(n.getKey());
        fillMatrix(this.leftChild(n), matrix, row + 1, left, mid - 1);
        fillMatrix(this.rightChild(n), matrix, row + 1, mid + 1, right);
    }
}

public class ArvoreBinariaPesquisaMain {
    public static void main(String args[]) {
        ArvoreBinariaPesquisa arvore = new ArvoreBinariaPesquisa();

        arvore.insert(10);
        arvore.insert(5);
        arvore.insert(15);
        arvore.insert(3);
        arvore.insert(7);
        arvore.insert(12);
        arvore.insert(18);

        arvore.draw();  
    }
}
