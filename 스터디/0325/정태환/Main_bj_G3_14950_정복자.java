package algoStudy.a0325;

import java.io.*;
import java.util.*;

public class Main_bj_G3_14950_정복자 {

	static class Node implements Comparable<Node> {
		int start, end, price;

		public Node(int start, int end, int price) {
			this.start = start;
			this.end = end;
			this.price = price;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(price, o.price);
		}
	}
	
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_14950"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		
		int N=Integer.parseInt(st.nextToken()); // 도시의 개수
		int M=Integer.parseInt(st.nextToken()); // 도로의 개수
		int T=Integer.parseInt(st.nextToken()); // 도시의 비용
		
		parent=new int[N+1]; for(int i=1; i<=N; i++) parent[i]=i;
		
		PriorityQueue<Node> pq=new PriorityQueue<>();
		
		for(int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine()," ");
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			pq.add(new Node(a, b, c));
		}
		
		int sum=0;
		int add=0;
		while(!pq.isEmpty()) {
			Node now=pq.poll();
			
			if(!isUnion(now.start, now.end)) {
				sum+=now.price+add;
				add+=T;
				union(now.start, now.end);
			}
		}
		System.out.println(sum);
	}
	
	static void union(int x, int y) {
		x=find(x);
		y=find(y);
		if(find(x)==find(y)) return;
		parent[y]=x;
	}
	
	static boolean isUnion(int x, int y) {
		x=find(x);
		y=find(y);
		if(find(x)==find(y)) return true;
		return false;
	}
	
	static int find(int x) {
		if(x==parent[x]) return x;
		return parent[x]=find(parent[x]);
	}

}
