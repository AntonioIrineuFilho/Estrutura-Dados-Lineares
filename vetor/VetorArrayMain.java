class EVetorRankInvalido extends RuntimeException {
    public EVetorRankInvalido() {
        super("Rank inválido");
    }
}

interface Vetor {
    public Object elementAtRank(int r) throws EVetorRankInvalido;
    public Object replaceAtRank(int r, Object o) throws EVetorRankInvalido;
    public void insertAtRank(int r, Object o) throws EVetorRankInvalido;
    public Object removeAtRank(int r) throws EVetorRankInvalido;
    public int size();
    public boolean isEmpty();
}

class VetorWithArray implements Vetor {

    private int size;
    private int max_size;
    private Object[] array;

    public VetorWithArray(int max_size) {
        this.size = 0;
        this.max_size = max_size;
        this.array = new Object[this.max_size];
    }

    public Object elementAtRank(int r) {
        if (r > this.size() || r < 0) {
            throw new EVetorRankInvalido();
        }
        return this.array[r];
    }

    public Object replaceAtRank(int r, Object o) {
        if (r > this.size() || r < 0) {
            throw new EVetorRankInvalido();
        }
        Object old = this.array[r];
        this.array[r] = o;
        return old;
    }

    public void insertAtRank(int r, Object o) { 
        if (r > this.size() || r < 0) {
            throw new EVetorRankInvalido();
        }
        if (this.size() == this.max_size) {
            this.max_size *= 2;
            Object[] newArray = new Object[this.max_size];
            for (int i = 0; i < this.size(); i++) {
                newArray[i] = this.array[i];
            }
            this.array = newArray;
        }
        Object current = this.array[r];
        for (int i = r; i < this.size(); i++) {
            Object next = this.array[i+1];
            this.array[i+1] = current;
            current = next;
        }
        this.array[r] = o;
        this.size++;
    }

    public Object removeAtRank(int r) {    
        if (r >= this.size() || r < 0) {
            throw new EVetorRankInvalido();
        }
        Object obj = this.array[r];
        Object current = this.array[this.size()-1];
        for (int i = this.size()-1; i > r; i--) {
            Object prev = this.array[i-1];
            this.array[i-1] = current;
            current = prev;
        }
        this.array[this.size()-1] = null;
        this.size--;
        return obj;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size==0;
    }

    public void print() {
        for (int i = 0; i < this.max_size; i++) {
            System.out.print(this.array[i] + " ");
        }
        System.out.println();
    }

}

public class VetorArrayMain {
    public static void main(String[] args) {
        VetorWithArray test = new VetorWithArray(20);
        System.out.println("TAMANHO: " + test.size());
        for (int i = 0; i < 20; i++) {
            test.insertAtRank(i, i);
        }
        test.print();
        test.insertAtRank(20, 20);
        test.print();
        test.removeAtRank(19);
        test.print();
    }
}