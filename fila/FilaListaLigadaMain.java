class EFilaVazia extends RuntimeException {
    public EFilaVazia() {
        super("Fila Vazia");
    }
}

interface Fila {
    public void enqueue(Object obj);
    public Object dequeue() throws EFilaVazia;
    public Object first() throws EFilaVazia; 
    public int size();
    public boolean isEmpty();
}

class Node {
    private Object element;
    private Node next;

    public Node(Object element) {
        this.element = element;
        this.next = null;
    }

    public void setElement(Object element) {
        this.element = element;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Object getElement() {
        return this.element;
    }

    public Node getNext() {
        return this.next;
    }
}

class FilaComListaLigada implements Fila {
    
    private Node start, end;
    private int size;

    public FilaComListaLigada() {
        this.start = null;
        this.end = null;
        this.size = 0;
    } 

    public void enqueue(Object element) {
        Node newNode = new Node(element);
        if (this.isEmpty()) {
            this.start = newNode;
            this.end = newNode;
        } else {
            this.end.setNext(newNode);
            this.end = newNode;
        }
        this.size++;
    }

    public Object dequeue() {
        if (this.isEmpty()) {
            throw new EFilaVazia();
        }
        Object first = this.start.getElement();
        this.start = this.start.getNext();
        this.size--;
        return first;
    }

    public Object first() {
        if (this.isEmpty()) {
            throw new EFilaVazia();
        }
        return this.start.getElement();
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.start==null;
    }

    public Object[] list() {
        Object[] list = new Object[100];
        Node node = this.start;
        list[0] = node.getElement();
        int i = 1;
        while(node.getNext() != null) {
            node = node.getNext();
            list[i] = node.getElement();
            i++;
        }
        return list;
    }
}

public class FilaListaLigadaMain {
    public static void main(String[] args) {
        FilaComListaLigada test = new FilaComListaLigada();
        for(int i = 0; i < 10; i++) {
            test.enqueue(i);
        }
        Object[] list = test.list();
        for(int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                System.out.print(list[i] + " "); 
            }
        }
        System.out.println();
        for (int i = 0; i < 5; i++) {
            test.dequeue();
        }
        list = test.list();
        for(int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                System.out.print(list[i] + " "); 
            }
        }
        System.out.println();
        System.out.println("PRIMEIRO: " + test.first());
        System.out.println("TAMANHO: " + test.size());
        System.out.println("ESTÁ VAZIO? " + test.isEmpty());        
    }
}