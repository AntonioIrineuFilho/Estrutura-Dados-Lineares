# Árvore

Abstração de uma estrutura hierárquica, sendo representado por nós interligados por arestas.

## Terminologias

- **Root**: Primeiro nó de uma árvore, não possuindo pai
- **Internal**: Nó que possuirá pelo menos um filho
- **External**: Também chamado de nó folha, não possui filhos, ou seja, estão nas pontas da árvore
- **Ancestral**: Nó que seja pai, avô, bisavô de outro nó
- **Descendant**: Nó que seja filho, neto, bisneto de outro nó
- **Profundidade**: Conceito voltado para um nó, definindo a distância desse nó até a raiz, com o raiz incluso
- **Altura**: Diz respeito à maior profundiade da árvore, ou seja, o nó que possuir maior profundidade também representará a altura da árvore

## TAD Árvore

Representação genérica do conceito de árvore.

### Métodos

- **int size()**: Retorna o total de nós da árvore
- **int height()**: Retorna a altura da árvore
- **boolean isEmpty()**: Retorna se a árvore possui pelo menos um nó ou não 
- **Iterator elements()**: Retorna um objeto iterator com os elementos dos nós
- **Iterator nodes()**: Retorna um objeto iterator com os nós
- **Iterator children(Node node)**: Retorna um objeto iterator para os filhos de um nó
- **Node root()**: Retorna o nó raiz
- **Node parent(Node node)**: Retorna o nó pai do nó de referência
- **boolean isInternal(Node node)**: Retorna se o nó é interno ou não
- **boolean isExternal(Node node)**: Retorna se o nó é folha ou não
- **booelan isRoot(Node node)**: Retorna se o nó é raiz
- **int depth(Node node)**: Retorna a profundidade de um nó
- **Object replace(Node node, Object o)**: Substitui o elemento e retorna o antigo no nó de referência

## Técnicas de ordenação dos nós

Em uma árvore genérica, os nós podem ser ordenados sistematicamente em pré-ordem e pós-ordem.

### Travessia pré-ordem

Visita cada nó antes de seus descentes, ou seja, primeiro há a aplicação da ordem no pai e em seguida nos seus descendentes (filhos e netos).

O algoritmo pode ser representado recursivamente, por exemplo:

```java
preOrder(node)
    // contabiliza o nó
    visit(node)
    // pega cada filho do nó e repete a rotina
    // visitando filho -> todos os filhos desse filho -> próximo filho -> todos os filhos dele
    for each children(c) of node
        preOrder(c)
```

### Travessia pós-ordem 

Visita todos os filhos de um nó antes de visitá-lo.

```java
postOrder(node)
    // visita todos os filhos até o folha
    for each children(c) of node
        postOrder(c)
    // contabiliza o nó a partir dos folhas até chegar no pai
    visit(node)
```

### Implementação

A classe nó deverá possuir um atributo para o elemento, outro para referenciar o pai e outro para uma estrutura que armazene os filhos, como um vetor, lista, sequência, etc.

## Árvore Binária

A árvore binária se caracteriza por cada nó possuir no máximo dois filhos.

Se possuir apenas zero ou dois filhos, a árvore binária se caracteriza como uma árvore binária própria.

Diferentemente da estrutura genérica, que armazena os filhos em uma estrutura como um array, a árvore binária possui um atributo para representar o filho da esquerda e um para representar o filho da direita.

### Aplicações

Uma árvore binária se destaca especialmente para armazenar expressões aritméticas, com os pais sendo os operadores e os filhos sendo os operandos, por exemplo.

Além disso, também pode funcionar como uma estrutura de decisão, com os filhos sendo caminhos de sim ou não, por exemplo.

Também se destaca no processo de busca.

### TAD Árvore Binária

Possui métodos adicionais em relação à árvore genérica:

- **Node leftChild(Node node)**: Retorna o filho esquerdo de um nó
- **Node rightChild(Node Node)**: Retorna o filho direito de um nó
- **Node hasLeft(Node node)**: Retorna se há ou não filho esquerdo
- **Node hasRight(Node node)**: Retorna se há ou não filho direito

### Travessia em ordem

Ordenação que se baseia na visita do filho esquerdo antes do próprio nó, e no próprio nó antes do filho direito.

```java
inOrder(node)
    if (isInternal(node))
        // entra no filho esquerdo se o nó for interno
        inOrder(leftChild(node))
    // quando chega no folha, visita
    visit(node)
    // após visitar o folha, volta para a recursão do pai, visita-o e entra na rota do filho direito
    if (isInternal(node))
        inOrder(rightChild(node))
```

**Insight: basta mudar a posição da rotina de visita para ter a travessia pré-ordem e pós-ordem**

### Implementação

A classe nó deve possuir um atributo para o elemento, uma referência para o pai e referências para o filho esquerdo e o filho direito.

É possível representar com array utilizando a seguinte lógica: o filho esquerdo estará no dobro do índice do pai e o da direita no dobro do indíce mais um.

Diante disso, o array deve começar a ser preenchido a partir do índice 1.