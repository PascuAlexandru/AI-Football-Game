package FootballGame.Astar;

import java.util.Objects; // Importă clasa Objects pentru a folosi metodele hashCode și equals

public class Node {
    public int x, y; // Coordonatele nodului pe teren
    public float g, h; // Costurile g (de la start la nod) și h (heuristic, de la nod la țintă)
    public Node parent; // Părintele nodului, folosit pentru a reconstrui calea

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.g = 0;
        this.h = 0;
        this.parent = null; // Inițial, nodul nu are părinte
    }

    public float getF() {
        return g + h; // Costul total f = g + h
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Verifică dacă cele două obiecte sunt identice
        if (obj == null || getClass() != obj.getClass()) return false; // Verifică dacă obiectul nu e null și e de același tip
        Node node = (Node) obj;
        return x == node.x && y == node.y; // Compară coordonatele x și y ale nodurilor
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y); // Creează un hash code bazat pe coordonatele x și y
    }
}

