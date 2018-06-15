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
 * Cette classe réprésente une Arc dans la théorie des graphes
 * @author Briguet
 * @version 1.0
 */
public class Curve extends Edge{
    
    
    
    private static final long serialVersionUID = -3487027852512672085L;

    
    
//CONSTRUCTORS
    /**
     * Crée une arc qui va de n1 vers n2
     * @param name Correspond au nom de l'arc
     * @param src Correspond au sommet 1
     * @param tar Correspond au sommet 2
     */
    public Curve(String name, Node src, Node tar) {
        super(name, src, tar);
    }

    /**
     * Crée une arc qui va de n1 vers n2
     * @param name Correspond au nom de l'arc
     * @param src Correspond au sommet 1
     * @param tar Correspond au sommet 2
     * @param weigth Correspond au poid de l'arc
     */
    public Curve(String name, Node src, Node tar, int weigth) {
        super(name, src, tar, weigth);
    }

    
    
//METHODES PUBLICS
    /**
     * Renvoie le sommet de départ
     * @return Retourne le sommet de départ
     */
    public Node getNodeSource() {
        return super.getNode1();
    }

    /**
     * Modifie le sommet de départ
     * @param n1 Correspond au nouveau sommet de départ
     */
    public void setNodeSource(Node n1) {
        super.setNode1(n1);
    }

    /**
     * Renvoie le sommet d'arrivé
     * @return Retourne le sommet d'arrivé
     */
    public Node getNodeTarget() {
        return super.getNode2();
    }

    /**
     * Modifie le sommet d'arrivé
     * @param n2 Correspond au nouveau sommet d'arrivé
     */
    public void setNodeTarget(Node n2) {
        super.setNode1(n2);
    }

    /**
     * Renvoie la représentation en chaîne de caractère de cet arc
     * @return Retourne la représentation en chaîne de caractère de cet arc
     */
    @Override
    public String toString() {
        return "Curve{" + "name=" + super.getName() + ", nodeSource=" + super.getNode1() + ", nodeTarget=" + super.getNode2() + ", weigth=" + super.getWeigth() + "}";
    }
    
    
    
}