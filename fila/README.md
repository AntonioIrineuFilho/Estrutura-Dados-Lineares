# Aula 02

## TAD Fila

Fila é uma estrutura que segue a lógica FIFO (First In First Out), ou seja, o primeiro a entrar na fila é o primeiro a sair.

Tal qual a pilha, o conjunto de dados é composto por **objetos arbitrários**, isto é, de qualquer natureza.

As operações são tal qual a pilha, no entanto, ao invés de retornar o último elemento retorna-se o primeiro e ao invés de remover o último remove-se o primeiro:

- enqueue(object): enfilera o objeto no final da fila
- dequeue(): remove o primeiro e retorna-o
- first(): retorna o primeiro
- size(): retorna o tamanho da fila
- isEmpty(): retorna booleano correspondente se a fila estiver vazia ou não 

Assim como a pilha, a única exceção é retornar e/ou remover se a fila estiver vazia.

## Implementação com Array

Assim como na pilha, se o array encher, ao invés de lançar uma exceção de fila cheia, copia-se os elementos para um array maior, seguindo a premissa incrmental ou de duplicação.

### Lógica da implementação com Array

Diferente da pilha, é necessário mais de um controlador de índice, um para o início da fila e outro para o final.

Para realizar o dequeue, o start deve ser incrementado uma posição, contabilizando o array a partir dele e ignorando os elementos anteriores.

Enquanto no enqueue, basta incrementar uma posição no end a atribuir o objeto.

### Array circular

Ao seguir essa lógica de índices, ocorre uma situação: o end eventualmente chega na posição limite do array, mas ele pode não estar cheio, pois o start pode ter sido incrementado múltiplas vezes no dequeue, e as posições antes do start podem estar livres.

Diante disso, não faz sentido aumentar o buffer do array, pois ainda há espaços de memória disponíveis para utilização antes do start.

Por isso a ideia de array circular, pois o índice do final sairia do topo e retornaria para a primeira posição, até chegar em start-1.

A ideia de start-1 nasce pois a forma de identifcar se um array está vazio é se o start é igual end, indicando que não houve crescimento nem diminuição, então deve haver uma posição vazia entre o end e o start, pois se o end preencher essa posição vazia naturalmente ele teria que ser incrementado e ficaria igual ao start, prejudicando o isEmpty.

