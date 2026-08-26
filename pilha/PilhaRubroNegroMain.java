class EPilhaVazia extends RuntimeException {
    public EPilhaVazia() {
        super("Pilha Vazia");
    }
}

interface PilhaRubroNegro {
    public void pushPilhaRubro(Object obj);
    public void pushPilhaNegro(Object obj);
    public Object popPilhaRubro() throws EPilhaVazia;
    public Object popPilhaNegro() throws EPilhaVazia;
    public Object topPilhaRubro() throws EPilhaVazia;
    public int sizePilhaRubro();
    public int sizePilhaNegro();
    public boolean isEmptyPilhaRubro();
    public boolean isEmptyPilhaNegro();
}

class PilhaRubroNegroArray implements PilhaRubroNegro {

    private int max_size;
    private int top_rubro, top_negro;
    private Object[] array;

    public PilhaRubroNegroArray(int max_size) {
        this.max_size = max_size;
        this.top_rubro = -1;
        this.top_negro = max_size;
        this.array = new Object[this.max_size];
    }

    public void pushPilhaRubro(Object obj) {
        if (this.top_rubro + 1 == this.top_negro || (this.sizePilhaRubro() + this.sizePilhaNegro()) == this.max_size / 3) {
            int newMaxSize;
            if (this.top_rubro + 1 == this.top_negro) { 
                newMaxSize = this.max_size * 2; 
            }
            else { 
                if (this.max_size % 2 == 0) { newMaxSize = this.max_size / 2; }
                else { newMaxSize = (this.max_size - 1) / 2; }
            }
            Object[] newArray = new Object[newMaxSize];
            for (int i = 0; i < this.sizePilhaRubro(); i++) {
                newArray[i] = this.array[i];
            }
            for (int i = 0; i < this.sizePilhaNegro(); i++) {
                newArray[newMaxSize-1-i] = this.array[this.max_size-1-i];
            }
            this.top_negro = newMaxSize - this.sizePilhaNegro();
            this.max_size = newMaxSize;
            this.array = newArray;
        } 
        this.array[++this.top_rubro] = obj;
    }

    public void pushPilhaNegro(Object obj) {
        if (this.top_rubro + 1 == this.top_negro || (this.sizePilhaRubro() + this.sizePilhaNegro()) == this.max_size / 3) {
            int newMaxSize;
            if (this.top_rubro + 1 == this.top_negro) { 
                newMaxSize = this.max_size * 2; 
            }
            else { 
                if (this.max_size % 2 == 0) { newMaxSize = this.max_size / 2; }
                else { newMaxSize = (this.max_size - 1) / 2; }
            }
            Object[] newArray = new Object[newMaxSize];
            for (int i = 0; i < this.sizePilhaRubro(); i++) {
                newArray[i] = this.array[i];
            }
            for (int i = 0; i < this.sizePilhaNegro(); i++) {
                newArray[newMaxSize-1-i] = this.array[this.max_size-1-i];
            }
            this.top_negro = newMaxSize - this.sizePilhaNegro();
            this.max_size = newMaxSize;
            this.array = newArray;
        } 
        this.array[--this.top_negro] = obj;
    } 
    
    public Object popPilhaRubro() {
        if (this.isEmptyPilhaRubro()) {
            throw new EPilhaVazia();
        } 
        if ((this.sizePilhaRubro() + this.sizePilhaNegro()) == this.max_size / 3) {
            int newMaxSize;
            if (this.max_size % 2 == 0) { newMaxSize = this.max_size / 2; }
            else { newMaxSize = (this.max_size - 1) / 2; }
            Object[] newArray = new Object[newMaxSize];
            for (int i = 0; i < this.sizePilhaRubro(); i++) {
                newArray[i] = this.array[i];
            }
            for (int i = 0; i < this.sizePilhaNegro(); i++) {
                newArray[newMaxSize-1-i] = this.array[this.max_size-1-i];
            }
            this.top_negro = newMaxSize - this.sizePilhaNegro();
            this.max_size = newMaxSize;
            this.array = newArray;
        } 
        Object temp = this.array[this.top_rubro];
        this.array[this.top_rubro] = null;
        this.top_rubro--;
        return temp;      
    }

    public Object popPilhaNegro() {
        if (this.isEmptyPilhaNegro()) {
            throw new EPilhaVazia();
        } 
        if ((this.sizePilhaRubro() + this.sizePilhaNegro()) == this.max_size / 3) {
            int newMaxSize;
            if (this.max_size % 2 == 0) { newMaxSize = this.max_size / 2; }
            else { newMaxSize = (this.max_size - 1) / 2; }
            Object[] newArray = new Object[newMaxSize];
            for (int i = 0; i < this.sizePilhaRubro(); i++) {
                newArray[i] = this.array[i];
            }
            for (int i = 0; i < this.sizePilhaNegro(); i++) {
                newArray[newMaxSize-1-i] = this.array[this.max_size-1-i];
            }
            this.top_negro = newMaxSize - this.sizePilhaNegro();
            this.max_size = newMaxSize;
            this.array = newArray;
        } 
        Object temp = this.array[this.top_negro];
        this.array[this.top_negro] = null;
        this.top_negro++;
        return temp;   
    }

    public Object topPilhaRubro() {
        if (this.isEmptyPilhaRubro()) {
            throw new EPilhaVazia();
        }
        return this.array[this.top_rubro];
    }

    public Object topPilhaNegro() {
        if (this.isEmptyPilhaNegro()) {
            throw new EPilhaVazia();
        }
        return this.array[this.top_negro];
    }

    public int sizePilhaRubro() {
        return this.top_rubro + 1;
    }

    public int sizePilhaNegro() {
        return max_size - this.top_negro;
    }

    public boolean isEmptyPilhaRubro() {
        return this.top_rubro == -1;
    }

    public boolean isEmptyPilhaNegro() {
        return this.top_negro == this.max_size;
    }

    public Object[] listArray() {
        return this.array;
    }
}

public class PilhaRubroNegroMain {
    public static void main(String[] args) {
         PilhaRubroNegroArray test = new PilhaRubroNegroArray(90);
         System.out.println("PILHA RUBRO ESTÁ VAZIA? " + test.isEmptyPilhaRubro());
         System.out.println("PILHA NEGRO ESTÁ VAZIA? " + test.isEmptyPilhaNegro());
         System.out.println();
         for (int i = 0; i < 46; i++) {
            test.pushPilhaRubro(i);
            test.pushPilhaNegro(i);
         }
         Object[] list = test.listArray();
         for (Object obj : list) {
            System.out.print(obj + " ");
         }
         for (int i = 0; i < 20; i++) {
            test.popPilhaRubro();
            test.popPilhaNegro();
         }
         test.popPilhaNegro();
         System.out.println();
         System.out.println();
         list = test.listArray();
         for (Object obj : list) {
            System.out.print(obj + " ");
         }
         System.out.println();
         System.out.println();
         System.out.println("TOP PILHA RUBRO: " + test.topPilhaRubro());
         System.out.println("TOP PILHA NEGRO: " + test.topPilhaNegro());
         System.out.println("TAMANHO PILHA RUBRO: " + test.sizePilhaRubro());
         System.out.println("TAMANHO PILHA NEGRO: " + test.sizePilhaNegro());
         System.out.println("PILHA RUBRO ESTÁ VAZIA? " + test.isEmptyPilhaRubro());
         System.out.println("PILHA NEGRO ESTÁ VAZIA? " + test.isEmptyPilhaNegro());
    }
}