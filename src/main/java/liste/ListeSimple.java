package liste;

/**
 * Class using "Noeud" class to act as a simple list
 * It's dynamical and can hold up to 9,223,372,036,854,775,807 items as it's the java limit for longs
 * It's a chained list using "next" system, each node is linked to the other within it's class
 * */

public class ListeSimple {
    private long size;
    Noeud tete;

    /**
     * Method that returns the current value of the size variable (long)
     * */

    public long getSize() {
        return size;
    }

    /**
     * Used to add an element to the back of the list (piling system)
     * @param var1 must be an integer, it will generate the Noeud element carrying this int
     * @return nothing
     * */

    public void ajout(int element) {
        tete = new Noeud(element, tete);
        size++;
    }

    /**
     * Change the value of the node with the desired value
     * It gets trought the list until it finds a node with the right value, once done, change the int value
     * @param var1 is the value of the element
     * @param var2 is the value wanted
     * @return nothing
     * */

    public void modifiePremier(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null && courant.getElement() != element)
            courant = courant.getSuivant();
        if (courant != null)
            courant.setElement(nouvelleValeur);
    }

    /**
     * Does the same as for the precedent method but for all instead of just one
     * same parameters, just multiplying the application to the number of similar nodes
     * */

    public void modifieTous(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null) {
            if (courant.getElement() == element)
                courant.setElement(nouvelleValeur);
            courant = courant.getSuivant();
        }
    }

    /**
     * Casting method, used to give the string value of the list
     * Starting with the "ListeSimple(" it concatenate until it's finished
     * No parameters
     * @return the string value
     * */

    public String toString() {
        StringBuilder sb = new StringBuilder("ListeSimple(");
        Noeud n = tete;
        while (n != null) {
            sb.append(n);
            n = n.getSuivant();
            if (n != null)
                sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * Delete the first node associated with the right value
     * Navigates troughout the list to find the first node then deletes it (recursive)
     * @param element, must be an object
     * @return just the value of the return (1,2,3,...)
     * */

    public void supprimePremier(Object element) {
        if (tete != null) {
            if (tete.getElement() == element) {
                tete = tete.getSuivant();
                size--;
                return;
            }
            Noeud precedent = tete;
            Noeud courant = tete.getSuivant();
            while (courant != null && courant.getElement() != element) {
                precedent = precedent.getSuivant();
                courant = courant.getSuivant();
            }
            if (courant != null) {
                precedent.setSuivant(courant.getSuivant());
                size--;
            }
        }
    }

    /**
     * Same as for the last one but for everyone of the correct value
     * */

    public void supprimeTous(int element) {
       tete = supprimeTousRecurs(element, tete);
    }

    public Noeud supprimeTousRecurs(Object element, Noeud tete) {
        if (tete != null) {
            Noeud suiteListe = supprimeTousRecurs(element, tete.getSuivant());
            if (tete.getElement() == element) {
                size--;
                return suiteListe;
            } else {
                tete.setSuivant(suiteListe);
                return tete;
            }
        } else return null;
    }

    /**
     * Gets the before-last objects value, once it finds it return the value
     * Scan troughout the list using the link between every nodes until it reaches the last one then, return the one before (recursive)
     * No parameters
     * */

    public Noeud getAvantDernier() {
        if (tete == null || tete.getSuivant() == null)
            return null;
        else {
            Noeud courant = tete;
            Noeud suivant = courant.getSuivant();
            while (suivant.getSuivant() != null) {
                courant = suivant;
                suivant = suivant.getSuivant();
            }
            return courant;
        }
    }

    /**
     * Inverts the list, so that the first one will be the last one
     * Sets up the last one as the head, then goes back up to the original head (recursive)
     * No parameters, no returns
     * */

    public void inverser() {
        Noeud precedent = null;
        Noeud courant = tete;
        while (courant != null) {
            Noeud next = courant.getSuivant();
            courant.setSuivant(precedent);
            precedent = courant;
            courant = next;
        }
        tete = precedent;
    }

    /**
     * Gets the node before the one selected
     * Scan troughout the list until it finds the right node, then returns the last one
     * @param r is a node
     * @return the one before r
     * */

    public Noeud getPrecedent(Noeud r) {
    // la liste n'est pas vide puisqu'on transmet un Node de la liste et le Node existe obligatoirement
        Noeud precedent = tete;
        Noeud courant = precedent.getSuivant();
        while (courant != r) {
            precedent = courant;
            courant = courant.getSuivant();
        }
        return precedent;
    }

    /**
     * Exchange the place of two nodes
     * Basically, takes the links of each and inverts them, does the same for the one before, and the nodes are inverted
     * @param r1  is a node object
     * @param r2 is a node too
     * @return nothing
     * */

    public void echanger(Noeud r1, Noeud r2) {
        if (r1 == r2)
            return;
        Noeud precedentR1, precedentR2;
        if (r1 != tete && r2 != tete) {
            precedentR1 = getPrecedent(r1);
            precedentR2 = getPrecedent(r2);
            precedentR1.setSuivant(r2);
            precedentR2.setSuivant(r1);
        } else if (r1 == tete) {
            precedentR2 = getPrecedent(r2);
            precedentR2.setSuivant(tete);
            tete = r2;
        }
        else{
            precedentR1 = getPrecedent(r1);
            precedentR1.setSuivant(tete);
            tete = r1;
        }
        Noeud temp = r2.getSuivant();
        r2.setSuivant(r1.getSuivant());
        r1.setSuivant(temp);
    }

}