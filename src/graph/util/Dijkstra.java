/*
 * Copyright (C) BRIGUET Systems, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by Briguet, Juin 2018
 */
package graph.util;



import graph.form.Node;



/**
 * Implémentation de l'algorithme de Dijkstra.
 * @author Briguet
 * @version 1.0
 */
public abstract class Dijkstra {
    
    
    
    /**
     * Récupère un tableau de nodes (dans l'ordre) du chemin le plus court entre un Node et un autre
     * @return Retourne un tableau de nodes
     */
    public abstract Node[] getPath();
    
    /**
     * Récupère le poids total du chemin le plus court entre un Node et un autre
     * @return Retourne le poids du chemin
     */
    public abstract int getWeight();
    
    
    
}