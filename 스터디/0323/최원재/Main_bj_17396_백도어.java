package practice0322;

import java.io.*;
import java.util.*;
public class Main_bj_17396_백도어 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int V=Integer.parseInt(st.nextToken());
		int E =Integer.parseInt(st.nextToken());
		boolean[] visited = new boolean[V];
		visited[0]=true; 
		ArrayList<ArrayList<int[]>> g = new ArrayList<>();
		for(int i=0; i<V; i++) {
			g.add(new ArrayList<int[]>());
		}
		int start = 0; 
		
		st = new StringTokenizer(br.readLine()," ");
		long [] distance = new long[V]; //거리 나타내기 
		for(int i=0; i<V; i++) {
			if(Integer.parseInt(st.nextToken())==1) {
				visited[i]=true;
			}
			distance[i]=Long.MAX_VALUE;
		}
		visited[V-1]=false;
		distance[start]=0;
		// distance visited
		for(int i=0; i<E; i++) {
			st=new StringTokenizer(br.readLine()," ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			g.get(a).add(new int[] {b, cost});
			g.get(b).add(new int[] {a, cost});			
		}
//	s
		
		PriorityQueue<node> pq = new PriorityQueue<>();
		
		pq.offer(new node(0,0));
	
		while(!pq.isEmpty()) {
			
			node no = pq.poll();
			
			int now = no.now;
			
			if(distance[now]<no.distance) continue;
			
			for(int i=0; i<g.get(now).size();i++) {
				long cost = distance[now]+g.get(now).get(i)[1];
				
				if(cost<distance[g.get(now).get(i)[0]]) {
					if(visited[g.get(now).get(i)[0]]) continue;
					distance[g.get(now).get(i)[0]] = cost;
					pq.offer(new node(g.get(now).get(i)[0], cost));
				}
			}
			//System.out.println(pq.size());
		
			//System.out.println(Arrays.toString(distance));
//			System.out.println(Arrays.toString(visited));
		}
		if(distance[V-1]==Long.MAX_VALUE)System.out.println(-1);
		else System.out.println(distance[V-1]);
		br.close();
		
	}
	
}
class node implements Comparable<node>{
	long distance;
	int now;
	
	public node(int now, long distance) {
		this.distance=distance;
		this.now=now;
	}
	@Override
	public int compareTo(node o) {
		if(this.distance<o.distance) {
			return -1;
		}
		return 1;
	}
}
