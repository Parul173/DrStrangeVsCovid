package Covid;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		BufferedReader in =new BufferedReader(new InputStreamReader(System.in));
		Scanner sc=new Scanner(System.in);
		
		//NOTE: n:number of universe ; m:number of portals
		
		//System.out.println("Enter number of universe:");
		//System.out.println("Enter number of portals:");
		//int n=sc.nextInt();
		//int m=sc.nextInt();
		int n=6;
		int m=7;
		
		/*int portals[][]=new int[m][3];
		for(int i=0;i<m;i++) 
		{
			for(int j=0;j<3;j++)
			{
				portals[i][j]=sc.nextInt();
			}
		}
		*/
		
		ArrayList<ArrayList<Node>> adj=new ArrayList<>();
		
		for(int i=0;i<=n;i++)
		{
			adj.add(new ArrayList<Node>()); 
		}
		
		/*
		 * for(int i=0;i<m;i++) 
		 * { 
		 *   for(int j=0;j<3;j++) 
		 *   {
		 *    adj.get(portals[i][0]).add(new Node(portals[i][1],portals[i][2]));
		 *  adj.get(portals[i][1]).add(new Node(portals[i][0],portals[i][2]));
		 *   } 
		 * }
		 */
		
		adj.get(1).add(new Node(2,2));
		adj.get(2).add(new Node(1,2));
		adj.get(2).add(new Node(3,3));
		adj.get(3).add(new Node(2,3));
		adj.get(3).add(new Node(4,1));
		adj.get(4).add(new Node(3,1));
		adj.get(4).add(new Node(5,2));
		adj.get(5).add(new Node(4,2));
		adj.get(5).add(new Node(6,1));
		adj.get(6).add(new Node(5,1));
		adj.get(6).add(new Node(1,1));
		adj.get(1).add(new Node(6,1));
		adj.get(2).add(new Node(6,3));
		adj.get(6).add(new Node(2,3));
		
		
		ArrayList<ArrayList<Integer>> patrollingTime=new ArrayList<>();
		for(int i=0;i<=n;i++)
		{
			patrollingTime.add(new ArrayList<>());
		}
		
		/*
		 * for(int i=1;i<=n;i++)
		 *  { 
		 *   int input=sc.nextInt();
		 *   for(int j=0;j<input;j++)
		 *    {
		 *	   patrollingTime.get(i).add(sc.nextInt()); 
		 *	  }
		 * }
		 */
		
		patrollingTime.get(1).add(null);
		patrollingTime.get(2).add(null);
		patrollingTime.get(3).add(null);
		patrollingTime.get(4).add(6);
		patrollingTime.get(4).add(7);
		patrollingTime.get(4).add(8);
		patrollingTime.get(4).add(10);
		patrollingTime.get(5).add(null);
		patrollingTime.get(6).add(1);
		patrollingTime.get(6).add(2);
		patrollingTime.get(6).add(3);
		patrollingTime.get(6).add(5);
		
		
		System.out.println("Time required to reach the "+n+"th universe: "+shortestPath(1,adj,n,patrollingTime));
	}
	
	public static int shortestPath(int src, ArrayList<ArrayList<Node>> adj, int n, ArrayList<ArrayList<Integer>> patrollingTime)
	{
		int dist[]=new int[n+1];
		dist[0]=0;
		for(int i=1;i<=n;i++)
		{
			dist[i]=Integer.MAX_VALUE;
				
		}
		dist[1]=0;
		
		
		PriorityQueue<Node> pq=new PriorityQueue<>(n,new Node());
		pq.add(new Node(src,0));
		
		while(pq.size()>0)
		{
			Node node=pq.poll();
			
			for(Node it: adj.get(node.getV()))
			{
				if((dist[node.getV()]+it.getWeight())<dist[it.getV()])
				{
					dist[it.getV()]=dist[node.getV()]+it.getWeight();
					
					int len=patrollingTime.get(it.getV()).size();
					int count=0;
					for(int i=0;i<len;i++)
					{
						if(patrollingTime.get(it.getV()).get(i)!= null &&(patrollingTime.get(it.getV()).get(i)==dist[it.getV()]))
						{
							while(patrollingTime.get(it.getV()).get(i+1)-patrollingTime.get(it.getV()).get(i)==1)
							{
								count++;
								i++;
							}
						}
					}
		
					dist[it.getV()]=dist[it.getV()]+count; 
					pq.add(new Node(it.getV(),dist[it.getV()]));
				}
			}
		}
		System.out.println("Universe | Time required to reach this Universe");
		for(int i=1;i<=n;i++)
		{
			System.out.println(i+"        |       "+dist[i]);
		}
		System.out.println();
		return dist[n];
	}

}
