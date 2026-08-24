class EPilhaVazia extends RuntimeException {
    public EPilhaVazia() {
        super("Pilha Vazia");
    }
}

class PilhaRubroNegro {

    private int max_size;
    private int top_rubro, top_negro;
    private Object[] array;

    public PilhaRubroNegro(int max_size) {
        this.max_size = max_size;
        this.top_rubro = -1;
        this.top_negro = max_size;
        this.array = new Object[this.max_size];
    }

    public void pushPilhaRubro(Object obj) {
        if (this.rubro + 1 == this.negro || this.array.length == this.max_size / 3) {
            int newMaxSize;
            if (this.rubro + 1 == this.negro) { 
                newMaxSize = this.max_size * 2; 
            }
            else { 
                if (this.max_size % 2 == 0) { newMaxSize = this.max_size / 2; }
                else { newMaxSize = (this.max_size - 1) / 2; }
            }
            Object[] newArray = new Object[newMaxSize];
            for (int i = 0; i < this.sizeArrayRubro(); i++) {
                newArray[i] = this.array[i];
            }
            for (int i = 1; i < this.sizeArrayNegro(); i++) {
                newArray[newMaxSize-i] = this.array[this.max_size-i];
            }
            this.top_negro = newMaxSize - (this.sizeArrayNegro() - 1);
            this.max_size = newMaxSize;
            this.array = newArray;
        } 
        this.array[++this.top_rubro] = obj;
    }

    public void pushPilhaNegro(Object obj) {
        if (this.top_rubro + 1 == this.top_negro || this.array.length == this.max_size / 3) {
            int newMaxSize;
            if (this.rubro + 1 == this.negro) { 
                newMaxSize = this.max_size * 2; 
            }
            else { 
                if (this.max_size % 2 == 0) { newMaxSize = this.max_size / 2; }
                else { newMaxSize = (this.max_size - 1) / 2; }
            }
            Object[] newArray = new Object[newMaxSize];
            for (int i = 0; i < this.sizeArrayRubro(); i++) {
                newArray[i] = this.array[i];
            }
            for (int i = 1; i < this.sizeArrayNegro(); i++) {
                newArray[newMaxSize-i] = this.array[this.max_size-i];
            }
            this.top_negro = newMaxSize - (this.sizeArrayNegro() - 1);
            this.max_size = newMaxSize;
            this.array = newArray;
        } 
        this.array[--this.top_negro] = obj;
    } 
    
    public Object popPilhaRubro() {
        if (this.isEmptyPilhaRubro()) {
            throw new EPilhaVazia();
        } 
        if (this.array.length == this.max_size / 3) {
            int newMaxSize;
            if (this.max_size % 2 == 0) { newMaxSize = this.max_size / 2; }
            else { newMaxSize = (this.max_size - 1) / 2; }
            Object[] newArray = new Object[newMaxSize];
            for (int i = 0; i < this.sizeArrayRubro(); i++) {
                newArray[i] = this.array[i];
            }
            for (int i = 1; i < this.sizeArrayNegro(); i++) {
                newArray[newMaxSize-i] = this.array[this.max_size-i];
            }
            this.top_negro = newMaxSize - (this.sizeArrayNegro() - 1);
            this.max_size = newMaxSize;
            this.array = newArray;
        } 
        return this.array[this.top_rubro--];      
    }

    public Object popPilhaNegro() {
        if (this.isEmptyPilhaNegro()) {
            throw new EPilhaVazia();
        } 
        if (this.array.length == this.max_size / 3) {
            int newMaxSize;
            if (this.max_size % 2 == 0) { newMaxSize = this.max_size / 2; }
            else { newMaxSize = (this.max_size - 1) / 2; }
            Object[] newArray = new Object[newMaxSize];
            for (int i = 0; i < this.sizeArrayRubro(); i++) {
                newArray[i] = this.array[i];
            }
            for (int i = 1; i < this.sizeArrayNegro(); i++) {
                newArray[newMaxSize-i] = this.array[this.max_size-i];
            }
            this.top_negro = newMaxSize - (this.sizeArrayNegro() - 1);
            this.max_size = newMaxSize;
            this.array = newArray;
        } 
        return this.array[this.top_negro++];      
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
}