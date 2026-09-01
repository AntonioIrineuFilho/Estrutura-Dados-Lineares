class ELista extends RuntimeException {
    public ELista(String message) {
        super(message);
    }
}

class Node {
    private Node prev, next;
    private Object element;

    public Node(Object element) {
        this.prev = null;
        this.next = null;
        this.element = element;
    }
    public void setPrev(Node prev) {
        this.prev = prev;
    }
    public void setNext(Node next) {
        this.next = next;
    }
    public void setElement(Object element) {
        this.element = element;
    }
    public Node getPrev() {
        return this.prev;
    }
    public Node getNext() {
        return this.next;
    }
    public Object getElement() {
        return this.element;
    }
}

interface Lista {
    public boolean isFirst(Node p) throws ELista;
    public boolean isLast(Node p) throws ELista;
    public Node first();
    public Node last();
    public Node before(Node p) throws ELista;
    public Node after(Node p) throws ELista;
    public void replaceElement(Node p, Object o) throws ELista;
    public void swapElements(Node p, Node q) throws ELista;
    public Node insertBefore(Node p, Object o) throws ELista;
    public Node insertAfter(Node p, Object o) throws ELista;
    public Node insertFirst(Object o);
    public Node insertLast(Object o);
    public Object remove(Node p);
    public int size();
    public boolean isEmpty();
}

class ListaWithListaDuplamenteLigada {
    private int size;
    private Node start, end;
    public ListaWithListaDuplamenteLigada() {
        this.size = 0;
        this.start = null;
        this.end = null;
    }
    public boolean isFirst(Node p) {
        return p==this.start;
    }
    public boolean isLast(Node p) {
        return p==this.end;
    }
    public Node first() {
        if (this.start == null) {
            throw new ELista("Lista vazia");
        }
        return this.start;
    }
    public Node last() {
        if (this.end == null) {
            throw new ELista("Lista vazia");
        }
        return this.end;
    }
    public Node before(Node p) {
        if (p == null) {
            throw new ELista("Posição inválida");
        }
        return p.getPrev();
    }
    public Node after(Node p) {
        if (p == null) {
            throw new ELista("Posição inválida");
        }
        return p.getNext();   
    }
    public void replaceElement(Node p, Object o) {
        if (p == null) {
            throw new ELista("Posição inválida");
        }
        p.setElement(o);   
    }
    public void swapElements(Node p, Node q) {
        if (p == null || q == null) {
            throw new ELista("Posição inválida");
        }
        Object temp = p.getElement();
        p.setElement(q.getElement());
        q.setElement(temp);
    }
    public Node insertBefore(Node p, Object o) {
        if (p == null) {
            throw new ELista("Posição Inválida");
        }
        Node newNode = new Node(o);
        p.getPrev().setNext(newNode);
        newNode.setNext(p);
        newNode.setPrev(p.getPrev());
        p.setPrev(newNode);
        this.size++;
        return newNode;
    }
    public Node insertAfter(Node p, Object o) {
        if (p == null) {
            throw new ELista("Posição Inválida");
        }
        Node newNode = new Node(o);
        newNode.setPrev(p);
        newNode.setNext(p.getNext()); 
        p.getNext().setPrev(newNode);
        p.setNext(newNode);
        this.size++;
        return newNode;
    }
    public Node insertFirst(Object o) {
        Node newNode = new Node(o);
        if (this.isEmpty()) {
            this.start = newNode;
            this.end = newNode;
        } else {
            this.start.setPrev(newNode);
            newNode.setNext(this.start);
            this.start = newNode;
        }
        this.size++;
        return this.start;
    }
    public Node insertLast(Object o) {
        Node newNode = new Node(o);
        if (this.isEmpty()) {
            this.start = newNode;
            this.end = newNode;
        } else {
            this.end.setNext(newNode);
            newNode.setPrev(this.end);
            this.end = newNode;
        }
        this.size++;
        return this.end;
    }
    public Object remove(Node p) {
        if (p == null) {
            throw new ELista("Posição inválida");
        }
        p.getPrev().setNext(p.getNext());
        p.getNext().setPrev(p.getPrev());
        p.setPrev(null);
        p.setNext(null);
        this.size--;
        return p.getElement();
    }
    public int size() {
        return this.size;
    }
    public boolean isEmpty() {
        return this.start==null;
    }
    public void print() {
        Node node = this.start;
        while (node != null) {
            System.out.print(node.getElement() + " ");
            node = node.getNext();
        }
        System.out.println();
    }
}

public class ListaMain {
    public static void main(String args[]) {
        ListaWithListaDuplamenteLigada test = new ListaWithListaDuplamenteLigada();
        for (int i = 0; i < 10; i++) {
            test.insertLast(i);
        }
        test.print();
        System.out.println("PRIMEIRO ELEMENTO: " + test.first().getElement());
        System.out.println("ÚLTIMO ELEMENTO: " + test.last().getElement());
        System.out.println("TOTAL: " + test.size());
        Node first = test.first();
        Node newNode = test.insertAfter(first, 44);
        test.print();
        test.insertBefore(newNode, 43);
        test.print();
        test.swapElements(first, newNode);
        test.print();
        test.replaceElement(first, 444);
        test.print();
    }
}