/*
 * Copyright (C) BRIGUET Systems, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by Briguet, Juin 2018
 */
package graph.adjacent;



import graph.form.Curve;
import graph.form.Edge;
import graph.form.Node;



/**
 *
 * @author Briguet
 * @version 1.0
 */
public class Matrix extends GraphTheory implements java.io.Serializable{
    
    
    
    private static final long serialVersionUID = 3027959292523612508L;

    private int[][] mat;
    
    
    
//CONSTRUCTORS
    /**
     * Crée une matrice d'adjacence
     * @param listNode Correspond à une liste de sommets
     * @param listEdge Correspond à une liste d'arrêtes et/ou d'arcs
     */
    public Matrix(Node[] listNode, Edge[] listEdge) {
        super(listNode, listEdge);
        generateMatrix(listNode, listEdge);
    }
    
    /**
     * Crée une matrice d'adjacence
     * @param m Correspond à une autre matrice d'adjacence
     */
    public Matrix(Matrix m){
        super(m.listNode, m.listEdge);
        generateMatrix(m.listNode, m.listEdge);
    }
    
    /**
     * Crée une matrice d'adjacence
     * @param l Correspond à une liste d'adjacence
     */
    public Matrix(List l){
        super(l.listNode, l.listEdge);
        generateMatrix(l.listNode, l.listEdge);
    }
    
    
    
//METHODES PUBLICS
    /**
     * Renvoie une liste d'adjacence
     * @return Retourne une liste d'adjacence
     */
    public List getList(){
        return new List(listNode, listEdge);
    }

    @Override
    public Node[] getSuccessors(Node n) {
        java.util.List<Node> list = new java.util.ArrayList<>();
        for(int i=0;i<mat.length;i++){
            int val = mat[indexOf(listNode, n)][i];
            if(val>0){
                list.add(listNode[i]);
            }
        }
        Node[] ns = new Node[list.size()];
        for(int i=0;i<list.size();i++){
            ns[i] = list.get(i);
        }
        return ns;
    }

    @Override
    public Node[] getPredecessors(Node n) {
        java.util.List<Node> list = new java.util.ArrayList<>();
        for(int i=0;i<mat.length;i++){
            int val = mat[i][indexOf(listNode, n)];
            if(val>0){
                list.add(listNode[i]);
            }
        }
        Node[] ns = new Node[list.size()];
        for(int i=0;i<list.size();i++){
            ns[i] = list.get(i);
        }
        return ns;
    }

    @Override
    public int nbInDegrees(Node n) {
        return getPredecessors(n).length;
    }

    @Override
    public int nbOutDegrees(Node n) {
        return getSuccessors(n).length;
    }
    
    @Override
    public String toString(){
        int maxLength = 0;
        for (int[] mat1 : mat) {
            for (int j = 0; j<mat.length; j++) {
                String value = "N";
                if (mat1[j] != -1) {
                    value = "" + mat1[j];
                }
                if(value.length()>maxLength) maxLength = value.length();
            }
        }
        String str = "Matrix{\n\t";
        str += "  |";
        for(int i=0;i<listNode.length;i++){
            Node n = listNode[i];
            String value = getCompleteValue(n.getName().substring(0, 1), maxLength+1);
            if(i == 0){
                str += value;
            }else{
                str += " "+value;
            }
        }
        str += "\n\t--+";
        for(int i=0;i<listNode.length;i++){
            if(i < listNode.length-1)
                str += "--";
            else 
                str += "-";
            for(int j=0;j<maxLength;j++){
                str += "-";
            }
        }
        str += "\n\t";
        for(int i=0;i<mat.length;i++){
            str += listNode[i].getName().substring(0, 1)+" | ";
            for(int j=0;j<mat.length;j++){
                String value = "N";
                if(mat[i][j] != -1) value = ""+mat[i][j];
                value = getCompleteValue(value, maxLength);
                if(j==0){
                    str += value;
                }else{
                    str += ", " + value;
                }
            }
            if(i<mat.length-1) str += "\n\t";
        }
        str += "\n}";
        return str;
    }
    
    
    
//METHODES PRIVATES
    /**
     * Génère une matrice d'adjacence d'entier contenant les poids des Segments
     * @param listNode Correpsond à la liste des Sommets
     * @param listEdge Correspond à la liste des Segments
     */
    private void generateMatrix(Node[] listNode, Edge[] listEdge){
        this.mat = new int[listNode.length][listNode.length];
        
        for(int i=0;i<listNode.length;i++){
            Node predecessor = listNode[i];
            for(int j=0;j<listNode.length;j++){
                Node successor = listNode[j];
                this.mat[i][j] = -1;
                for (Edge e : listEdge) {
                    if(e instanceof Curve){
                        Curve c = (Curve) e;
                        if(c.getNodeSource().equals(predecessor) && c.getNodeTarget().equals(successor)){
                            this.mat[i][j] = c.getWeigth();
                        }
                    }else{
                        if((e.getNode1().equals(predecessor) && e.getNode2().equals(successor)) || (e.getNode1().equals(successor) && e.getNode2().equals(predecessor))){
                            this.mat[i][j] = e.getWeigth();
                            this.mat[j][i] = e.getWeigth();
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Complète une valeur par des espaces [ ]. Exemple: Si maxLength = 2 alors value = "A" -> value = " A"; Si maxLength = 5 alors value = "A" -> value = "    A"
     * @param value Correspond à la chaine de départ qui va être complété
     * @param maxLength Correspond au nombre maximum de caractère autorisé pour la nouvelle chaine
     * @return Retourne la chaine modifié. Exemple "    A";
     */
    private String getCompleteValue(String value, int maxLength){
        int sub = maxLength - value.length();
        String blank = "";
        for(int i=0;i<sub;i++){
            blank += " ";
        }
        return blank + value;
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
    
    
    
}