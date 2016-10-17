# DynamicProgrammingLib
Biblioteca com vários algorítimos feitos utilizando programação dinâmica criada como parte da nota da segunda unidade da disciplina Análise e Projeto de Algorítimos, no período 2016.2 da Universidade Federal da Paraíba.

### Problemas Resolvidos:

* Aellison Cassimiro Teixeira dos Santos - `11311469`
  * [Graph Coloring](../master/README.md#graph-coloring)
  * [Matrix Multiplication](../master/README.md#matrix-multiplication)
  * [Minimum Edit Distance](../master/README.md#minimum-edit-distance)
  * [Prim's Minimum Spanning Tree](../master/README.md#prims-minimum-spanning-tree)
  * [Subset Sum](../master/README.md#subset-sum)

* Diego Filipe Sousa Lima - `11408104`
  * [Box Stacking Problem](../master/README.md#box-stacking)
  * Dijkstra’s shortest path algorithm
  * [Floyd Warshall Algorithm](../master/README.md#floyd-warshall)
  * Kruskal’s Minimum Spanning Tree Algorithm
  * [Longest Common Subsequence](../master/README.md#longest-common-subsequence)
  
* Marcelo Aguiar Rodrigues - `11311862`
  * [Activity Selection](../master/README.md#activity-selection)
  * [Coin Change](../master/README.md#coin-change)
  * [Fractional Knapsack](../master/README.md#fractional-knap-sack)
  * [Knapsack](../master/README.md#knap-sack)
  * [Word Break](../master/README.md#word-break)


## Instalação
1. Faça o download dos arquivos
2. Adicione ao seu projeto

## Utilização
No seu projeto: `import lib/DynamicAlgorithms;`

## Algorítimos

### Activity Selection
Para utilizar esse algorítimo: `import lib/Activity;`

A partir de uma `List<Activity>` retora uma lista com o índice das atividades que podem ser realizadas por uma única pessoa sendo que apenas uma atividade pode ser realizada por vez.
Para funcionar corretamente a lista de atividades deve estar ordenada pelo tempo de finalização.

A função vai ler a lista de atividades e adicionar a respostas que o tempo de início seja maior ou igual ao tempo de finalizacão da última atividade adicionada.

Complexidade: `O(n)`

Utilização:
```java
List<Activity> activities = new ArrayList<Activity>() {{
    add(new Activity(startTime, finishTime));
    .
    .
    .
}};

List<Integer> list = DynamicAlgorithms.activitySelection(activities);
```

### Box Stacking

O usuário terá um conjunto de "n" caixas 3d, e ele deseja saber qual a maior altura possivel de ser obtida empilhando as caixas. Há uma restrição de que a base 2d da caixa de baixo tem que ser sempre estritamente maior que a da caixa de cima.

O programa recebe um array de caixas, onde cada uma delas pode ser rotacionada para ser empilhada em cima de si mesmo como se fosse outra caixa, desde que atenda a restrição. Retornará um número que é a máxima altura da pilha de caixas.

Complexidade: `O(n²)`

Utilização:
```java
int[] input = { ... }

int output = DynamicAlgorithms.maxHeight(input);
```

### Coin Change
A partir de um array com os valores das moedas e um valor para o troco essa função retorna a quantidade de arranjos possíveis das moedas para chegar no troco desejado.

Complexidade: `O(mn)`

Onde `m` o tamanho do array de moedas (quantidade de moedas) e `n` o tamanho do troco.

Utilização:
```java
int[] coins = { ... };
int change = NUMBER;

int output = DynamicAlgorithms.coinChange(coins, change);
```
#### Floyd Warshall

Dado um grafo orientado com "n" nós, não negativo, esse algoritmo busca expor os caminhos mais curtos entre todos os pares de nós do grafo.

O programa tem como entrada as informações sobre a quantidade de nós do grafo e informações como: nó de origem, nó de destino e o tamanho do caminho entre esses dois nós respectivamente. Retornará um grafo resultante que contem as menores distâncias entre cada dois nós.

Complexidade: `O(n³)`

Utilização:
```java
Graph fw = new Graph( ... );
fw.addEdge( ... );

double[][] result = DynamicAlgorithms.floydWarshall(fw);
```

### Fractional Knap Sack
Para utilizar esse algorítimo: `import lib/Item;`

A partir de uma `List<Item>` e o tamanho de uma mochila a função retorna o valor máximo que pode ser alcançado ao colocar os items dentro dessa mochila. Nesse problema os items não são discretos e podem ser fracionados. (ex.: grãos)

A primeira parte da execução dessa função é ordenar os items por valor/peso. Após ordenar faz-se uma varredura simples na lista adicionando o máximo possível do item mais caro, caso sobre espaço na mochila, coloca-se o segundo item mais caro e isso se repete até encher a mochila.

Complexidade: `O(n)`

Ordenação dos items: Depende do algoritimo de ordenação utilizado. Podendo ser uma ordenação linear.

Utilização:
```java
int sizeOfKnapsack = NUMBER;

List<Item> items = new ArrayList<Item>() {{
    add(new Item(itemValue, itemWeight));
    .
    .
    .
}};

double output = DynamicAlgorithms.fractionalKnapsack(items, sizeOfKnapsack);
```

### Graph Coloring

Com um grafo de v vértice e um conjunto de x cores, precisamos saber se todos os nós podem ser pintados usando as cores dadas de uma forma que dois nós adjacentes não tenham a mesma cor.

A função receberá uma matriz de adjacência representando o grafo, retornando verdadeiro caso seja possível pintar o grafo com as cores dadas ou falso caso contrário.

Complexidade: `O(mn)`

Utilização:
```java
int vert = x;
int numCores = y;
int[][] grafo = new int[][]{{...}};

boolean possivel = DynamicAlgorithms.colorGraph(grafo, numCor, vert)
```

### Knap Sack
Para utilizar esse algorítimo: `import lib/Item;`

A partir de uma `List<Item>` e o tamanho de uma mochila a função retorna o valor máximo que pode ser alcançado ao colocar os items dentro dessa mochila. Nesse problema os items não podem ser fracionados.

Complexidade: `O(mn)`

Utilização:
```java
int sizeOfKnapsack = NUMBER;

List<Item> items = new ArrayList<Item>() {{
    add(new Item(itemValue, itemWeight));
    .
    .
    .
}};

int output = DynamicAlgorithms.knapSack(binaryKnapsack, sizeOfKnapsack);
```

### Longest Common Subsequence

Dado duas strings "m" e "n" o usuário deseja obter o tamanho da maior subsequência comum dentre os seus elementos. Para que os elementos pertençam a maior subsequência, eles não precisam estar adjacentes na sua string de origem, precisam apenas estar um apos o outro na mesma ordem nas duas strings.

O programa recebe duas strings que são convertidas em dois arrays de caracteres, os quais são comparados de diversas maneiras. Retornará o tamanho da maior subsequência comum entre as duas strings.

Complexidade: `O(mn)`

Utilização:
```java
String first_string = " ... "
String second_string = " ... "

int output = DynamicAlgorithms.lcs(first_string, second_string);
```

### Matrix Multiplication

Com um conjunto de n matrizes, esta função retornará a quantidade de multiplicações feitas em um sequência ótima de operações no dado conjunto na ordem apresentada.

A função receberá um array onde os n-1 primeiros valores do array serão o numero de linhas das matrizes, e o último valor será o numero de colunas da última matriz. O retorno é o número de operações feitas numa multiplicação otimizada.

Complexidade: `O(mn)`

Utilização:
```java
int[] array = { ... };
		
int output = DynamicAlgorithms.matrixMultiplication(array);
```

### Minimum Edit Distance

O usuário terá duas strings e necessita saber quantas operações de remoção, substituição, e/ou inserção serão feitas para transformar a primeira string na segunda.

O programa recebe duas strings e retorna o número mínimo de operações (a distância) para transformar a primeira string na segunda. 

Complexidade: `O(mn)`

Utilização:
```java
String a = " ... ";
String b = " ... ";

int output = DynamicAlgorithms.minEdit(a, b));
```

### Prim's Minimum Spanning Tree

Tendo um grafo ponderado, pode-se convertê-lo para um array bidimensional. Este array será tratado como uma tabela de adjacência contendo os pesos das arestas, e com isso será possível montar a árvore desejada.

Com o input pronto, a função analisará o grafo partindo da raiz (índice 0) e retornará uma string contendo as arestas da árvore no formato "i-j;i-x;x-y;" onde cada letra será o valor da vértice que representa um nó.

Complexidade: `O(n²)`

Utilização:
```java
int vert = x;
int[][] grafo = new int[][]{{...}};

int[] arvore = DynamicAlgorithms.PrimMinTree(grafo, vert);
```

### Subset Sum

O usuário terá um número x e um conjunto de inteiros I e deseja saber se o x pode ser formado por uma soma de um subconjunto de valores de V.

O programa recebe um número e um array como entrada, o número será a soma e o array o conjunto no qual os valores que possivelmente podem somar a x estão contidos. Retornará verdadeiro se for encontrado um subconjunto cuja a soma seja x, falso caso contrário. 

Complexidade: `O(mn)`

Utilização:
```java
int sum = ... ;
int []set = { ... };

boolean output = DynamicAlgorithms.SubsetSum(sum, set);
```

### Word Break
A partir de um dicionário de palavras e uma string retorna `true` ou `false` caso a string possa ser dividida em uma sequencia de strings presente no dicionário.

Utilização:
```java
List<String> dictionary = new ArrayList<String>() {{
            add("i");
            add("like");
            add("ice");
            add("cream");
        }};

boolean doILikeIceCream = DynamicAlgorithms.wordBreak(dictionary, "ilikeicecream")
```
