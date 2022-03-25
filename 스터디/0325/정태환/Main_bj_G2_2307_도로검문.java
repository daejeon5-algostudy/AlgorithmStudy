package algoStudy.a0325;

import java.io.*;
import java.util.*;

public class Main_bj_G2_2307_도로검문 {
	
	static class Node implements Comparable<Node> {
		int end, time;

		public Node(int end, int time) {
			this.end = end;
			this.time = time;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(time, o.time);
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2307"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		
		int N=Integer.parseInt(st.nextToken()); // 지점의 수
		int M=Integer.parseInt(st.nextToken()); // 도로의 수
		ArrayList<Node>[] g=new ArrayList[N+1];
		for(int i=1; i<=N; i++) g[i]=new ArrayList<>();
		boolean[] v=new boolean[N+1];
		int[] dist=new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		int[] path=new int[N+1];
		
		for(int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine()," ");
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int t=Integer.parseInt(st.nextToken());
			g[a].add(new Node(b, t));
			g[b].add(new Node(a, t));
		}
		
		PriorityQueue<Node> pq=new PriorityQueue<>();
		dist[1]=0;
		pq.add(new Node(1, 0));
		
		// 최단거리구함
		while(!pq.isEmpty()) { 
			Node now=pq.poll();
			
			if(v[now.end]) continue;
			v[now.end]=true;
			
			for(Node next:g[now.end]) {
				if(!v[next.end] && dist[next.end]>dist[now.end]+next.time) {
					path[next.end]=now.end;
					dist[next.end]=dist[now.end]+next.time;
					pq.add(new Node(next.end, dist[next.end]));
				}
			}
		}
//		System.out.println(Arrays.toString(path));
		int min=dist[N];
		int max=0; // 지연시간 최대값 구하기
		LinkedList<Integer> route=new LinkedList<>();
		route.add(N);
		int idx=N;
		while(true) {
			if(path[idx]==0) break;
			route.add(path[idx]);
			idx=path[idx];
		}
//		System.out.println(route.toString());
		
		// 최단경로 경로중에 하나씩 제외해가며 다시 최단거리 구함
		while(true) {
			if(route.size()==1) break;
			int cp1=route.get(0);
			int cp2=route.get(1);
			route.remove(0);
//			System.out.print(cp1+" "+cp2+" ");
			
			v=new boolean[N+1];
			dist=new int[N+1];
			for(int i=1; i<=N; i++) dist[i]=Integer.MAX_VALUE;
			dist[1]=0;
			pq.add(new Node(1, 0));
			
			while(!pq.isEmpty()) { 
				Node now=pq.poll();
				
				if(v[now.end]) continue;
				v[now.end]=true;
				
				for(Node next:g[now.end]) {
					if(!v[next.end] && dist[next.end]>dist[now.end]+next.time && !(now.end==cp2 && next.end==cp1)) {
						dist[next.end]=dist[now.end]+next.time;
						pq.add(new Node(next.end, dist[next.end]));
					}
				}
			}
//			System.out.println(dist[N]);
			if(dist[N]==Integer.MAX_VALUE) {
				max=-1;
				break;
			}
			if(dist[N]-min > max) {
				max=dist[N]-min;
			}
		}
		
		System.out.println(max);
	}

}
