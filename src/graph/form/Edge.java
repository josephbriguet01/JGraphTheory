/*
 * Copyright (C) BRIGUET Systems, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by Briguet, Juin 2018
 */
package graph.form;



/**
 * Cette classe réprésente une Arrête dans la théorie des graphes
 * @author Briguet
 * @version 1.0
 */
public class Edge implements java.io.Serializable {
    
    
    
    private static int cptInt = 0;
    private static final long serialVersionUID = 7749680557345858948L;
    
    private int weigth;
    private String name;
    private final int id;
    private Node n1;
    private Node n2;

    
    
//CONSTRUCTORS
    /**
     * Crée une arrête entre n1 et n2
     * @param name Correspond au nom de l'arrête
     * @param n1 Correspond au sommet 1
     * @param n2 Correspond au sommet 2
     */
    public Edge(String name, Node n1, Node n2) {
        this.name   = name;
        this.n1     = n1;
        this.n2     = n2;
        this.weigth = 1;
        this.id     = cptInt++;
    }

    /**
     * Crée une arrête entre n1 et n2
     * @param name Correspond au nom de l'arrête
     * @param n1 Correspond au sommet 1
     * @param n2 Correspond au sommet 2
     * @param weigth Correspond au poid de l'arrête
     */
    public Edge(String name, Node n1, Node n2, int weigth) {
        this.weigth = weigth;
        this.name   = name;
        this.n1     = n1;
        this.n2     = n2;
        this.id     = cptInt++;
    }
    
    
    
//METHODES PUBLICS
    /**
     * Renvoie le poid de l'arrête
     * @return Retourne le poid de l'arrête
     */
    public int getWeigth() {
        return weigth;
    }

    /**
     * Modifie le poid de l'arrête
     * @param weigth Correspond au nouveau poid de l'arrête
     */
    public void setWeigth(int weigth) {
        this.weigth = weigth;
    }

    /**
     * Renvoie le nom de l'arrête
     * @return Retourne le nom de l'arrête
     */
    public String getName() {
        return name;
    }

    /**
     * Modifie le nom de l'arrête
     * @param name Correspond au nouveau nom de l'arrête
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Renvoie le sommet 1
     * @return Retourne le sommet 1
     */
    public Node getNode1() {
        return n1;
    }

    /**
     * Modifie le sommet 1
     * @param n1 Correspond au nouveau sommet
     */
    public void setNode1(Node n1) {
        this.n1 = n1;
    }

    /**
     * Renvoie le sommet 2
     * @return Retourne le sommet 2
     */
    public Node getNode2() {
        return n2;
    }

    /**
     * Modifie le sommet 2
     * @param n2 Correspond au nouveau sommet
     */
    public void setNode2(Node n2) {
        this.n2 = n2;
    }

    /**
     * Renvoie le résultat de la méthode hashCode()
     * @return Retourne le résultat de la méthode hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.id;
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
        final Edge other = (Edge) obj;
        return this.id == other.id;
    }

    /**
     * Renvoie la représentation en chaîne de caractère de cette arrête
     * @return Retourne la représentation en chaîne de caractère de cette arrête
     */
    @Override
    public String toString() {
        return "Edge{" + "name=" + name + ", node1=" + n1 + ", node2=" + n2 + ", weigth=" + weigth + "}";
    }
    
    
    
}