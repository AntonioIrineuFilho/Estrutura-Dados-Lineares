# Aula 03

## TAD Lista

Estabelece uma relação de antes e depois entre posições no array/lista.

O conjunto dedados são objetos arbitrários, isto é, objetos de qualquer natureza.

O conjunto de operações são:

- **isFirst(n)**: Verifica se determinada posição é a primeira da lista
- **isLast(n)**: Verifica se determinada posição é a última da lista
- **first()**: Retorna o primeiro objeto da lista
- **last()**: Retorna o último objeto da lista
- **before(p)**: Retorna o elemento da posição anterior à posição p
- **after(p)**: Retorna o elemento da posição seguinte à posição p
- **replaceElement(n, o)**: Substitui o elemento da posição n pelo objeto o
- **swapElements(n, q)**: Troca o elemento da posição n com o elemento da posição q
- **insertBefore(n, o)**: Insere o objeto na posição anterior à posição n e retorna a nova posição
- **insertAfter(n, o)**: Insere o objeto na posição seguinte à posição n e retorna a nova posição
- **insertFirst(o)**: Insere o objeto o na primeira posição e retorna a posição
- **insertLast(o)**: Insere o objeto o na última posição e retorna a posição
- **remove(n)**: Remove a posição n e retorna o elemento
- **size()**: Retorna a quantidade de elementos na lista
- **isEmpty()**: Verifica se a lista está vazia

### Array x Lista Duplamente Encadeada

Numa implementação baseada em array, o conceito de posição se traduz em índice.

Já numa implementação baseada em lista duplamente encadeada, se traduz em nós.