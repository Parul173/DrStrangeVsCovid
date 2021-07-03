package Covid;

import java.util.Comparator;

public class Node implements Comparator<Node>
{
private int v;
private int weight;
Node(int v, int w)
{
	this.v=v;
	this.weight=w;
}
	Node()
	{
		
	}
	
	public int getV() {
		return v;
	}
	public void setV(int v) {
		this.v = v;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int compare(Node node1,Node node2)
	{
		if(node1.weight<node2.weight)
			return -1;
		if(node1.weight>node2.weight)
			return 1;
		return 0;
	}
	
}
