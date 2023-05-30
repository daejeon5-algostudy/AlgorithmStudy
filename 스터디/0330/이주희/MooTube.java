import java.io.*;
import java.util.*;

public class Main {
	
	static class Node {
		int vertex;
		long value;
		
		Node(int vertex, long value) {
			this.vertex = vertex;
			this.value = value;
		}
	}
	
	static int N;
	static List<Node>[] list;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		list = new List[N+1];
		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long v = Long.parseLong(st.nextToken());
			
			list[a].add(new Node(b, v));
			list[b].add(new Node(a, v));
		}
		
		StringBuilder sb = new StringBuilder();
		while(Q-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			int k = Integer.parseInt(st.nextToken());		// 이상인값
			int v = Integer.parseInt(st.nextToken());		// 보고있는 영상
			
			sb.append(bfs(k, v)).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static int bfs(int k, int v) {
		int cnt = 0;
		Queue<Node> que = new ArrayDeque<>();
		que.add(new Node(v, 0));
		boolean[] visited = new boolean[N+1];
		visited[v] = true;
		
		while(!que.isEmpty()) {
			Node now  = que.poll();
			
			for(Node neigh : list[now.vertex]) {
				if(visited[neigh.vertex] || neigh.value < k) continue;
				cnt++;
				visited[neigh.vertex] = true;
				que.add(neigh);
			}
			
		}
		
		return cnt;
	}

}
