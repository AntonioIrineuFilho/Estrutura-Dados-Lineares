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
    public Object first();
    public Object last();
    public Object before(Node p) throws ELista;
    public Object after(Node p) throws ELista;
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
    public Object first() {
        if (this.start == null) {
            throw new ELista("Lista vazia");
        }
        return this.start.getElement();
    }
    public Object last() {
        if (this.end == null) {
            throw new ELista("Lista vazia");
        }
        return this.end.getElement();
    }
    public Object before(Node p) {
        if (p == null) {
            throw new ELista("Posição inválida");
        }
        return p.getPrev().getElement();
    }
    public Object after(Node p) {
        if (p == null) {
            throw new ELista("Posição inválida");
        }
        return p.getNext().getElement();   
    }
    public int size() {
        return this.size;
    }
    public boolean isEmpty() {
        return this.start==null;
    }
}