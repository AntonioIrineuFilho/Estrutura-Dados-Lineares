class Node {
    int key;
    Node parent, leftChild, rightChild;

    public Node (int key) {
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

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public void setKey(int key) {
        this.key = key;
    }
}

class FilaPrioridadeHeapNo {
    private Node root, last;
    private int size;

    public FilaPrioridadeHeapNo() {
        this.root = null;
        this.last = null;
        this.size = 0;
    }

    public Node min() {
        if (this.isEmpty()) {
            throw new RuntimeException("HEAP Vazio");
        }
        return this.root;
    }
    // 1 - encontrar último nó | 2 - encontrar onde inserir novo nó | 3 - fazer o upheap para restaurar ordem
    public void insert(int key) {
        Node newNode = new Node(key);
        if (this.isEmpty()) {
            this.root = newNode;
            this.last = newNode;
        } else if (this.size() == 1) {
            this.root.setLeftChild(newNode);
            this.last = newNode;
        } else {
            // se o último nó for um filho esquerdo, basta adicionar o direito no pai
            if (this.last.getParent().getLeftChild() == this.last) {
                this.last.setRightChild(newNode);
                newNode.setParent(this.last);
                this.last = newNode;
            } else {
                // atualiza current com raiz ou filho esquerdo | depois atualiza com filho direito | depois pega filho esquerdo e atualiza com novo nó
                Node current = this.last;
                while (current != this.root) {
                    if (current.getParent().getLeftChild() == current) {
                        current = current.getParent().getLeftChild();
                        break;
                    }
                    current = current.getParent();
                }
                current = current.getRightChild();
                while (current.getLeftChild() != null) {
                    current = current.getLeftChild();
                }
                current.setLeftChild(newNode);
                newNode.setParent(current);
                this.last = newNode;
                // aplica o upheap para ajustar ordenação
            }
            this.size++;
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.root==null;
    }


}