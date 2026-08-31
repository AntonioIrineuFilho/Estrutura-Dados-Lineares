class EVetor extends RuntimeException {
    public EVetor(String message) {
        super(message);
    }
}

interface Vetor {
    public Object elementAtRank(int r) throws EVetor;
    public Object replaceAtRank(int r, Object o);
    public void insertAtRank(int r, Object o);
    public Object removeAtRank(int r);
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
        if (r >= this.max_size || r < 0) {
            throw new EVetor("Rank fora da capacidade do array");
        }
        if (this.array[r] == null) {
            throw new EVetor("Rank vazio, nada a retornar");
        }
        return this.array[r];
    }

    public Object replaceAtRank(int r, Object o) {
        if (r >= this.max_size || r < 0) {
            throw new EVetor("Rank fora da capacidade do array");
        }
        if (this.array[r] == null) {
            throw new EVetor("Rank vazio, nada a substituir");
        }
        Object old = this.array[r];
        this.array[r] = o;
        return old;
    }

    public void insertAtRank(int r, Object o) {
        if (r >= this.max_size || r < 0) {
            throw new EVetor("Rank fora da capacidade do array");
        }
        this.array[r] = o;  
        this.size++;      
    }

    public Object removeAtRank(int r) {
        if (r >= this.max_size || r < 0) {
            throw new EVetor("Rank fora da capacidade do array");
        }
        Object removed = this.array[r];
        this.array[r] = null;
        this.size--;
        return removed;        
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size==0;
    }

    public Object[] list() {
        return this.array;
    }

}

public class VetorMain {
    public static void main(String[] args) {
        VetorWithArray test = new VetorWithArray(20);
        System.out.println("TAMANHO: " + test.size());
        test.insertAtRank(10, 40);
        test.insertAtRank(0, 34);
        test.insertAtRank(14, 26);
        for (Object obj : test.list()) {
            System.out.print(obj + " ");
        }
        System.out.println();
        System.out.println("TAMANHO: " + test.size());
        test.removeAtRank(0);
        System.out.println("TAMANHO: " + test.size());
        test.replaceAtRank(14, 27);
        System.out.println("TAMANHO: " + test.size());
        System.out.println("Elemento no rank 10: " + test.elementAtRank(10));
        for (Object obj : test.list()) {
            System.out.print(obj + " ");
        }
        System.out.println();
        System.out.println("TAMANHO: " + test.size());
        test.insertAtRank(20, 26);
    }
}