class EPilhaVazia extends RuntimeException {
    public EPilhaVazia() {
        super("Pilha Vazia");
    }
}

interface Pilha {
    public void push(Object element);
    public Object pop() throws EPilhaVazia;
    public Object top() throws EPilhaVazia;
    public boolean isEmpty();
    public int size();
}

class Node {
    private Object element;
    private Node next;

    public Node(Object element) {
        this.element = element;
        this.next = null;
    }

    public Object getElement() {
        return this.element;
    }

    public Node getNext() {
        return this.next;
    }

    public void setElement(Object element) {
        this.element = element;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}

class PilhaComListaLigada implements Pilha {

    private Node top;
    private int size;

    public PilhaComListaLigada() {
        this.top = null;
        this.size = 0;
    }

    public void push(Object element) { 
        Node newNode = new Node(element);
        if (this.top == null) {
            this.top = newNode;
        } else {
            newNode.setNext(this.top);
            this.top = newNode;
        }
        this.size++;
    }

    public Object top() {
        if (this.isEmpty()) {
            throw new EPilhaVazia();
        }
        return this.top.getElement();
    }

    public Object pop() {
        if (this.isEmpty()) {
            throw new EPilhaVazia();
        }
        Object element = this.top.getElement();
        if (this.top.getNext() == null) {
            this.top = null;
        } else {
            this.top = this.top.getNext();
        }
        this.size--;
        return element;

    }

    public boolean isEmpty() {
        return this.top==null;
    }

    public int size() {
        return this.size;
    }

    public Object[] list() {
        Object[] elements = new Object[15];
        Node node = this.top;
        Object element;
        if (!this.isEmpty()) {
            element = node.getElement();
            int i = 0;
            elements[i] = element;
            while (node.getNext() != null) {
                i++;
                node = node.getNext();
                element = node.getElement();
                elements[i] = element;
            }
        }
        return elements;
    }

}

public class PilhaListaLigadaMain {
    public static void main(String[] args) {
        PilhaComListaLigada test = new PilhaComListaLigada();
        for (int i = 1; i < 11; i++) {
            test.push(i);
        }
        for (Object obj : test.list()) {
            if (obj != null) {
                System.out.print(obj + " ");
            }
        }
        System.out.println();

        for (int i = 1; i < 6; i++) {
            test.pop();
        }

        for (Object obj : test.list()) {
            if (obj != null) {
                System.out.print(obj + " ");
            }
        }
        System.out.println();
        System.out.println("TOPO: " + test.top());
        System.out.println("TAMANHO: " + test.size());
        System.out.println("ESTÁ VAZIO? " + test.isEmpty());
    }
}