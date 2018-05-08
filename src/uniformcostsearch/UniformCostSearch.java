/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniformcostsearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author sengkhunlim
 */
public class UniformCostSearch {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Node[] graph = initialGraph(8);
        printGraph(graph);

        String start, goal;
        start = "A";
        goal = "F";

        System.out.printf("Starting Node is %s\n", start);
        System.out.printf("Ending Node is %s\n", goal);

        Node result = uniformCostSearch(graph, start, goal);

        if (result != null) {
            System.out.printf("SUCCESS! From %s to %s node the min cost search is %d!\n", start, goal, result.getCost());
        } else {
            System.out.printf("FAIL! The goal %s is not found!\n", goal);
        }

    }

    public static Node uniformCostSearch(Node[] graph, String start, String goal) {

        Node n, m;
        List<Node> OPEN = new ArrayList<Node>();
        List<Node> CLOSED = new ArrayList<Node>();

        Node s = getNode(graph, start);

        if (s == null) {
            System.out.println("Cannot find the Starting Node!");
        }

        // Set OPEN = { s }
        OPEN.add(s);

        // if OPEN = {}, Terminate with failure
        while (!OPEN.isEmpty()) {

            // Select the minimum cost state, n, from OPEN and save n in CLOSED
            n = getMinimunCostNode(OPEN);
            OPEN.remove(n);
            CLOSED.add(n);

            // if n ε G, terminate with success
            if (n.getName().equals(goal)) {
                return n;
            }

            // Generate the successors of n using O
            m = getNode(graph, n.getName());
            while (m != null) {

                // Skip the first node in the array graph
                if (m.getWeight() == -1) {
                    m = m.getChild();
                    continue;
                }

                // if m ε [OPEN ⋃ CLOSED]
                if (checkElement(OPEN, m.getName()) || checkElement(CLOSED, m.getName())) {

                    // Set C(m) = min{ C(m), C(n)+C(n, m)}
                    if (m.getWeight() < n.getCost() + m.getWeight()) {

                        // if C(m) has decreased and m є CLOSED, move it to OPEN
                        if (checkElement(CLOSED, m.getName())) {
                            OPEN.add(m);
                        }

                    }

                } else { // if m ∉ [OPEN ⋃ CLOSED]

                    // Set C(m) = C(n) + C(n, m) and insert m in OPEN
                    m.setCost(n.getCost() + m.getWeight());
                    OPEN.add(m);

                }

                // Next Successor
                m = m.getChild();
            }
        }
        return null;
    }

    public static boolean checkElement(List<Node> arr, String name) {

        for (Node node : arr) {

            if (node.getName().equals(name)) {
                return true;
            }

        }
        return false;
    }

    public static Node getMinimunCostNode(List<Node> graph) {

        if (graph.size() <= 1) {
            return graph.get(0);
        }

        Node currentNode = graph.get(0);

        for (Node n : graph) {

            if (n.getWeight() < currentNode.getWeight()) {
                currentNode = n;
            }

        }

        return currentNode;
    }

    public static Node getNode(Node[] graph, String name) {

        for (int i = 0; i < graph.length; i++) {
            if (graph[i].getName().equals(name)) {
                return graph[i];
            }
        }
        return null;
    }

    public static Node[] initialGraph(int size) {

        String name, used;
        Node tmp;
        Node[] graph = new Node[size];

        for (int i = 0; i < size; i++) {

            int length = new Random().nextInt(size - 1) + 1;
            name = Character.toString((char) (i + 65));
            used = name;

            // The root node in each array
            graph[i] = new Node(name, -1);

            for (int j = 0; j < length; j++) {

                tmp = getLastChild(graph[i]);

                for (int k = 0; k < length; k++) {
                    name = Character.toString((char) (new Random().nextInt(size) + 65)); // generate random letter
                    if (!used.contains(name)) {
                        break;
                    }
                }
                
                if (used.contains(name)) {
                    break;
                }
                used += name;
                tmp.setChild(name, new Random().nextInt(size));
            }
        }
        return graph;
    }

    public static Node getLastChild(Node n) {

        Node current = n;

        while (true) {

            if (current.getChild() == null) {
                return current;
            }

            current = current.getChild();
        }
    }

    public static void printGraph(Node[] g) {

        Node current;

        for (int i = 0; i < g.length; i++) {

            current = g[i];

            while (true) {

                if (current != null) {
                    System.out.printf("%s %d -> ", current.getName(), current.getWeight());
                    current = current.getChild();
                } else {
                    System.out.println("NULL");
                    break;
                }
            }
        }

    }

}
