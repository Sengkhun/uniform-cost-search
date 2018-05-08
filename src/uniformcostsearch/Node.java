/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniformcostsearch;

/**
 *
 * @author sengkhunlim
 */
public class Node {

    private final int weight;
    private int cost;
    private final String name;
    private Node child;

    public Node(String name, int weight) {
        this.name = name;
        this.weight = weight;
        this.cost = 0;
        this.child = null;
    }

    public String getName() {
        return this.name;
    }

    public void setChild(String name, int weight) {
        this.child = new Node(name, weight);
    }

    public Node getChild() {
        return this.child;
    }

    public int getWeight() {
        return this.weight;
    }

    public int getCost() {
        return this.cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

}
