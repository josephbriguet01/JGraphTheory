Copyright (C) BRIGUET Systems, Inc - All Rights Reserved
# [JGraphTheory](https://github.com/josephbriguet01/JGraphTheory "Accès au projet Git JGraphTheory")

### Introduction

Cette classe permet d'utiliser l'algorithme de Dijkstra pour calculer dans un graphe le plus court chemin entre deux `sommets` en utilisants des `arrêtes` ou des `arcs`. On peut également créer des matrices d'adjacences pour calculer le `prédécesseur` d'un sommet ou ses `successeurs`.

> La différence entre un arc et une arrête réside dans sa direction. On peut aller dans les deux sens sur une arrête alors qu'un arc n'a qu'une seule direction.

Un sommet est représenté par la classe `Node`
Une arrête est représentée par la classe `Edge`
Un arc est représenté par la classe `Curve`
Une matrice d'adjacence est représentée par la classe `Matrix`
Une liste d'adjacence est représentée par la classe `List`

> La classe `Dijkstra` permet avec l'aide d'une `Matrix` ou d'une `List` de calculer le plus court chemin entre un `Node` et un autre.

### Cas d'utilisation

```java
public static void main(String[] args){
    
    // Créations des sommets du Graphe. Chaque sommet doit posséder un nom unique
    Node a = new Node("A");
    Node b = new Node("B");
    Node c = new Node("C");
    Node d = new Node("D");
    Node e = new Node("E");
    Node f = new Node("F");
    Node g = new Node("G");
    
    // Création des arcs ( = arrêtes unidirectionnelles). Ils ont un nom unique et peuvent posséder un poid. Ce qui est le cas ici. Le sens de l'arc va de a vers b dans l'arc e1
    Edge e1 = new Curve("A -> B", a, b, 4);
    Edge e2 = new Curve("A -> C", a, c, 2);
    Edge e3 = new Curve("B -> E", b, e, 9);
    Edge e4 = new Curve("B -> D", b, d, 3);
    Edge e5 = new Curve("C -> F", c, f, 8);
    Edge e6 = new Curve("C -> D", c, d, 6);
    Edge e7 = new Curve("D -> E", d, e, 5);
    Edge e8 = new Curve("D -> F", d, f, 7);
    Edge e9 = new Curve("E -> G", e, g, 1);
    Edge e10 = new Curve("F -> G", f, g, 6);
    
    // On crée une liste de sommets
    Node[] ns = {a, b, c, d, e, f, g};
    
    // On crée une liste d'arcs
    Edge[] es = {e1, e2, e3, e4, e5, e6, e7, e8, e9, e10};
    
    // On crée une matrice d'adjacence
    Matrix m = new Matrix(ns, es);
    
    // On crée un objet Dijkstra qui calcul le plus court chemin entre le sommet A et le sommet G
    Dijkstra di = m.getDijkstra(a, g);
    
    // On affiche tous les sommets A et G compris qui correspond au chemin le plus court
    System.out.println(Arrays.toString(di.getPath()));
    
    // Affiche la longueur du chemin à partir des poids des arcs
    System.out.println(di.getWeight());
}
```

Il est possible de créer une liste d'adjacence de cette manière:
```java
List l = new List(ns, es);
```

On peut également calculer le chemin le plus court à partir de la liste:
```java
Dijkstra di = l.getDijkstra(a, g);
```

On peut récupérer les sommets `prédécesseurs` d'un sommet avec la matrice ou la liste d'adjacence:
```java
Node[] predecessors = m.getPredecessors(c);
```
> Ce qui va nous donner "A"

On peut récupérer les sommets `successeurs` d'un sommet avec la matrice ou la liste d'adjacence:
```java
Node[] successors = l.getSuccessors(c);
```
> Ce qui va nous donner "D" et "F"

On peut connaitre le nombre de degrés entrant avec la matrice ou la liste d'adjacence:
```java
int n = c.nbInDegrees(c);
```
> Ce qui va nous donner "1"

On peut connaitre le nombre de degrés sortant avec la matrice ou la liste d'adjacence:
```java
int n = l.nbOutDegrees(c);
```
> Ce qui va nous donner "2"

On peut vérifier qu'un chemin existe entre deux sommets *(en prenant en compte le sens des arcs)*:
```java
boolean b1 = m.pathExistBetween(g, c);
boolean b2 = m.pathExistBetween(c, g);
```
> Ce qui va nous donner "false" pour b1 et "true" pour b2

On peut récupérer une liste d'adjacence à partir d'une matrice d'adjacence:
```java
List l = m.getList();
```

L'inverse est vrai également:
```java
Matrix m = l.getMatrix();
```

## Accès au projet GitHub => [ici](https://github.com/josephbriguet01/JGraphTheory "Accès au projet Git JGraphTheory")
