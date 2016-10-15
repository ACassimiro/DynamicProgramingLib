<snippet>
  <content>
# DynamicProgrammingLib
Biblioteca com vários algorítimos feitos utilizando programação dinâmica criada como parte da nota da segunda unidade da disciplina Análise e Projeto de Algorítimos, no período 2016.2 da Universidade Federal da Paraíba.

## Instalação
1. Faça o download dos arquivos
2. Adicione ao seu projeto

## Utilização
No seu projeto: `import lib/DynamicAlgorithms;`

## Algorítimos
#### Activity Selection
Para utilizar esse algorítimo: `import lib/Activity;`

A partir de uma `List<Activity>` retora uma lista com o índice das atividades que podem ser realizadas por uma única pessoa sendo que apenas uma atividade pode ser realizada por vez.
Para funcionar corretamente a lista de atividades deve estar ordenada pelo tempo de finalização.

A função vai ler a lista de atividades e adicionar a respostas que o tempo de início seja maior ou igual ao tempo de finalizacão da última atividade adicionada.

Complexidade: O(n)

Utilização:
```java
List<Activity> activities = new ArrayList<Activity>() {{
    add(new Activity(startTime, finishTime));
    add(new Activity(startTime, finishTime));
    add(new Activity(startTime, finishTime));
}};

List<Integer> list = DynamicAlgorithms.activitySelection(activities);
```

#### Coin Change
A partir de um array com os valores das moedas e um valor para o troco essa função retorna a quantidade de arranjos possíveis das moedas para chegar no troco desejado.

Complexidade: O(mn)

Utilização:
```java
int[] coins = {2, 5, 3, 6};
int change = 10;

int output = DynamicAlgorithms.coinChange(coins, change);
```

#### Fractional Knap Sack
Para utilizar esse algorítimo: `import lib/Item;`

A partir de uma `List<Item>` e o tamanho de uma mochila a função retorna o valor máximo que pode ser alcançado ao colocar os items dentro dessa mochila. Nesse problema os items não são discretos e podem ser fracionados. (ex.: grãos)

A primeira parte da execução dessa função é ordenar os items por valor/peso.
Após ordenar faz-se uma varredura simples na lista adicionando o máximo possível do item mais caro, caso sobre espaço na mochila, coloca-se o segundo item mais caro e isso se repete até encher a mochila.

Complexidade: O(n)
Ordenação dos items: Depende do algoritimo de ordenação utilizado. Podendo ser uma ordenação linear.

Utilização:
```java
int sizeOfKnapsack = 50;

List<Item> items = new ArrayList<Item>() {{
    add(new Item(60, 10));
    add(new Item(120, 30));
    add(new Item(100, 20));
}};


double output = DynamicAlgorithms.fractionalKnapsack(items, sizeOfKnapsack);
```

#### Knap Sack
Para utilizar esse algorítimo: `import lib/Item;`

A partir de uma `List<Item>` e o tamanho de uma mochila a função retorna o valor máximo que pode ser alcançado ao colocar os items dentro dessa mochila. Nesse problema os items não podem ser fracionados.

Complexidade: O(mn)

Utilização:
```java
int sizeOfKnapsack = 50;

List<Item> binaryKnapsack = new ArrayList<Item>() {{
    add(new Item(60, 10));
    add(new Item(100, 20));
    add(new Item(120, 30));
}};

int output = DynamicAlgorithms.knapSack(binaryKnapsack, sizeOfKnapsack);
```

#### Word Break
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

  </content>
</snippet>
