class ELista extends RuntimeException {
    public ELista(String message) {
        super(message);
    }
}

interface Lista {
    public boolean isFirst(int p) throws ELista;
    public boolean isLast(int p) throws ELista;
    public Object first() throws ELista;
    public Object last() throws ELista;
    public Object before(int p) throws ELista;
    public Object after(int p) throws ELista;
    public Object replaceElement(int p, Object o) throws ELista;
    public void swapElements(int p, int q) throws ELista;
    public Object insertBefore(int p, Object o) throws ELista;
    public Object insertAfter(int p, Object o) throws ELista;
    public Object insertFirst(Object o);
    public Object insertLast(Object o);
    public Object remove(int p) throws ELista;
    public int size();
    public boolean isEmpty();
}

class ListaWithArray implements Lista {
    private int size;
    private int max_size;
    private Object[] array;
    public ListaWithArray(int max_size) {
        this.size = 0;
        this.max_size = max_size;
        this.array = new Object[this.max_size];
    }
    public boolean isFirst(int p) {
        if (this.isEmpty()) {
            throw new ELista("Lista vazia");
        }
        return p==0;
    }
    public boolean isLast(int p) {
        if (this.isEmpty()) {
            throw new ELista("Lista vazia");
        }
        return p==this.size()-1;    
    }
    public Object first() {
        if (this.isEmpty()) {
            throw new ELista("Lista vazia");
        }
        return this.array[0];  
    }
    public Object last() {
        if (this.isEmpty()) {
            throw new ELista("Lista vazia");
        }
        return this.array[this.size()-1]; 
    }
    public Object before(int p) {
        if (this.isEmpty()) {
            throw new ELista("Lista vazia");
        }
        if (p <= 0 || p >= this.size()) {
            throw new ELista("Posição inválida");
        }
        return this.array[p-1];
    }
    public Object after(int p) {
        if (this.isEmpty()) {
            throw new ELista("Lista vazia");
        }
        if (p < 0 || p >= this.size()-1) {
            throw new ELista("Posição inválida");
        }
        return this.array[p+1];
    }
    public Object replaceElement(int p, Object o) {
        if (this.isEmpty()) {
            throw new ELista("Lista vazia");
        }
        if (p < 0 || p >= this.size()) {
            throw new ELista("Posição inválida");
        }
        Object old = this.array[p];
        this.array[p] = o;
        return old;
    }
    public void swapElements(int p, int q) {
        if (this.isEmpty()) {
            throw new ELista("Lista vazia");
        }
        if (p < 0 || p >= this.size() || q < 0 || q >= this.size()) {
            throw new ELista("Posição inválida");
        }
        Object temp = this.array[q];
        this.array[q] = this.array[p];
        this.array[p] = temp;
    }
    public Object insertBefore(int p, Object o) {
        if (this.isEmpty()) {
            throw new ELista("Lista vazia");
        }
        if (p <= 0 || p >= this.size()) {
            throw new ELista("Posição inválida");
        }
        if (this.size() == this.max_size) {
            this.max_size *= 2;
            Object[] newArray = new Object[this.max_size];
            for (int i = 0; i < this.size(); i++) {
                newArray[i] = this.array[i];
            }
            this.array = newArray;
        }
        Object current = this.array[p];
        for (int i = p; i < this.size(); i++) {
            Object next = this.array[i+1];
            this.array[i+1] = current;
            current = next;
        }
        this.size++;
        this.array[p] = o;
        return this.array[p];
    }
    public Object insertAfter(int p, Object o) {
        if (this.isEmpty()) {
            throw new ELista("Lista vazia");
        }
        if (p < 0 || p > this.size()-1) {
            throw new ELista("Posição inválida");
        }
        if (this.size() == this.max_size) {
            this.max_size *= 2;
            Object[] newArray = new Object[this.max_size];
            for (int i = 0; i < this.size(); i++) {
                newArray[i] = this.array[i];
            }
            this.array = newArray;
        }
        Object current = this.array[p+1];
        for (int i = p+1; i < this.size(); i++) {
            Object next = this.array[i+1];
            this.array[i+1] = current;
            current = next;
        }
        this.array[p+1] = o;
        this.size++;
        return this.array[p+1];
    }
    public Object insertFirst(Object o) {
        if (this.size() == this.max_size) {
            this.max_size *= 2;
            Object[] newArray = new Object[this.max_size];
            for (int i = 0; i < this.size(); i++) {
                newArray[i] = this.array[i];
            }
            this.array = newArray;
        }
        Object current = this.array[0];
        for(int i = 0; i < this.size(); i++) {
            Object next = this.array[i+1];
            this.array[i+1] = current;
            current = next;
        }
        this.array[0] = o;
        this.size++;
        return this.array[0];
    }
    public Object insertLast(Object o) {
        if (this.size() == this.max_size) {
            this.max_size *= 2;
            Object[] newArray = new Object[this.max_size];
            for (int i = 0; i < this.size(); i++) {
                newArray[i] = this.array[i];
            }
            this.array = newArray;
        }
        this.array[this.size()] = o;
        this.size++;
        return this.array[this.size()-1];
    }
    public Object remove(int p) {
        if (this.isEmpty()) {
            throw new ELista("Lista vazia");
        }
        if (p < 0 || p >= this.size()) {
            throw new ELista("Posição inválida");
        }
        Object removed = this.array[p];
        Object current = this.array[this.size()-1];
        for (int i = this.size()-1; i > p; i--) {
            Object prev = this.array[i-1];
            this.array[i-1] = current;
            current = prev;
        }
        this.array[this.size()-1] = null;
        this.size--;
        return removed;
    }
    public int size() {
        return this.size;
    }
    public boolean isEmpty() {
        return this.size==0;
    }
    public void print() {
        for (int i = 0; i < this.size(); i++) {
            System.out.print(this.array[i] + " ");
        }
        System.out.println();
    }
}

public class ListaArrayMain {
    public static void main(String args[]) {
        ListaWithArray test = new ListaWithArray(20);
        for (int i = 0; i < 20; i++) {
            test.insertFirst(i);
        }
        test.print();
        for (int i = 0; i < 20; i++) {
            test.insertLast(i);
        }
        test.print();
        test.insertBefore(1, 777);
        test.print();
        test.insertAfter(10, 777);
        test.print();
        System.out.println("ANTES DA POSIÇÃO 1: " + test.before(1));
        System.out.println("DEPOIS DA POSIÇÃO 40: " + test.after(40));
        System.out.println("TAMANHO DA LISTA: " + test.size());
        test.remove(11);
        test.print();
        System.out.println("TAMANHO DA LISTA: " + test.size());
    }
}