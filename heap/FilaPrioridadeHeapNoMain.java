import java.util.Random;

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
            newNode.setParent(this.root);
            this.last = newNode;
        } else {
            // se o último nó for um filho esquerdo, basta adicionar o direito no pai
            if (this.last.getParent().getLeftChild() == this.last) {
                this.last.getParent().setRightChild(newNode);
                newNode.setParent(this.last.getParent());
                this.last = newNode;
            } else {
                // atualiza current com raiz ou filho esquerdo | depois atualiza com filho direito | depois pega filho esquerdo e atualiza com novo nó
                Node current = this.last.getParent();
                while (current != this.root) {
                    if (current.getParent().getLeftChild() == current) {
                        // se o current for diferente de raiz aí deve ir para a direita primeiro antes de buscar o folha mais à esquerda
                        current = current.getParent().getRightChild();
                        break;
                    }
                    // se for raiz nunca chega no filho esquerdo, ou seja, só vai pela direita
                    // se só vai pela direita é porque o heap tá completo, então basta ir até o folha mais à esquerda
                    current = current.getParent();
                }
                while (current.getLeftChild() != null) {
                    current = current.getLeftChild();
                }
                current.setLeftChild(newNode);
                newNode.setParent(current);
                this.last = newNode;
            }
        }
        // aplica o upheap para ajustar ordenação
        this.upheap();
        this.size++;
    }

    private void upheap() {
        Node current = this.last;
        while(current != this.root) {
            if (current.getKey() > current.getParent().getKey()) {
                break;
            }
            int parentKey = current.getParent().getKey();
            current.getParent().setKey(current.getKey());
            current.setKey(parentKey);
            current = current.getParent();
        }
    }

    public int removeMin() {
        if (this.isEmpty()) {
            throw new RuntimeException("HEAP Vazio");
        }
        int removed = this.root.getKey();
        if (this.size() == 1) {
            this.root = null;
            this.last = null;
        } else {
            this.root.setKey(this.last.getKey());
            Node lastParent = this.last.getParent();
            this.last.setParent(null);
            if (lastParent.getRightChild() == this.last) {
                lastParent.setRightChild(null);
                this.last = lastParent.getLeftChild();
                // aplica o downheap para ajustar ordenação
                this.downheap();
            } else {
                lastParent.setLeftChild(null);
                // atualiza current com raiz ou filho direito | depois atualiza com filho esquerdo | depois pega filho direito e atualiza com novo nó
                Node current = lastParent;
                while (current != this.root) {
                    if (current.getParent().getRightChild() == current) {
                        current = current.getParent().getLeftChild();
                        break;
                    }
                    current = current.getParent();
                }
                while (current.getRightChild() != null) {
                    current = current.getRightChild();
                }
                this.last = current;
                // aplica o downheap para ajustar ordenação
                this.downheap();
            }
        }
        this.size--;
        return removed;
    }

    private void downheap() {
        // da raiz até um folha ou ao pai ser menor q os filhos
        // se o pai for maior que um dos filhos troca a chave com pai e segue na sub arvore do filho trocado
        Node current = this.root;
        while (current.getLeftChild() != null) {
            Node smallestChild = current.getLeftChild();
            if (current.getRightChild() != null && current.getLeftChild().getKey() > current.getRightChild().getKey()) {
                smallestChild = current.getRightChild();
            }
            if (smallestChild.getKey() < current.getKey()) {
                int parentKey = current.getKey();
                current.setKey(smallestChild.getKey());
                smallestChild.setKey(parentKey);
                current = smallestChild;
            } else {
                break;
            }
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.root==null;
    }
}

public class FilaPrioridadeHeapNoMain {
    public static void main(String args[]) {
        FilaPrioridadeHeapNo heap = new FilaPrioridadeHeapNo();
        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            int n = random.nextInt(100);
            heap.insert(n);
        }
        for (int i = 0; i < 20; i++) {
            System.out.println(heap.removeMin());
        }
    }
}