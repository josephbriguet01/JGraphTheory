/*
 * Copyright (C) BRIGUET Systems, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by Briguet, Juin 2018
 */
package graph.form;



import graph.adjacent.GraphTheory;



/**
 * Cette classe permet de créer un Sommet
 * @author Briguet
 * @version 1.0
 */
public class Node implements java.io.Serializable {
    
    
    
    private static int cptInt = 0;
    private static final long serialVersionUID = 3234147748943410945L;
    
    private String name;
    private final int id;

    
    
//CONSTRUCTOR
    /**
     * Crée un sommet
     * @param name Correspond au nom du sommet (il doit être unique)
     */
    public Node(String name) {
        this.name = name;
        this.id = cptInt++;
    }

    
    
//METHODES PUBLICS
    /**
     * Renvoie le nom du sommet
     * @return Retourne le nom du sommet
     */
    public String getName() {
        return name;
    }

    /**
     * Modifie le nom du sommet
     * @param name Correspond au nouveau nom
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Renvoie la liste des sommets prédécesseurs de ce sommet
     * @param gt Correspond au graphe qui va permettre le calcul
     * @return Retourne la liste des sommets prédécesseurs
     */
    public Node[] getPredeccessors(GraphTheory gt){
        return gt.getPredecessors(this);
    }
    
    /**
     * Renvoie la liste des sommets successeurs de ce sommet
     * @param gt Correspond au graphe qui va permettre le calcul
     * @return Retourne la liste des sommets successeurs
     */
    public Node[] getSuccessors(GraphTheory gt){
        return gt.getSuccessors(this);
    }

    /**
     * Renvoie le nombre de degrés entrant de ce sommet
     * @param gt Correspond au graphe qui va permettre le calcul
     * @return Retourne le nombre de degrés entrant
     */
    public int nbInDegrees(GraphTheory gt) {
        return getPredeccessors(gt).length;
    }

    /**
     * Renvoie le nombre de degrés sortant de ce sommet
     * @param gt Correspond au graphe qui va permettre le calcul
     * @return Retourne le nombre de degrés sortant
     */
    public int nbOutDegrees(GraphTheory gt) {
        return getSuccessors(gt).length;
    }

    /**
     * Renvoie le résultat de la méthode hashCode()
     * @return Retourne le résultat de la méthode hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.id;
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
        final Node other = (Node) obj;
        return this.id == other.id;
    }

    /**
     * Renvoie la représentation en chaîne de caractère de ce sommet
     * @return Retourne la représentation en chaîne de caractère de ce sommet
     */
    @Override
    public String toString() {
        return "Node{" + "name=" + name + '}';
    }
    
    
    
}