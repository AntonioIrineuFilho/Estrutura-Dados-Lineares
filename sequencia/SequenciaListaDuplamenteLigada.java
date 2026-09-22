class ESequencia extends RuntimeException {
    public ESequencia(String message) {
        super(message);
    }
}

interface Sequencia {
    public Object elemAtRank(int r) throws ESequencia;
    public Object replaceAtRank(int r, Object o) throws ESequencia;
    public void insertAtRank(int r, Object o) throws ESequencia;
    public Object removeAtRank(int r) throws ESequencia;
    public Node atRank(int r) throws ESequencia; 
    public int rankOf(Node n) throws ESequencia;
    public Node first() throws ESequencia;
    public Node last() throws ESequencia;
    public Node before(Node n) throws ESequencia;
    public Node after(Node n) throws ESequencia;
    public Node replaceElement(Node n) throws ESequencia;
    public void swapElements(Node n, Node q) throws ESequencia;
    public Node insertBefore(Node n) throws ESequencia;
    public Node insertAfter(Node n) throws ESequencia;
    public Node insertFirst(Object o) throws ESequencia;
    public Node insertLast(Object o) throws ESequencia;
    public Object remove(Node n) throws ESequencia;
}

class Node {
    private Object element;
    private Node prev, next;
    public Node(Object element) {
        this.prev = null;
        this.next = null;
        this.element = element;
    }
    public void setElement(Object element) {
        this.element = element;
    }
    public void setPrev(Node prev) {
        this.prev = prev;
    }
    public void setNext(Node next) {
        this.next = next;
    }
    public Object getElement() {
        return this.element;
    }
    public Node getPrev() {
        return this.prev;
    }
    public Node getNext() {
        return this.next;
    }
}

class SequenciaListaDuplamenteLigada implements Sequencia {
    private Node first, last;
    private int size;
    public SequenciaListaDuplamenteLigada() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }
    public Object elemAtRank(int r) {
        if (this.isEmpty()) {
            throw new ESequencia("Lista vazia");
        }
        if (r < 0 || r >= this.size()) {
            throw new ESequencia("Rank inválido");
        }
        Node node = this.first;
        int i = 0;
        while (i < r) {
            node = node.getNext();
            i++;
        }
        return node.getElement();
    }
    public Object replaceAtRank(int r, Object o) {
        if (this.isEmpty()) {
            throw new ESequencia("Lista vazia");
        }
        if (r < 0 || r >= this.size()) {
            throw new ESequencia("Rank inválido");
        }
        Node node = this.first;
        int i = 0;
        while(i < r) {
            node = node.getNext();
            i++;
        }
        Object old = node.getElement();
        node.setElement(o);
        return old;
    }
    public void insertAtRank(int r, Object o) {
        if (r < 0 || r > this.size()) {
            throw new ESequencia("Rank inválido");
        }
        Node newNode = new Node(o);
        if (this.isEmpty()) {
            this.first = newNode;
            this.last = newNode;
        } else if (r == this.size()) {
            newNode.setPrev(this.last);
            this.last.setNext(newNode);
            this.last = newNode;
        } else {
            Node node = this.first;
            int i = 0;
            while (i < r) {
                node = node.getNext();
                i++;
            }
            newNode.setNext(node);
            newNode.setPrev(node.getPrev());
            newNode.getPrev().setNext(newNode);
            node.setPrev(newNode);
        }
        this.size++;
    }
    public Object removeAtRank(int r) {
        if (r < 0 || r >= this.size()) {
            throw new ESequencia("Rank inválido");
        }
        Object removedElement;
        if (r == 0) {
            removedElement = this.first.getElement();
            if (this.size() > 1) {
                Node newFirst = this.first.getNext(); 
                newFirst.setPrev(null);
                this.first.setNext(null);
                this.first = newFirst;
            } else {
                this.first = null;
                this.last = null;
            }
        } else if (r == this.size() - 1) {
            removedElement = this.last.getElement();
            Node newLast = this.last.getPrev();
            newLast.setNext(null);
            this.last.setPrev(null);
            this.last = newLast;
        } else {
            Node toRemove = this.first;
            int i = 0;
            while(i < r) {
                toRemove = toRemove.getNext();
                i++;
            }
            removedElement = toRemove.getElement();
            toRemove.getNext().setPrev(toRemove.getPrev());
            toRemove.getPrev().setNext(toRemove.getNext());
            toRemove.setPrev(null);
            toRemove.setNext(null);
        }
        this.size--;
        return removedElement;
    }

    public Node atRank(int r) {
        if (r < 0 || r >= this.size()) {
            throw new ESequencia("Rank inválido");
        }
        Node node = this.first;
        int i = 0;
        while (i < r) {
            node = node.getNext();
            i++;
        }
        return node;
    }

    public int rankOf(Node n) {
        if (n == null) {
            throw new ESequencia("Nó inválido");
        }
        Node node = this.first;
        int i = 0;
        while (node != n) {
            if (node == null) {
                throw new ESequencia("Nó inválido");
            }
            node = node.getNext();
            i++;
        }
        return i;
    }

    public Node first() {
        if (this.isEmpty()) {
            throw new ESequencia("Lista vazia");
        }
        return this.first;
    }

    public Node last() {
        if (this.isEmpty()) {
            throw new ESequencia("Lista vazia");
        }
        return this.last;
    }

    public Node before(Node n) {
        if (n == null) {
            throw new ESequencia("Nó inválido");
        }
        if (n == this.first) {
            throw new ESequencia("Não é possível retornar um nó anterior ao primeiro");
        }
        return n.getPrev();
    }

    public Node after(Node n) {
        if (n == null) {
            throw new ESequencia("Nó inválido");
        }
        if (n == this.last) {
            throw new ESequencia("Não é possível retornar um nó superior ao último");
        }
        if (this.size() == 1) {
            throw new ESequencia("Lista possui apenas um elemento");
        }
        return n.getNext(); 
    }

    public Object replaceElement(Node n, Object o) {
        if (n == null) {
            throw new ESequencia("Nó inválido");
        }
        Object old = n.getElement();
        n.setElement(o);
        return old;
    }

    public void swapElements(Node n, Node q) {
        if (n == null || q == null) {
            throw new ESequencia("Nó inválido");
        }
        Object temp = n.getElement();
        n.setElement(q.getElement());
        q.setElement(temp);
    }

    public Node insertBefore(Node n, Object o) {
        if (n == null) {
            throw new ESequencia("Nó inválido");
        }
        Node newNode = new Node(o);
        if (n == this.first) {
            newNode.setNext(this.first);
            this.first.setPrev(newNode);
            this.first = newNode;
        } else {
            newNode.setNext(n);
            newNode.setPrev(n.getPrev());
            n.setPrev(newNode);
        }
        this.size++;
        return newNode;
    }

    public Node insertAfter(Node n, Object o) {
        if (n == null) {
            throw new ESequencia("Nó inválido");
        }
        Node newNode = new Node(o);
        if (n == this.last) {
            this.last.setNext(newNode);
            newNode.setPrev(this.last);
            this.last = newNode;
        } else {
            newNode.setPrev(n);
            newNode.setNext(n.getNext());
            n.setNext(newNode);
        }
        this.size++;
        return newNode;
    }

    public Node insertFirst(Object o) {
        Node newNode = new Node(o);
        if (this.size() == 0) {
            this.first = newNode;
            this.last = newNode;
        } else {
            newNode.setNext(this.first);
            this.first.setPrev(newNode);
            this.first = newNode;
        }
        this.size++;
        return newNode;
    }

    public Node insertLast(Object o) {
        Node newNode = new Node(o);
        if (this.size == 0) {
            this.first = newNode;
            this.last = newNode;
        } else {
            this.last.setNext(newNode);
            newNode.setPrev(this.last);
            this.last = newNode;
        }
        this.size++;
        return newNode;
    }

    public Node remove(Node n) {
        if (n == null) {
            throw new ESequencia("Nó inválido");
        }
        if (n == this.first && n == this.last) {
            this.first = null;
            this.last = null;
        } else if (n == this.first) {
            this.first.getNext().setPrev(null);
            this.first = this.first().getNext();
        } else if (n == this.last) {
            this.last.getPrev().setNext(null);
            this.last = this.last.getPrev();
        } else {
            n.getPrev().setNext(n.getNext());
            n.getNext().setPrev(n.getPrev());
            n.setPrev(null);
            n.setNext(null);
        }
        this.size--;
        return n;
    }

    public int size() {
        return this.size;
    }
    public boolean isEmpty() {
        return this.first == null && this.last == null;
    }
}