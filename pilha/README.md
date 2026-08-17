# EDL Aula 01 - Pilha

## TAD (Tipos abstratos de dados)

É uma abstração de uma estrutura de dados, buscando especificar e classificar essa estrutura conforme:

- Conjunto de dados: Objetos de qualquer natureza
- Operações: Funções/métodos (inserir, coletar, remover, etc)
- Exceções: Casos específicos das operações que gerem comportamentos inesperados

Ou seja, um TAD descreve uma estrutura de dados, enquanto a implementação pode ser feita de diversas formas através da linguagem de programação.

## TAD Pilha

A pilha é uma estrutura que segue a lógica LIFO (Last In First Out), ou seja, o último a entrar na pilha é sempre o primeiro a sair.

O conjunto de dados da pilha são **objetos arbitrários**, ou seja, objetos de qualquer natureza.

As operações são:

- push(object): insere o objeto recebido no topo da pilha
- top(): retorna o objeto do topo da pilha
- pop(): remove o objeto do topo e retorna
- size(): retorna o tamanho da pilha
- isEmpty(): retorna um booleano conforme a pilha estar vazia ou não

A única exceção é se a **pilha estiver vazia** nas operações de top e pop.

### Implementação com array

Uma implementação simples da pilha é com arrays.

A ocupação de espaço na memória é O(n), ou seja, a ocupação será proporcional a quantidade n de elementos no array.

As operações serão O(1), ou seja, cada operação pode ser feita de forma direta, sem percorrer todos os elementos do array.

No entanto, devido à especificação fixa da quantidade máxima de elementos que um array deve ter, ele fica limitado a esse quantidade, ocasionando em uma exceção de pilha cheia.

### Pilha crescente baseada em array 

Ao invés de lançar uma exceção, a melhor abordagem é substituir o array quando encher por um maior.

Para isso, existem duas estratégias: incrementar uma constante sempre que o array encher ou duplicar o tamanho dele sempre que encher.

No entanto, em ambos os casos será necessário utilizar iteração para fazer a realocação dos objetos no novo array.

Para validar qual a melhor forma a longo prazo, isto é, de acordo com o crescimento do número n de elementos inseridos no array, é preciso calcular o tempo de amortização, representado por T(n)/n, ou seja, o tempo em função da quantidade de elementos dividido pelo número de elementos.

#### Amortização da estratégia incremental

Para a comparação, pensa-se em um array de uma posição, inicialmente vazio.

O T(n) é calculado considerando a soma constante da quantidade de elementos n a inserir, uma quantidade k de vezes.

k é calculado pela divisão de n pela constante de incremento c.

Se considerar n = 1000 e c = 100, significa que, para preencher o array definido com todos os elementos, ele deve ser incrementado k=1000/100=10 vezes:

n + c + 2c + 3c + ... + kc => n + c(1 + 2 + 3 + ... + k) => 1 + 2 + 3 + ... + k equivalente à soma de progressão de Gauss (1 + k) * (k/2)

n + c * ((1 + k) * (k/2)) => n + c * (k/2 + k²/2) => n + ck/2 + ck²/2 => n + ck(1 + k)/2 => como c é constante, considera-se o k*k=k², portanto, T(n)=O(n²)

**Amortização => n²/n=O(n)**

#### Amortização da estratégia de duplicação

Para a comparação, pensa-se em um array de uma posição, inicialmente vazio.

Nesse caso, k será log de n na base 2, pois será a quantidade de vezes em que o tamanho da array será duplicado.

Considerando que n=1024 para a quantidade de elementos a serem inseridos, k será 10, isto é, o array será duplicado 10 vezes.

n + 1 + 2 + 4 + 8 + ... + (2 elevado a k) => n + (2 elevado a (k-1)) - 2 => 3n - 2 => T(n)=O(n)

**Amortização => n/n=O(1)**

Diante disso, para inserção de muitos elementos, a estratégia mais eficiente em termos de performance é a da duplicação.
