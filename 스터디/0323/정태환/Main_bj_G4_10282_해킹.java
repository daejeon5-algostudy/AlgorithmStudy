package algoStudy.a0323;

import java.io.*;
import java.util.*;

public class Main_bj_G4_10282_해킹 {

	static class Node implements Comparable<Node> {
		int end, price;

		public Node(int end, int price) {
			this.end = end;
			this.price = price;
		}
		
		@Override
		public int compareTo(Node o) {
			return price-o.price;
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_10282"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T=Integer.parseInt(br.readLine());
		for(int tc=0; tc<T; tc++) {
			st=new StringTokenizer(br.readLine()," ");
			int n=Integer.parseInt(st.nextToken()); // 컴퓨터대수
			int d=Integer.parseInt(st.nextToken()); // 의존성 개수
			int c=Integer.parseInt(st.nextToken()); // 해킹당한 컴퓨터
			
			ArrayList<Node>[] g=new ArrayList[n+1];
			for(int i=1; i<=n; i++) g[i]=new ArrayList<>();
			boolean[] v=new boolean[n+1];
			int[] dist=new int[n+1];
			for(int i=1; i<=n; i++)
				dist[i]=Integer.MAX_VALUE;
			
			for(int i=0; i<d; i++) {
				st=new StringTokenizer(br.readLine()," ");
				int a=Integer.parseInt(st.nextToken());
				int b=Integer.parseInt(st.nextToken());
				int s=Integer.parseInt(st.nextToken());
//				g[a].add(new Node(b, s));
				g[b].add(new Node(a, s));
			}
			
			PriorityQueue<Node> pq=new PriorityQueue<>();
			dist[c]=0;
			pq.add(new Node(c, 0));
			
			while(!pq.isEmpty()) {
				Node now=pq.poll();
				
				if(v[now.end]) continue;
				v[now.end]=true;
				
				for(Node next:g[now.end]) {
					if(!v[next.end] && dist[next.end] > dist[now.end]+next.price){
						dist[next.end] = dist[now.end]+next.price;
						pq.add(new Node(next.end, dist[next.end]));
					}
				}
			}
//			System.out.println(Arrays.toString(v));
			int cnt=0, max=0;
			for(int i=1; i<=n; i++) {
				if(v[i]) cnt++;
				if(dist[i]>max && dist[i]!=Integer.MAX_VALUE) max=dist[i];
			}
			System.out.println(cnt+" "+max);
		}
	}

}
