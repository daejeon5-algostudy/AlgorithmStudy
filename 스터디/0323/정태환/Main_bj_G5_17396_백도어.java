package algoStudy.a0323;

import java.io.*;
import java.util.*;

public class Main_bj_G5_17396_백도어 {

	static class Node implements Comparable<Node> {
		int end;
		long price;

		public Node(int end, long price) {
			this.end = end;
			this.price = price;
		}
		
		@Override
		public int compareTo(Node o) {
			return Long.compare(price, o.price);
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_17396"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		
		int N=Integer.parseInt(st.nextToken()); // 정점 수
		int M=Integer.parseInt(st.nextToken()); // 간선 수
		
		boolean[] v=new boolean[N];
		int[] ward=new int[N];
		long[] dist=new long[N];
		st=new StringTokenizer(br.readLine()," ");
		for(int i=0; i<N; i++) {
			ward[i]=Integer.parseInt(st.nextToken());
			dist[i]=Long.MAX_VALUE;
		}
		
		ArrayList<Node>[] g=new ArrayList[N];
		for(int i=0; i<N; i++) g[i]=new ArrayList<>();
		
		for(int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine()," ");
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			long c=Long.parseLong(st.nextToken());
			g[a].add(new Node(b, c));
			g[b].add(new Node(a, c));
		}
		
		PriorityQueue<Node> pq=new PriorityQueue<>();
		dist[0]=0;
		pq.offer(new Node(0, dist[0]));
		
		while(!pq.isEmpty()) {
			Node now=pq.poll();
			if(v[now.end]) continue;
			v[now.end]=true;
			
			for(Node next:g[now.end]) {
				if(!v[next.end] && (ward[next.end]==0 || next.end==N-1) && dist[next.end] > dist[now.end] + next.price) {
					dist[next.end] = dist[now.end] + next.price;
					pq.offer(new Node(next.end, dist[next.end]));
				}
			}
		}
		
		if(dist[N-1]==Long.MAX_VALUE)
			System.out.println(-1);
		else 
			System.out.println(dist[N-1]);
		
	}

}
