# Aula 03

## TAD Vetor

Representação abstrata do conceito de array, acessando e modificando um array com base na colocação (rank).

O rank se diferencia do índice pois o rank indica a sequência lógica do array, desconsiderando as posições vazias.

Na prática, isso significa que os ranks válidos seguem a lógica sequência, de modo que ele não pode ser maior que a quantidade de elementos do array.

Por exemplo, se o array possui cinco elementos, os ranks válidos vão de 0 a 4, além de um 5, como próximo da sequência.

O conjunto de dados são objetos arbitrários, isto é, de qualquer natureza.

As operações são:

- **elementAtRank(int r)**: Retorna o elemento no índice r
- **replaceAtRank(int r, object o)**: Substitui o elemento em r por o e retorna o antigo elemento
- **insertAtRank(int r, object o)**: Insere o em r
- **removeAtRank(int r)**: Remove o elemento em r e retorna-o
- **size()**: Retorna a quantidade de elementos
- **isEmpty()**: Retorna um booleano que indica se o array está vazio