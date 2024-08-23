package FootballGame.Astar;

import java.util.*;

public class AStar {
    private static final int[][] DIRECTIONS = {
            {1, 0}, {0, 1}, {-1, 0}, {0, -1},
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1} // Direcțiile de mișcare (inclusiv diagonale)
    };
    private static final float MAX_SPEED = 2.1f; // Viteza maximă pe axele x și y
    private int[][] grid; // Terenul de joc, reprezentat ca o matrice
    private Node start, goal; // Nodurile de start și de țintă

    public AStar(int[][] grid, Node start, Node goal) {
        this.grid = grid; // Inițializează terenul de joc
        this.start = start; // Inițializează nodul de start
        this.goal = goal; // Inițializează nodul de țintă
    }

    private float heuristic(Node node) {
        return (float) Math.sqrt(Math.pow(node.x - goal.x, 2) + Math.pow(node.y - goal.y, 2)); // Calculează distanța Euclidiană
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] == 0; // Verifică dacă poziția e validă și nu e obstacol
    }

    private List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();
        for (int[] direction : DIRECTIONS) {
            int newX = node.x + direction[0];
            int newY = node.y + direction[1];
            if (isValid(newX, newY)) {
                neighbors.add(new Node(newX, newY)); // Adaugă vecinii valizi
            }
        }
        return neighbors;
    }

    public List<Node> findPath() {
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparing(Node::getF)); // Coada de priorități pentru noduri deschise
        Set<Node> closedSet = new HashSet<>(); // Setul de noduri închise
        start.g = 0;
        start.h = heuristic(start);
        openSet.add(start); // Adaugă nodul de start în openSet

        while (!openSet.isEmpty()) {
            Node current = openSet.poll(); // Extrage nodul cu costul f cel mai mic
            if (current.equals(goal)) {
                return reconstructPath(current); // Dacă am ajuns la țintă, reconstruiește calea
            }

            closedSet.add(current); // Adaugă nodul curent în closedSet

            for (Node neighbor : getNeighbors(current)) {
                if (closedSet.contains(neighbor)) continue; // Sari peste vecinii care sunt deja în closedSet

                float tentativeG = current.g + MAX_SPEED;

                if (!openSet.contains(neighbor) || tentativeG < neighbor.g) {
                    neighbor.g = tentativeG; // Actualizează costul g
                    neighbor.h = heuristic(neighbor); // Recalculează heuristicul h
                    neighbor.parent = current; // Setează părintele nodului

                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor); // Adaugă vecinul în openSet dacă nu e deja acolo
                    }
                }
            }
        }
        return null; // Dacă nu găsim calea, returnăm null
    }

    private List<Node> reconstructPath(Node node) {
        List<Node> path = new ArrayList<>();
        while (node != null) {
            path.add(node); // Adaugă nodul curent în cale
            node = node.parent; // Mergi la părintele nodului
        }
        Collections.reverse(path); // Inversează lista pentru a obține calea de la start la țintă
        return path;
    }

}

