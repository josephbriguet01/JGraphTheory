/*
 * Copyright (C) BRIGUET Systems, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by Briguet, Juin 2018
 */
package graph.adjacent;



import graph.util.Dijkstra;
import graph.form.Curve;
import graph.form.Edge;
import graph.form.Node;



/**
 * Cette classe représente un Graphe
 * @author Briguet
 * @version 1.0
 */
public abstract class GraphTheory {
    
    
    
    private static int cptInt = 0;
    private final int id;
    /**
     * Correspond à la liste de sommets
     */
    Node[] listNode;
    /**
     * Correspond à la liste d'arrêtes
     */
    Edge[] listEdge;

    
    
    /**
     * Crée un graphe
     * @param listNode Correspond à une liste de sommets
     * @param listEdge Correspond à une liste d'arrêtes et/ou d'arcs
     */
    public GraphTheory(Node[] listNode, Edge[] listEdge) {
        this.listNode = listNode;
        this.listEdge = listEdge;
        this.id = cptInt++;
    }

    
    
//METHODE PUBLICS
    /**
     * Renvoie la liste des sommets
     * @return Retourne la liste des sommets
     */
    public Node[] getListNode() {
        return listNode;
    }

    /**
     * Modifie la liste des sommets
     * @param listNode Correspond à la nouvelle liste des sommets
     */
    public void setListNode(Node[] listNode) {
        this.listNode = listNode;
    }

    /**
     * Renvoie la liste des arrêtes
     * @return Retourne la liste des arrêtes
     */
    public Edge[] getListEdge() {
        return listEdge;
    }

    /**
     * Modifie la liste des arrêtes
     * @param listEdge Correspond à la nouvelle liste des arrêtes
     */
    public void setListEdge(Edge[] listEdge) {
        this.listEdge = listEdge;
    }
    
    /**
     * Renvoie la liste des sommets prédécesseurs du sommet n
     * @param n Correspond au graphe qui va permettre le calcul
     * @return Retourne la liste des sommets successeurs
     */
    public abstract Node[] getPredecessors(Node n);
    
    /**
     * Renvoie la liste des sommets prédécesseurs de ce sommet
     * @param n Correspond au graphe qui va permettre le calcul
     * @return Retourne la liste des sommets prédécesseurs
     */
    public abstract Node[] getSuccessors(Node n);
    
    /**
     * Renvoie le nombre de degrés entrant du sommet n
     * @param n Correspond au sommet dont on veut connaitre le nombre de degrés
     * @return Retourne le nombre de degrées entrant
     */
    public abstract int nbInDegrees(Node n);
    
    /**
     * Renvoie le nombre de degrés sortant du sommet n
     * @param n Correspond au sommet dont on veut connaitre le nombre de degrés
     * @return Retourne le nombre de degrées sortant
     */
    public abstract int nbOutDegrees(Node n);
    
    /**
     * Renvoie s'il existe un chemin entre le sommet start et le sommet end
     * @param start Correspond au sommet de départ
     * @param end Correspond au sommet d'arrivé
     * @return Retourne true s'il existe un chemin, sinon false
     */
    public boolean pathExistBetween(Node start, Node end){
        return (getDijkstra(start, end).getPath() != null);
    }
    
    /**
     * Renvoie un objet Dijkstra
     * @param start Correspond au sommet de départ
     * @param end Correspond au sommet d'arrivé
     * @return Retourne un objet Dijkstra
     */
    public Dijkstra getDijkstra(Node start, Node end){
        int[][] mat = generateMatrix(listNode, listEdge);
        return new DijkstraObject(mat, indexOf(listNode, start), indexOf(listNode, end));
    }

    /**
     * Renvoie le résultat de la méthode hashCode()
     * @return Retourne le résultat de la méthode hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.id;
        return hash;
    }

    /**
     * Renvoie le résultat de la méthode equals()
     * @param obj Correspond à l'objet à comparer
     * @return Retourne le résultat de la méthode equals()
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GraphTheory other = (GraphTheory) obj;
        return this.id == other.id;
    }

    /**
     * Renvoie la représentation en chaîne de caractère de ce graphe
     * @return Retourne la représentation en chaîne de caractère de ce graphe
     */
    @Override
    public String toString() {
        return "GraphTheory{" + "\n\tlistNode=" + java.util.Arrays.toString(listNode) + ",\n\tlistEdge=" + java.util.Arrays.toString(listEdge) + "\n}";
    }
    
    
    
//METHODE PRIVATES
    /**
     * Génère une matrice d'adjacence d'entier contenant les poids des Segments
     * @param listNode Correpsond à la liste des Sommets
     * @param listEdge Correspond à la liste des Segments
     * @return Retourne une matice de poids
     */
    private int[][] generateMatrix(Node[] listNode, Edge[] listEdge){
        int[][] mat = new int[listNode.length][listNode.length];
        
        for(int i=0;i<listNode.length;i++){
            Node predecessor = listNode[i];
            for(int j=0;j<listNode.length;j++){
                Node successor = listNode[j];
                mat[i][j] = -1;
                for (Edge e : listEdge) {
                    if(e instanceof Curve){
                        Curve c = (Curve) e;
                        if(c.getNodeSource().equals(predecessor) && c.getNodeTarget().equals(successor)){
                            mat[i][j] = c.getWeigth();
                        }
                    }else{
                        if((e.getNode1().equals(predecessor) && e.getNode2().equals(successor)) || (e.getNode1().equals(successor) && e.getNode2().equals(predecessor))){
                            mat[i][j] = e.getWeigth();
                            mat[j][i] = e.getWeigth();
                        }
                    }
                }
            }
        }
        return mat;
    }
    
    /**
     * Renvoie l'index du Sommet dans le tableau de Sommets
     * @param nodes Correspond au tableau de Sommets
     * @param node Correspond au Sommet à rechercher
     * @return Retourne l'index ou -1 s'il n'a pas été trouvé
     */
    private int indexOf(Node[] nodes, Node node){
        for(int i=0;i<nodes.length;i++){
            if(nodes[i].equals(node)) return i;
        }
        return -1;
    }
    
    
    
//CLASS
    /**
     * Implementation de l'algorithme de Dijkstra.
     * @version 1.0
     */
    private class DijkstraObject extends Dijkstra{

        private int[][]     matrice;
        private int[]       distanceFromStart;
        private int[]       precedences;
        private boolean[]   activesNodes;
        private int         dim;

        private int[] path = null;
        private int weight = -1;



    //CONSTRUCTOR
        /**
         * Crée un objet Dijkstra et calcul de suite le chemin le plus court
         * @param matrice Correspond à la matrice d'adjacence (lorsqu'il n'y a pas d'arc entre un prédécesseur et un successeur, mettre -1 en poid)
         * @param start Correspond au sommet de départ
         * @param end Correspond au sommet d'arrivé
         */
        public DijkstraObject(int[][] matrice, int start, int end) {
            java.util.List<Integer> list = getPath(matrice, start, end);
            if(list!=null){
                if(list.size()>=3){
                    weight = list.get(list.size()-1);
                    path = new int[list.size()-1];
                    for(int i=0;i<list.size()-1;i++){
                        path[i] = list.get(i);
                    }
                }
            }
        }



    //METHODES PUBLICS
        /**
         * Renvoie la liste des sommets à parcourir
         * @return Retourne une liste de sommet
         */
        @Override
        public Node[] getPath() {
            if(path == null) return null;
            Node[] ns = new Node[path.length];
            for(int i=0;i<path.length;i++){
                int index = path[i];
                ns[i] = listNode[index];
            }
            return ns;
        }

        /**
         * Renvoie le poid minimum
         * @return Retourne le poid minimum
         */
        @Override
        public int getWeight() {
            return weight;
        }  



    //METHODES PRIVATES
        private void activeAdjacents(final int node) {
            int distanceTo;
            for (int to = 0; to < this.dim; to++)
                if (this.isAdjacent(node, to) && (distanceTo = this.distanceFromStart[node] + this.matrice[node][to]) < this.distanceFromStart[to])
                    this.activeNode(node, to, distanceTo);
        }

        private void activeNode(final int from, final int node, final int distance) {
            this.distanceFromStart[node] = distance;
            this.precedences[node]      = from;
            this.activesNodes[node]     = true;
        }

        private java.util.List<Integer> buildPath(final int end) {
            @SuppressWarnings("LocalVariableHidesMemberVariable")
            final java.util.List<Integer> path = new java.util.ArrayList<>();
            path.add(end);

            // utilisation d'une boucle do-while pour conserver le point de depart
            // et d'arrivee dans la liste même lorsque le point de depart correspond
            // au point d'arrivee
            int position = end;
            do {
                path.add(0, this.precedences[position]);
                position = path.get(0);
            } while (this.distanceFromStart[position] != 0);
            if(!path.isEmpty()){
                int compteur = 0;
                for(int i=0;i<path.size();i++){
                    try{
                        compteur += matrice[path.get(i)][path.get(i+1)];
                    }catch(java.lang.IndexOutOfBoundsException e){}
                }
                path.add(compteur);
            }
            return path;
        }

        private java.util.List<Integer> getPath(final int[][] graph, final int start, final int end) {
            if(start == end){
                java.util.List<Integer> l = new java.util.ArrayList<>();
                l.add(start);
                l.add(end);
                l.add(0);
                return l;
            }

            return this.getPath(graph, new int[] { start }, new int[] { end });
        }

        private java.util.List<Integer> getPath(final int[][] graph, final int[] starts, final int[] ends) {
            java.util.Arrays.sort(ends);

            // initialisation des variables necessaires a la resolution du probleme
            this.init(graph, starts);

            // calcul des distances par rapport au point de depart et recuperation
            // du point d'arrivee
            final int end = this.processDistances(ends);

            return (end != -1) ? this.buildPath(end) : null;
        }

        private void init(final int[][] graph, final int[] start) {
            this.matrice        = graph;
            this.dim            = graph.length;
            this.activesNodes   = new boolean[this.dim];
            this.precedences    = new int[this.dim];

            java.util.Arrays.fill(this.precedences, -1);

            this.distanceFromStart = new int[this.dim];

            java.util.Arrays.fill(this.distanceFromStart, Integer.MAX_VALUE);

            for (final int value : start)
                this.activeNode(value, value, 0);
        }

        private boolean isAdjacent(final int from, final int to) {
            return this.matrice[from][to] >= 0;
        }

        private int processDistances(final int[] ends) {
            // selectionne le prochain noeud a analyser (noeud courant)
            final int next = this.selectNextNode();

            // return -1 s'il n'y a plus de noeud a analyser, donc qu'il n'existe
            // pas de chemin satisfaisant la recherche
            if (next == -1)
                return -1;

            // retourne la position du noeud actuel s'il appartient au tableau des
            // destinations possibles
            if (java.util.Arrays.binarySearch(ends, next) >= 0)
                return next;

            // active les prochains noeuds a analyser a partir du noeud courant
            this.activeAdjacents(next);

            // desactive le noeud courant
            this.activesNodes[next] = false;

            // appel recursif de la methode pour traiter le prochain noeud
            return this.processDistances(ends);
        }

        private int selectNextNode() {
            int nextNode = -1;
            for (int node = 0; node < this.dim; node++)
                if (this.activesNodes[node] && (nextNode == -1 || this.distanceFromStart[node] < this.distanceFromStart[nextNode]))
                    nextNode = node;
            return nextNode;
        }
    }
    
    
    
}