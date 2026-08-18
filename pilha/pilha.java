class EPilhaVazia extends RuntimeException {
    public EPilhaVazia() {
        super("Pilha Vazia");
    }
}

interface Pilha {
    public void push(Object object);
    public Object top() throws EPilhaVazia;
    public Object pop() throws EPilhaVazia;
    public int size();
    public boolean isEmpty();
}

public class PilhaWithArray implements Pilha {
    public int max_size;
    public int top;
    public int constant;
    public Object[] array;

    public PilhaWithArray(int max_size, int constant) {
        this.max_size = max_size;
        this.top = -1;
        this.constant = constant;
        this.array = new Object[this.size];
    }

    public void push(Object obj) {
        if (this.array.length == this.max_size) {
            int newMaxSize = this.max_size;
            if (this.constant > 0) { newSize += this.constant; }
            else { newMaxSize *= 2; }
            Object[] newArray = new Object[newMaxSize];
            for (int i = 0; i < this.max_size; i++) {
                newArray[i] = this.array[i];
            }
            this.array = newArray;
            this.size = newMaxSize;
        }
        this.top++;
        this.array[this.top] = obj;
    }

    public Object top() {
        if (this.isEmpty()) { throw new EPilhaVazia(); }
        return this.array[this.top];
    }

    public Object pop() {
        if (this.isEmpty()) { throw new EPilhaVazia(); }
        Object last = this.array[this.top];
        this.top--;
        return last;
    }

    public int size() {
        return this.top+1;
    }

    public boolean isEmpty() {
        return this.top==-1;
    }

    public Object[] print() {
        Object[] printArray = new Object[this.size()];
        for (int i = 0; i < this.size(); i++) {
            printArray[i] = this.array[i];
        }
        return printArray;
    }

}    