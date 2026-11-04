import java.util.Random;

class FilaPrioridadeHeapArray {
    private int first, last, size, max_size;
    private Integer[] array;
    
    public FilaPrioridadeHeapArray(int max_size) {
        this.last = 0;
        this.size = 0;
        this.max_size = max_size;
        this.array = new Integer[this.max_size];
    }

    private void duplicateArray() {
        Integer[] newArray = new Integer[this.max_size * 2];
        for (int i = 0; i < this.last+1; i++) {
            newArray[i] = this.array[i];
        }
        this.array = newArray;
        this.max_size *= 2;
    }

    public int min() {
        if (this.isEmpty()) {
            throw new RuntimeException("HEAP Vazio");
        }
        return this.array[1];
    }

    public void insert(int key) {
        if (this.last + 1 == this.max_size) {
            this.duplicateArray();
        }
        this.array[++this.last] = key;
        this.size++;
        this.upheap();
    }

    private void upheap() {
        int current = this.last;
        while (current != 1) {
            if (this.array[current] > this.array[(int) current/2]) {
                break;
            }
            int parentKey = this.array[(int) current/2];
            this.array[(int) current/2] = this.array[current];
            this.array[current] = parentKey;
            current = (int) current/2;
        }
    }

    public int removeMin() {
        if (this.isEmpty()) {
            throw new RuntimeException("HEAP Vazio");
        }
        int removed = this.array[1];
        if (this.size() > 1) {
            this.array[1] = this.array[this.last];
        } 
        this.array[this.last--] = null;
        this.size--;
        this.downheap();
        return removed;
    }

    private void downheap() {
        int current = 1;
        while (current*2 <= this.last) {
            int leftChildIndex = current*2;
            int rightChildIndex = current*2+1;
            int smallestChildIndex = leftChildIndex;
            if (rightChildIndex <= this.last && this.array[leftChildIndex] > this.array[rightChildIndex]) {
                smallestChildIndex = rightChildIndex;
            }
            if (this.array[smallestChildIndex] < this.array[current]) {
                int parentKey = this.array[current];
                this.array[current] = this.array[smallestChildIndex];
                this.array[smallestChildIndex] = parentKey;
                current = smallestChildIndex; 
            } else {
                break;
            }
        }
    }

    public boolean isEmpty() {
        return this.last==0;
    }

    public int size() {
        return this.size;
    }
}

public class FilaPrioridadeHeapArrayMain {
    public static void main(String args[]) {
        FilaPrioridadeHeapArray heap = new FilaPrioridadeHeapArray(10);
        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            int n = random.nextInt(100);
            heap.insert(n);
        }
        System.out.println("TAMANHO: " + heap.size());
        for (int i = 0; i < 20; i++) {
            System.out.print(heap.removeMin() + " ");
        }
        System.out.println();
        System.out.println("TAMANHO: " + heap.size());
    }
}