class EVetorRankInvalido extends RuntimeException {
    public EVetorRankInvalido() {
        super("Rank inválido");
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

interface Vetor {
    public Object elementAtRank(int r) throws EVetorRankInvalido;
    public Object replaceAtRank(int r, Object o) throws EVetorRankInvalido;
    public void insertAtRank(int r, Object o) throws EVetorRankInvalido;
    public  Object removeAtRank(int r) throws EVetorRankInvalido;
    public int size();
    public boolean isEmpty();
}

class VetorListaDuplamenteLigada {
    private int size;
    private Node start;
    public VetorListaDuplamenteLigada() {
        this.size = 0;
        this.start = null;
    }
    public Object elementAtRank(int r) {
        if (r >= this.size() || r < 0) {
            throw new EVetorRankInvalido();
        }
        Node node = this.start;
        int i = 0;
        while (i < r) {
            node = node.getNext();
            i++;
        }
        return node.getElement();
    }
    public Object replaceAtRank(int r, Object o) {
        if (r >= this.size() || r < 0) {
            throw new EVetorRankInvalido();
        }
        Node node = this.start;
        int i = 0;
        while (i < r) {
            node = node.getNext();
            i++;
        }
        Object old = node.getElement();
        node.setElement(o);
        return old;
    }
    public void insertAtRank(int r, Object o) {
        if (r > this.size() || r < 0) {
            throw new EVetorRankInvalido();
        }
        Node newNode = new Node(o);
        if (this.start == null) {
            this.start = newNode;
        } else {
            Node node = this.start;
            int i = 0;
            while (i < r) {
                if (i < this.size() - 1) {
                    node = node.getNext();
                }
                i++;
            }
            if (r == this.size()) {
                node.setNext(newNode);
                newNode.setPrev(node);
            } else {
                Node old_prev = node.getPrev();
                node.setPrev(newNode);
                newNode.setPrev(old_prev);
                newNode.setNext(node);
            }
        }
        this.size++;
    }
    public Object removeAtRank(int r) {
        if (r >= this.size() || r < 0) {
            throw new EVetorRankInvalido();
        }
        Node node = this.start;
        int i = 0;
        while (i < r) {
            node = node.getNext();
            i++;
        }
        Object removed = node.getElement();
        if (this.size() == 1) {
            this.start = null;
        } else if (node == this.start) {
            this.start = node.getNext();
            this.start.setPrev(null);
            node.setNext(null);
        } else if (r == this.size() - 1) {
            node.getPrev().setNext(null);
            node.setPrev(null);
        } else {
            node.getPrev().setNext(node.getNext());
            node.getNext().setPrev(node.getPrev());
            node.setPrev(null);
            node.setNext(null);
        }
        this.size--;
        return removed;
    }
    public int size() {
        return this.size;
    }
    public boolean isEmpty() {
        return this.start==null;
    }
    public void print() {
        Node node = this.start;
        for (int i = 0; i < this.size(); i++) {
            System.out.print(node.getElement() + " ");
            node = node.getNext();
        }
        System.out.println();
    }
}

public class VetorListaDuplamenteLigadaMain {
    public static void main(String[] args) {
        VetorListaDuplamenteLigada test = new VetorListaDuplamenteLigada();
        for (int i = 1; i < 11; i++) {
            test.insertAtRank(i-1, i);
        }
        test.print();
        System.out.println("ELEMENTO NO RANK 6: " + test.elementAtRank(6));
        System.out.println("SUBSTITUINDO ELEMENTO RANK 6: " + test.replaceAtRank(6, 77));
        test.print();
        System.out.println("REMOVENDO ELEMENTO RANK 0: " + test.removeAtRank(0));
        test.print();
        System.out.println("REMOVENDO ELEMENTO RANK 9: " + test.removeAtRank(9));
        test.print();
    }
}

