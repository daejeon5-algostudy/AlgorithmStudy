package algoStudy.a0330;

import java.io.*;
import java.util.*;

public class Main_bj_G5_15591_MooTube {
	
	static class Node {
		int end;
		long price;
		
		public Node(int end, long price) {
			this.end = end;
			this.price = price;
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_15591"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		
		int N=Integer.parseInt(st.nextToken()); // N개의 동영상
		int Q=Integer.parseInt(st.nextToken()); // Q개의 질문
		
		ArrayList<Node>[] g=new ArrayList[N+1];
		for(int i=1; i<=N; i++) g[i]=new ArrayList<>();
		
		for(int i=1; i<N; i++) {
			st=new StringTokenizer(br.readLine()," ");
			int p=Integer.parseInt(st.nextToken());
			int q=Integer.parseInt(st.nextToken());
			long r=Long.parseLong(st.nextToken());
			g[p].add(new Node(q, r));
			g[q].add(new Node(p, r));
		}
		
		for(int i=0; i<Q; i++) {
			st=new StringTokenizer(br.readLine()," ");
			int k=Integer.parseInt(st.nextToken()); // 이상인값
			int v=Integer.parseInt(st.nextToken()); // 보고있는 영상
			
			boolean[] visit=new boolean[N+1];
			int cnt=0;
			Queue<Node> q=new LinkedList<>();
			q.add(new Node(v, 0));
			while(!q.isEmpty()) {
				Node now=q.poll();
				
				if(visit[now.end]) continue;
				visit[now.end]=true;
				for(Node next:g[now.end]) {
					if(!visit[next.end] && next.price >= k) {
						cnt++;
						q.add(new Node(next.end, next.price));
					}
				}
			}
			System.out.println(cnt);
		}
	}

}
