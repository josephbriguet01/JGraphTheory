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
public class List extends GraphTheory implements java.io.Serializable{
    
    
    
    private static final long serialVersionUID = 9185175016676773464L;
    private ListNode[] listNodes;

    
    
//CONSTRUCTORS
    /**
     * Crée une liste d'adjacence
     * @param listNode Correspond à une liste de sommets
     * @param listEdge Correspond à une liste d'arrêtes et/ou d'arcs
     */
    public List(Node[] listNode, Edge[] listEdge) {
        super(listNode, listEdge);
        generateList(listNode, listEdge);
    }
    
    /**
     * Crée une liste d'adjacence
     * @param l Correspond à une autre liste d'adjacence
     */
    public List(List l){
        super(l.listNode, l.listEdge);
        generateList(l.listNode, l.listEdge);
    }
    
    /**
     * Crée une liste d'adjacence
     * @param m Correspond à une matrice d'adjacence
     */
    public List(Matrix m){
        super(m.listNode, m.listEdge);
        generateList(m.listNode, m.listEdge);
    }
    
    
    
//METHODES PUBLICS
    /**
     * Renvoie une matrice d'adjacence
     * @return Retourne une matrice d'adjacence
     */
    public Matrix getMatrix(){
        return new Matrix(listNode, listEdge);
    }

    @Override
    public Node[] getSuccessors(Node n) {
        for (ListNode ln : listNodes) {
            if(ln.getFather().equals(n)){
                java.util.List<Node> nodes = ln.getSons();
                Node[] ns = new Node[nodes.size()];
                for(int j=0;j<nodes.size();j++){
                    ns[j] = nodes.get(j);
                }
                return ns;
            }
        }
        return null;
    }

    @Override
    public Node[] getPredecessors(Node n) {
        java.util.List<Node> ps = new java.util.ArrayList<>();
        for (ListNode ln : listNodes) {
            if(ln.getSons().contains(n))
                if(!ps.contains(ln.getFather())) 
                    ps.add(ln.getFather());
        }
        Node[] ns = new Node[ps.size()];
        for(int i=0;i<ps.size();i++){
            ns[i] = ps.get(i);
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
        String str = "List{\n\t";
        for(int i=0;i<listNodes.length;i++){
            ListNode ln = listNodes[i];
            str += ln.getFather()+" -> ";
            java.util.List<Node> ns = ln.getSons();
            for(int j=0;j<ns.size();j++){
                if(j==0){
                    str += ns.get(j);
                }else{
                    str += ", " + ns.get(j);
                }
            }
            if(i < listNodes.length-1) str += "\n\t";
        }
        str += "\n}";
        return str;
    }
    
    
    
//METHODES PRIVATES
    /**
     * Génère la liste d'adjacence
     * @param listNode Correpsond à la liste des Sommets
     * @param listEdge Correspond à la liste des Segments
     */
    private void generateList(Node[] listNode, Edge[] listEdge){
        listNodes = new ListNode[listNode.length];
        for(int i=0;i<listNode.length;i++){
            Node n = listNode[i];
            ListNode ln = new ListNode(n);
            for (Edge e : listEdge) {
                if(e instanceof Curve){
                    Curve c = (Curve) e;
                    if(c.getNodeSource().equals(n)){
                        ln.addSon(c.getNodeTarget());
                    }
                }else{
                    if(e.getNode1().equals(n)){
                        ln.addSon(e.getNode2());
                    }else if(e.getNode2().equals(n)){
                        ln.addSon(e.getNode1());
                    }
                }
            }
            listNodes[i] = ln;
        }
    }
    
    
    
//CLASS
    private class ListNode implements java.io.Serializable{
        
        
        
        private static final long serialVersionUID = -6468936644516570587L;
        
        private final Node father;
        private final java.util.List<Node> sons;

        
        
        public ListNode(Node father) {
            this.father = father;
            this.sons = new java.util.ArrayList<>();
        }

        public Node getFather() {
            return father;
        }
        
        public void addSon(Node n){
            this.sons.add(n);
        }

        public java.util.List<Node> getSons() {
            return sons;
        }

        @Override
        public String toString() {
            return "ListNode{" + "father=" + father + ", sons=" + sons + '}';
        }
    }
    
    
    
}