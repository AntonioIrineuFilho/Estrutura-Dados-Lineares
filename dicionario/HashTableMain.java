import java.util.Iterator;
import java.util.NoSuchElementException;

class Entry {
    private int key;
    private String element;

    public Entry(int key, String element) {
        this.key = key;
        this.element = element;
    }

    public int getKey() {
        return this.key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getElement() {
        return this.element;
    }

    public void setElement(String element) {
        this.element = element;
    }
}

class HashTable {
    public enum CollisionMethod {
        LINEAR_PROBING,
        DOUBLE_HASHING
    }
    private int size, max_size;
    private Entry[] array;
    private CollisionMethod collisionMethod;
    // Marcador de posicao removida
    private Entry AVAILABLE;
    // Limite do fator de carga
    private double MAX_LOAD_FACTOR;

    public HashTable(int max_size, CollisionMethod collisionMethod) {
        if (max_size < 3) {
            max_size = 3;
        }
        this.max_size = nextPrime(max_size);
        this.size = 0;
        this.array = new Entry[this.max_size];
        this.collisionMethod = collisionMethod;
        this.AVAILABLE = new Entry(0, null);
        this.MAX_LOAD_FACTOR = 0.5;
    }

    // Retorna o proximo numero primo
    private int nextPrime(int number) {
        while (!isPrime(number)) {
            number++;
        }
        return number;
    }

    private boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    // Funcao de dispersao principal: h(k) = k mod N
    private int hash(int key) {
        return Math.floorMod(key, this.max_size);
    }

    // Encontra o maior primo menor que N
    private int secondaryPrime() {
        int q = this.max_size - 1;
        while (q >= 2 && !isPrime(q)) {
            q--;
        }
        return q;
    }

    // Funcao secundaria: d(k) = q - (k mod q)
    private int secondaryHash(int key) {
        int q = this.secondaryPrime();
        return q - Math.floorMod(key, q);
    }

    // Calcula a posicao conforme a tecnica de tratamento de colisao
    private int probeIndex(int key, int attempt) {
        int initialIndex = this.hash(key);
        if (this.collisionMethod == CollisionMethod.LINEAR_PROBING) {
            // Linear probing: (h(k) + j) mod N
            return (initialIndex + attempt) % this.max_size;
        }
        // Hashing duplo: (h(k) + j * d(k)) mod N
        long index = initialIndex + (long) attempt * this.secondaryHash(key);
        return (int) (index % this.max_size);
    }

    // Calcula o fator de carga: alfa = n / N.
    public double loadFactor() {
        return (double) this.size / this.max_size;
    }

    // Duplica a tabela e reinsere os elementos.
    private void rehash() {
        Entry[] oldArray = this.array;
        int oldSize = this.size;
        this.max_size = nextPrime(this.max_size * 2);
        this.array = new Entry[this.max_size];
        this.size = 0;
        for (int i = 0; i < oldArray.length; i++) {
            Entry current = oldArray[i];
            if (current != null && current != AVAILABLE) {
                this.insertWithoutRehash(current.getKey(), current.getElement());
            }
        }
        if (this.size != oldSize) {
            throw new RuntimeException("Erro durante o rehash");
        }
    }

    // Insercao auxiliar sem verificar o fator de carga.
    private void insertWithoutRehash(int key, String element) {
        for (int i = 0; i < this.max_size; i++) {
            int index = this.probeIndex(key, i);
            if (this.array[index] == null ||
                this.array[index] == AVAILABLE) {
                this.array[index] = new Entry(key, element);
                this.size++;
                return;
            }
        }
        throw new RuntimeException("Tabela hash cheia");
    }

    // Insere um item (chave, elemento).
    public void insertItem(int key, String element) {
        if (element == null) {
            throw new IllegalArgumentException("O elemento nao pode ser null");
        }
        // Verifica se a proxima insercao ultrapassara o limite
        double nextLoadFactor = (double) (this.size + 1) / this.max_size;
        if (nextLoadFactor > MAX_LOAD_FACTOR) {
            this.rehash();
        }
        this.insertWithoutRehash(key, element);
    }

    // Busca uma chave e retorna seu elemento
    // Retorna null caso a chave nao exista
    public String findElement(int key) {
        for (int i = 0; i < this.max_size; i++) {
            int index = this.probeIndex(key, i);
            Entry current = this.array[index];
            if (current == null) {
                return null;
            }
            // AVAILABLE nao interrompe a busca
            if (current != AVAILABLE && current.getKey() == key) {
                return current.getElement();
            }
        }
        return null;
    }

    // Remove a primeira ocorrencia da chave
    public String removeElement(int key) {
        for (int i = 0; i < this.max_size; i++) {
            int index = this.probeIndex(key, i);
            Entry current = this.array[index];
            if (current == null) {
                return null;
            }
            if (current != AVAILABLE &&
                current.getKey() == key) {
                String removedElement = current.getElement();
                this.array[index] = AVAILABLE;
                this.size--;
                return removedElement;
            }
        }

        return null;
    }

    // Retorna um Iterator contendo as chaves
    public Iterator<Integer> keys() {
        return new Iterator<Integer>() {
            private int currentIndex = 0;
            // Pula posicoes vazias e removidas
            private void advance() {
                while (this.currentIndex < max_size &&
                    (array[this.currentIndex] == null || array[this.currentIndex] == AVAILABLE)) {
                    this.currentIndex++;
                }
            }
            @Override
            public boolean hasNext() {
                this.advance();
                return this.currentIndex < max_size;
            }
            @Override
            public Integer next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return array[this.currentIndex++].getKey();
            }
        };
    }

    // Retorna um Iterator contendo os elementos
    public Iterator<String> elements() {
        return new Iterator<String>() {
            private int currentIndex = 0;
            private void advance() {
                while (this.currentIndex < max_size &&
                    (array[this.currentIndex] == null || array[this.currentIndex] == AVAILABLE)) {
                    this.currentIndex++;
                }
            }
            @Override
            public boolean hasNext() {
                this.advance();
                return this.currentIndex < max_size;
            }
            @Override
            public String next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return array[this.currentIndex++].getElement();
            }
        };
    }

    public Iterator<String> Elements() {
        return this.elements();
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public int capacity() {
        return this.max_size;
    }

    public void printTable() {
        for (int i = 0; i < this.max_size; i++) {
            System.out.print(i + ": ");

            if (this.array[i] == null) {
                System.out.println("null");

            } else if (this.array[i] == AVAILABLE) {
                System.out.println("AVAILABLE");

            } else {
                System.out.println("(" + this.array[i].getKey() + ", " + this.array[i].getElement() + ")");
            }
        }
    }
}

public class HashTableMain {
    // Imprime os elementos de qualquer Iterator
    private static void printIterator(Iterator<?> iterator) {
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }
    public static void main(String args[]) {
        // TESTE 1: LINEAR PROBING
        System.out.println("========== LINEAR PROBING ==========");
        HashTable linearHash = new HashTable(13, HashTable.CollisionMethod.LINEAR_PROBING);
        int keys[] = {18, 41, 22, 44, 59, 32, 31, 73};
        for (int i = 0; i < keys.length; i++) {
            linearHash.insertItem(keys[i], "Elemento " + keys[i]);
        }
        System.out.println("TAMANHO: " + linearHash.size());
        System.out.println("CAPACIDADE: " + linearHash.capacity());
        System.out.println("FATOR DE CARGA: " + linearHash.loadFactor());
        System.out.println("BUSCA 44: " + linearHash.findElement(44));
        System.out.print("CHAVES: ");
        printIterator(linearHash.keys());
        System.out.print("ELEMENTOS: ");
        printIterator(linearHash.elements());
        System.out.println("REMOVIDO: " + linearHash.removeElement(18));
        System.out.println("BUSCA 44 APOS REMOCAO: " + linearHash.findElement(44));
        // TESTE 2: HASHING DUPLO
        System.out.println();
        System.out.println("========== HASHING DUPLO ==========");
        HashTable doubleHash = new HashTable(13, HashTable.CollisionMethod.DOUBLE_HASHING);
        for (int i = 0; i < keys.length; i++) {
            doubleHash.insertItem(keys[i], "Elemento " + keys[i]);
        }
        System.out.println("TAMANHO: " + doubleHash.size());
        System.out.println("CAPACIDADE: " + doubleHash.capacity());
        System.out.println("FATOR DE CARGA: " + doubleHash.loadFactor());
        System.out.println("BUSCA 31: " + doubleHash.findElement(31));
        System.out.print("CHAVES: ");
        printIterator(doubleHash.keys());
        // TESTE 3: REHASH
        System.out.println();
        System.out.println("========== TESTE DE REHASH ==========");
        HashTable rehashTest = new HashTable(5, HashTable.CollisionMethod.LINEAR_PROBING);
        System.out.println("CAPACIDADE INICIAL: " + rehashTest.capacity());
        for (int i = 1; i <= 10; i++) {
            rehashTest.insertItem(i, "Valor " + i);
            System.out.println("Inseriu chave " + i + " | Tamanho: " + rehashTest.size() + " | Capacidade: " + rehashTest.capacity() + " | Alfa: " + rehashTest.loadFactor());
        }
        System.out.println("BUSCA 7 APOS REHASH: " + rehashTest.findElement(7));
        // TESTE 4: CHAVES REPETIDAS
        System.out.println();
        System.out.println("========== CHAVES REPETIDAS ==========");
        rehashTest.insertItem(7, "Segundo valor da chave 7");
        System.out.println("BUSCA 7: " + rehashTest.findElement(7));
        System.out.println("REMOVIDO: " + rehashTest.removeElement(7));
        System.out.println("BUSCA 7 APOS REMOCAO: " + rehashTest.findElement(7));
        // ESTADO FINAL
        System.out.println();
        System.out.println("========== TABELA FINAL ==========");
        System.out.println("TAMANHO: " + rehashTest.size());
        System.out.println("VAZIA: " + rehashTest.isEmpty());
        rehashTest.printTable();
    }
}